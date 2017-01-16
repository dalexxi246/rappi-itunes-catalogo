package com.grability.rappiitunescatalogo.splash;

import com.grability.rappiitunescatalogo.appslist.event.AppslistEvent;

/**
 * Created by wilmer on 15/01/17.
 */

public interface SplashPresenter {

    void onCreate();

    void onDestroy();

    void getApps(int limit);

    void onEventMainThreat(AppslistEvent evt);
}
