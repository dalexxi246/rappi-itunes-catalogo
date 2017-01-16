package com.grability.rappiitunescatalogo.appslist.view.ui;

import com.grability.rappiitunescatalogo.model.db.tables.App;
import com.grability.rappiitunescatalogo.model.db.tables.Category;

import java.util.List;

/**
 * Created by wilmer on 16/01/17.
 */

public interface AppsListView {

    void searchCatalog(Category c, App a);

    void setAppList(List<App> appList);

    void onError(String errorMsg);

}
