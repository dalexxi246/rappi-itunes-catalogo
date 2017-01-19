package com.grability.rappiitunescatalogo.appslist.view;

import com.grability.rappiitunescatalogo.model.db.tables.App;
import com.grability.rappiitunescatalogo.model.db.tables.Category;

import java.util.List;

/**
 * Created by wilmer on 16/01/17.
 */

public interface AppsListView {

    void showProgressBar();

    void hideProgressBar();

    void searchCatalog(int category_code, String app_name);

    void setAppList(List<App> appList);

    void getCategories();

    void onCategoriesReaded(List<Category> categories);

    void onError(String errorMsg);

}
