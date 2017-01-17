package com.grability.rappiitunescatalogo.appslist;

import com.grability.rappiitunescatalogo.appslist.event.AppslistEvent;

/**
 * Created by wilmer on 16/01/17.
 */

public interface AppsListPresenter {

    void onCreate();

    void onDestroy();

    void getApps(int limit, int category_code);

    void onEventMainThreat(AppslistEvent evt);

}
