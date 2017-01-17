package com.grability.rappiitunescatalogo;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.grability.rappiitunescatalogo.appslist.di.AppsListComponent;
import com.grability.rappiitunescatalogo.appslist.di.AppsListModule;
import com.grability.rappiitunescatalogo.appslist.di.DaggerAppsListComponent;
import com.grability.rappiitunescatalogo.appslist.view.AppsListView;
import com.grability.rappiitunescatalogo.appslist.view.adapter.OnAppListItemClickListener;
import com.grability.rappiitunescatalogo.libs.di.LibsModule;
import com.grability.rappiitunescatalogo.splash.di.DaggerSplashComponent;
import com.grability.rappiitunescatalogo.splash.di.SplashComponent;
import com.grability.rappiitunescatalogo.splash.di.SplashModule;
import com.grability.rappiitunescatalogo.splash.ui.SplashView;
import com.raizlabs.android.dbflow.config.FlowManager;

/**
 * Created by wilmer on 11/01/17.
 */

public class RappiItunesApp extends Application {

    private SharedPreferences prefs;

    private LibsModule libsModule;
    private RappiItunesAppModule appModule;

    @Override
    public void onCreate() {
        super.onCreate();
        initDB();
        initModules();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        DBTearDown();
    }

    public Context getContext(){
        return getApplicationContext();
    }

    private void initDB() {
        FlowManager.init(getApplicationContext());
    }

    private void DBTearDown() {
        FlowManager.destroy();
    }

    private void initModules() {
        // TODO: DI (inicializar los modulos)
        libsModule = new LibsModule();
        appModule = new RappiItunesAppModule(this);
    }

    public SplashComponent getSplashComponent(SplashView view) {
        return DaggerSplashComponent
                .builder()
                .libsModule(libsModule)
                .rappiItunesAppModule(appModule)
                .splashModule(new SplashModule(view))
                .build();
    }

    public AppsListComponent getAppsListComponent(AppsListView appListView, OnAppListItemClickListener onAppListItemClickListener) {
        return DaggerAppsListComponent
                .builder()
                .libsModule(libsModule)
                .rappiItunesAppModule(appModule)
                .appsListModule(new AppsListModule(appListView, onAppListItemClickListener))
                .build();
    }
}

// TODO: http://www.jsonschema2pojo.org/ (Crear Clases a partir de JSon)
// TODO: http://www.jsoneditoronline.org/ (Consultar Endpoints de APIs y Navegar por JSon's)