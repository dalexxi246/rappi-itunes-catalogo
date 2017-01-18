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
    public void getApps(int limit, int category_code) {
        repository.getSavedApps(limit, category_code, "");
    }

    @Override
    public void getApps(String name) {
        repository.getSavedApps(20, 0, name);
    }

    @Override
    public void getApps(int limit, int category_code, String name) {
        repository.getSavedApps(limit, category_code, name);
    }
}
