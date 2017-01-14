package com.grability.rappiitunescatalogo.preload.ui.view;

/**
 * Created by wilmer on 12/01/17.
 */

public interface SplashView {
    void refreshCatalog();

    void showProgressBar();

    void hideProgressBar();

    void onCatalogUpdated();

    void onFirstUse();
}
