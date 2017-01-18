package com.grability.rappiitunescatalogo.appslist;

/**
 * Created by wilmer on 15/01/17.
 */

public interface AppslistInteractor {
    void getApps(int limit, int category_code);

    void getApps(int limit, int category_code, String name);

    void getApps(String name);
}
