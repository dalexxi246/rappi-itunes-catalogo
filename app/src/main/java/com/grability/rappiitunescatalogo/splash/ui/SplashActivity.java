package com.grability.rappiitunescatalogo.splash.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.ContentLoadingProgressBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RelativeLayout;

import com.grability.rappiitunescatalogo.R;
import com.grability.rappiitunescatalogo.RappiItunesApp;
import com.grability.rappiitunescatalogo.appslist.view.AppsListActivity;
import com.grability.rappiitunescatalogo.splash.SplashPresenter;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SplashActivity extends AppCompatActivity implements SplashView, View.OnClickListener {

    @BindView(R.id.progressBar)
    ContentLoadingProgressBar progressBar;

    @BindView(R.id.activity_splash)
    RelativeLayout layout;

    @Inject
    SplashPresenter presenter;
    @Inject
    SharedPreferences preferences;

    private RappiItunesApp app;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);

        app = (RappiItunesApp) getApplication();
        setupInjection();

        presenter.onCreate();
        refreshCatalog();
    }

    private void setupInjection() {
        app.getSplashComponent(this).inject(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }

    @Override
    public void refreshCatalog() {
        // TODO: El valor se define en una SharedPreferences
        presenter.getApps(20);
    }

    @Override
    public void showProgressBar() {
        progressBar.show();
    }

    @Override
    public void hideProgressBar() {
        progressBar.hide();
    }

    @Override
    public void onCatalogUpdated() {
        gotoAppListActivity();
    }

    @Override
    public void onError(String errorMsg) {
        Snackbar message;
        message = Snackbar
                .make(layout, errorMsg, Snackbar.LENGTH_INDEFINITE)
                .setAction(getResources().getString(R.string.splash_label_reload), this);
        if (android.os.Build.VERSION.SDK_INT == Build.VERSION_CODES.M) {
            message.setActionTextColor(getResources().getColor(R.color.colorAccent, getTheme()));
        } else {
            message.setActionTextColor(getResources().getColor(R.color.colorAccent));
        }
        message.show();
    }

    private void gotoAppListActivity() {
        startActivity(new Intent(this, AppsListActivity.class));
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == android.support.design.R.id.snackbar_action) {
            refreshCatalog();
        }
    }
}
