package com.grability.rappiitunescatalogo.appdetails;

/**
 * Created by wilmer on 15/01/17.
 */

public class AppDetailsInteractorImpl implements AppDetailsInteractor {

    AppDetailsRepository repository;

    public AppDetailsInteractorImpl(AppDetailsRepository repository) {
        this.repository = repository;
    }

    @Override
    public void getApp(int app_id) {
        repository.getAppInfo(app_id);
    }
}
