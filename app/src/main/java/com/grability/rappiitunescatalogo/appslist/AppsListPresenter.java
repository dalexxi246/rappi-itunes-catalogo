package com.grability.rappiitunescatalogo.appslist;

import com.grability.rappiitunescatalogo.appslist.event.AppslistEvent;
import com.grability.rappiitunescatalogo.appslist.event.CategorieslistEvent;

/**
 * Created by wilmer on 16/01/17.
 */

public interface AppsListPresenter {

    void onCreate();

    void onDestroy();

    void getApps(int category_code, String name);

    void getCategories();

    void onEventMainThreat(AppslistEvent evt);

    void onEventMainThreat(CategorieslistEvent evt);

}
