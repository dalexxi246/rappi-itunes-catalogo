package com.grability.rappiitunescatalogo.preload.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.grability.rappiitunescatalogo.R;
import com.grability.rappiitunescatalogo.appslist.view.ui.AppListActivity;
import com.grability.rappiitunescatalogo.preload.ui.view.SplashView;

public class Splash extends AppCompatActivity implements SplashView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        onCatalogUpdated();
    }

    @Override
    public void refreshCatalog() {

    }

    @Override
    public void showProgressBar() {

    }

    @Override
    public void hideProgressBar() {

    }

    @Override
    public void onCatalogUpdated() {
        gotoAppListActivity();
    }

    @Override
    public void onFirstUse() {

    }

    private void gotoAppListActivity() {
        startActivity(new Intent(this, AppListActivity.class));
    }
}
