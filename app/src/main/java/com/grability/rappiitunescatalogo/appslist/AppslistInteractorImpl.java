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
    public void getApps() {
        repository.getSavedApps();
    }

    @Override
    public void updateLocalDB(int limit) {
        repository.retrieveNewestApps(limit);
    }

}
