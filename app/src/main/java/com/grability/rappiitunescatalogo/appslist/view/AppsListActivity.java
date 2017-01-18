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
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import com.grability.rappiitunescatalogo.R;
import com.grability.rappiitunescatalogo.appdetails.view.AppDetailsActivity;
import com.grability.rappiitunescatalogo.appdetails.view.AppDetailsFragment;
import com.grability.rappiitunescatalogo.model.db.tables.App;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AppsListActivity extends AppCompatActivity implements AppsListFragment.OnFragmentInteractionListener {

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

    FrameLayout containerList;
    FrameLayout containerDetails;

    AppsListFragment appsListFragment;
    AppDetailsFragment appDetailsFragment;
    FragmentManager fragmentManager;

    // TODO: Busqueda por nombre (Usando CircleReveal)
    // TODO; Busqueda por categoria (Usando NavigationView)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_list);
        ButterKnife.bind(this);
        setupContainers();
        setupAppBar();

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
    }

    @OnClick(R.id.btn_search)
    public void onClick() {
        if (appsListFragment != null) {
            if (containerAnimatedSearch.getVisibility() == View.VISIBLE) {
                closeSearchByName();
                String name = txtAppNameSearch.getText().toString();
                if (name.length() > 0) {
                    appbar.setTitle("Nombre: " + name);
                    appsListFragment.searchCatalog(AppsListFragment.FILTER_NO_CATEGORY, name);
                }
            }
            if (containerAnimatedSearch.getVisibility() == View.INVISIBLE) {
                performSearchByName();
            }
        }
    }

    private void performSearchByName() {
        if (containerAnimatedSearch.getVisibility() == View.INVISIBLE) {
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {

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
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {

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
}
