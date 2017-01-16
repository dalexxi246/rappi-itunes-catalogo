package com.grability.rappiitunescatalogo.splash.ui;

/**
 * Created by wilmer on 12/01/17.
 */

public interface SplashView {

    void refreshCatalog();

    void showProgressBar();

    void hideProgressBar();

    void onCatalogUpdated();

    void onError(String errorMsg);
}
