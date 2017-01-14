package com.grability.rappiitunescatalogo.model.db;

import com.raizlabs.android.dbflow.annotation.Database;

/**
 * Created by wilmer on 11/01/17.
 */

@Database(name = AppsCatalogDatabase.NAME, version = AppsCatalogDatabase.VERSION)
public class AppsCatalogDatabase {
    public static final int VERSION = 1;
    public static final String NAME = "AppsCatalog";
}
