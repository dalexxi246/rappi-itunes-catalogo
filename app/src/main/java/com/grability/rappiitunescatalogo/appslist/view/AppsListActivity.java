package com.grability.rappiitunescatalogo.appslist.view;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.grability.rappiitunescatalogo.R;
import com.grability.rappiitunescatalogo.appdetails.view.AppDetailsActivity;
import com.grability.rappiitunescatalogo.appdetails.view.AppDetailsFragment;
import com.grability.rappiitunescatalogo.model.db.tables.App;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AppsListActivity extends AppCompatActivity implements AppsListFragment.OnFragmentInteractionListener {

    @BindView(R.id.txt_title_term)
    TextView txtTitleTerm;
    @BindView(R.id.appbar)
    Toolbar appbar;
    @BindView(R.id.container_list)
    FrameLayout containerList;
    @BindView(R.id.container_details)
    FrameLayout containerDetails;
    @BindView(R.id.btn_search)
    FloatingActionButton btnSearch;

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

    private void setupAppBar() {
        setSupportActionBar(appbar);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @OnClick(R.id.btn_search)
    public void onClick() {
        if (appsListFragment != null) {
            appsListFragment.searchCatalog(AppsListFragment.FILTER_NO_CATEGORY, "Mario");
        }
    }

    @Override
    public void onFragmentError(String errorMsg) {
        Snackbar message;
        message = Snackbar
                .make(btnSearch, errorMsg, Snackbar.LENGTH_INDEFINITE);
        if (android.os.Build.VERSION.SDK_INT == Build.VERSION_CODES.M) {
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
}
