package com.grability.rappiitunescatalogo.domain.db;

import com.raizlabs.android.dbflow.annotation.Database;

/**
 * Created by wilmer on 11/01/17.
 */

@Database(name = AppsCatalog.NAME, version = AppsCatalog.VERSION)
public class AppsCatalog {
    public static final int VERSION = 1;
    public static final String NAME = "AppsCatalog";
}
