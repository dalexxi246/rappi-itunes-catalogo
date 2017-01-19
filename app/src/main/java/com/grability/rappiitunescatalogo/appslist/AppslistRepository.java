package com.grability.rappiitunescatalogo.appslist;

/**
 * Created by wilmer on 15/01/17.
 */

public interface AppslistRepository {

    void getSavedApps(int category_code, String name);

    void getSavedCategories();
}
