package com.grability.rappiitunescatalogo.appslist.view;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

import com.grability.rappiitunescatalogo.R;
import com.grability.rappiitunescatalogo.appdetails.view.AppDetailsActivity;
import com.grability.rappiitunescatalogo.appdetails.view.AppDetailsFragment;
import com.grability.rappiitunescatalogo.model.db.tables.App;
import com.grability.rappiitunescatalogo.model.db.tables.Category;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AppsListActivity extends AppCompatActivity implements AppsListFragment.OnFragmentInteractionListener, View.OnClickListener {

    @BindView(R.id.appbar)
    Toolbar appbar;
    @BindView(R.id.btn_search)
    FloatingActionButton btnSearch;
    @BindView(R.id.txt_app_name_search)
    EditText txtAppNameSearch;
    @BindView(R.id.txtil_app_name_search)
    TextInputLayout txtilAppNameSearch;
    @BindView(R.id.container_animated_search)
    RelativeLayout containerAnimatedSearch;
    @BindView(R.id.btn_perform_search)
    ImageButton btnPerformSearch;
    @BindView(R.id.navview_categories)
    NavigationView navviewCategories;

    FrameLayout containerList;
    FrameLayout containerDetails;

    AppsListFragment appsListFragment;
    AppDetailsFragment appDetailsFragment;
    FragmentManager fragmentManager;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;

    // TODO; Busqueda por categoria (Usando NavigationView)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_list);
        ButterKnife.bind(this);
        setupContainers();
        setupAppBar();
        containerAnimatedSearch.setVisibility(View.INVISIBLE);

        fragmentManager = getSupportFragmentManager();
        if (containerDetails == null) {
            appsListFragment = AppsListFragment.newInstance(AppsListFragment.SCREEN_SIZE_PHONE, AppsListFragment.DEFAULT_LIMIT_APP_LIST, AppsListFragment.FILTER_NO_CATEGORY);
        } else {
            appsListFragment = AppsListFragment.newInstance(AppsListFragment.SCREEN_SIZE_TABLET, AppsListFragment.DEFAULT_LIMIT_APP_LIST, AppsListFragment.FILTER_NO_CATEGORY);
            appDetailsFragment = AppDetailsFragment.newInstance("", "");
            fragmentManager.beginTransaction().replace(R.id.container_details, appDetailsFragment).commit();
        }
        appsListFragment.setOnFragmentInteractionListener(this);
        fragmentManager.beginTransaction().replace(R.id.container_list, appsListFragment).commit();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        if (containerAnimatedSearch.getVisibility() == View.VISIBLE) {
            closeSearchByName();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!checkConnectivity()) {
            onFragmentError("Modo sin conexion");
        }
    }

    private void setupContainers() {
        containerList = (FrameLayout) findViewById(R.id.container_list);
        containerDetails = (FrameLayout) findViewById(R.id.container_details);
    }

    private void setupAppBar() {
        setSupportActionBar(appbar);
        appbar.setTitle(R.string.appslist_label_all_aps);
        appbar.setNavigationOnClickListener(this);
    }

    @OnClick(R.id.btn_search)
    public void onClickSearch() {
        if (appsListFragment != null) {
            if (containerAnimatedSearch.getVisibility() == View.VISIBLE) {
                performSearchByAppName();
            }
            if (containerAnimatedSearch.getVisibility() == View.INVISIBLE) {
                showSearchByNameInput();
            }
        }
    }

    private void performSearchByAppName() {
        closeSearchByName();
        String name = txtAppNameSearch.getText().toString();
        if (name.length() > 0) {
            appbar.setTitle("Nombre: " + name);
            appsListFragment.searchCatalog(AppsListFragment.FILTER_NO_CATEGORY, name);
        } else {
            appbar.setTitle(R.string.appslist_label_all_aps);
            appsListFragment.searchCatalog(AppsListFragment.FILTER_NO_CATEGORY, "");
        }
    }

    private void showSearchByNameInput() {
        if (containerAnimatedSearch.getVisibility() == View.INVISIBLE) {
            btnSearch.hide();
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

                int cx = (containerAnimatedSearch.getLeft() + containerAnimatedSearch.getRight()) / 2;
                int cy = (containerAnimatedSearch.getTop() + containerAnimatedSearch.getBottom()) / 2;
                int finalRadius = Math.max(containerAnimatedSearch.getWidth(), containerAnimatedSearch.getHeight());

                Animator anim = ViewAnimationUtils.createCircularReveal(containerAnimatedSearch, cx, cy, 0, finalRadius);
                containerAnimatedSearch.setVisibility(View.VISIBLE);
                anim.start();
            } else {
                containerAnimatedSearch.setVisibility(View.VISIBLE);
            }
        }
    }

    private void closeSearchByName() {
        if (containerAnimatedSearch.getVisibility() == View.VISIBLE) {
            btnSearch.show();
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                int cx = (containerAnimatedSearch.getLeft() + containerAnimatedSearch.getRight()) / 2;
                int cy = (containerAnimatedSearch.getTop() + containerAnimatedSearch.getBottom()) / 2;
                int initialRadius = containerAnimatedSearch.getWidth();

                Animator anim = ViewAnimationUtils.createCircularReveal(containerAnimatedSearch, cx, cy, initialRadius, 0);
                anim.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        containerAnimatedSearch.setVisibility(View.INVISIBLE);
                    }
                });

                anim.start();
            } else {
                containerAnimatedSearch.setVisibility(View.INVISIBLE);
            }
        }
    }

    @Override
    public void onFragmentError(String errorMsg) {
        Snackbar message;
        message = Snackbar
                .make(btnSearch, errorMsg, Snackbar.LENGTH_INDEFINITE);
        if (Build.VERSION.SDK_INT == Build.VERSION_CODES.M) {
            message.setActionTextColor(getResources().getColor(R.color.colorAccent, getTheme()));
        } else {
            message.setActionTextColor(getResources().getColor(R.color.colorAccent));
        }
        message.show();
    }

    @Override
    public void onSelectedApp(App app) {
        if (containerDetails == null) {
            Intent i = new Intent(this, AppDetailsActivity.class);
            // TODO: Enviar id de App en el bundle para que el fragment la cargue
            startActivity(i);
        } else {
            // TODO: Enviar id de App como parametro para que el fragment la cargue
            appDetailsFragment = AppDetailsFragment.newInstance("", "");
            fragmentManager.beginTransaction().replace(R.id.container_details, appDetailsFragment).commit();
        }
    }

    @Override
    public void onCategoriesReaded(List<Category> categories) {
        // TODO: AQUI !!!!! navviewCategories.getMenu().add()
    }

    public boolean checkConnectivity() {
        boolean enabled = true;

        ConnectivityManager connectivityManager = (ConnectivityManager) this
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = connectivityManager.getActiveNetworkInfo();

        if ((info == null || !info.isConnected() || !info.isAvailable())) {
            enabled = false;
        }
        return enabled;
    }

    @OnClick(R.id.btn_perform_search)
    public void onClickPerformSearch() {
        performSearchByAppName();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                break;
        }
    }
}
