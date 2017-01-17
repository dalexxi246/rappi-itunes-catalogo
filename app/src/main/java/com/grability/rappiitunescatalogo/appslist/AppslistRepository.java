package com.grability.rappiitunescatalogo.appslist;

import com.grability.rappiitunescatalogo.model.db.tables.Category;

/**
 * Created by wilmer on 15/01/17.
 */

public interface AppslistRepository {

    void getSavedApps(int limit, Category c);

    void retrieveNewestApps(int limit);

}
