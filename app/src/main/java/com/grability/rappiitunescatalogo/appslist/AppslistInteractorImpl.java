package com.grability.rappiitunescatalogo.appslist;

/**
 * Created by wilmer on 15/01/17.
 */

public class AppslistInteractorImpl implements AppslistInteractor {

    AppslistRepository repository;

    public AppslistInteractorImpl(AppslistRepository repository) {
        this.repository = repository;
    }

    @Override
    public void getApps(int category_code, String name) {
        repository.getSavedApps(category_code, name);
    }

    @Override
    public void getCategories() {
        repository.getSavedCategories();
    }
}
