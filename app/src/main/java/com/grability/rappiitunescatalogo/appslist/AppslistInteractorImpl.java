package com.grability.rappiitunescatalogo.appslist;

import com.grability.rappiitunescatalogo.model.db.tables.Category;

/**
 * Created by wilmer on 15/01/17.
 */

public class AppslistInteractorImpl implements AppslistInteractor {

    AppslistRepository repository;

    public AppslistInteractorImpl(AppslistRepository repository) {
        this.repository = repository;
    }

    @Override
    public void getApps(int limit, Category c) {
        repository.getSavedApps(limit, c);
    }

    @Override
    public void updateLocalDB(int limit) {
        repository.retrieveNewestApps(limit);
    }

}
