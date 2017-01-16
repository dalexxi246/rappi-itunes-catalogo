package com.grability.rappiitunescatalogo.appslist;

/**
 * Created by wilmer on 15/01/17.
 */

public interface AppslistRepository {
    void getSavedApps();

    void retrieveNewestApps(int limit);
}
