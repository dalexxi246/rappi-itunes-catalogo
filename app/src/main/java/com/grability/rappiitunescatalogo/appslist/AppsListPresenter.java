package com.grability.rappiitunescatalogo.appslist;

import com.grability.rappiitunescatalogo.appslist.event.AppslistEvent;
import com.grability.rappiitunescatalogo.model.db.tables.App;
import com.grability.rappiitunescatalogo.model.db.tables.Category;

/**
 * Created by wilmer on 16/01/17.
 */

public interface AppsListPresenter {

    void onCreate();

    void onDestroy();

    void getApps(int limit, Category c, App a);

    void onEventMainThreat(AppslistEvent evt);

}
