package com.grability.rappiitunescatalogo.splash;

/**
 * Created by wilmer on 15/01/17.
 */

public class SplashInteractorImpl implements SplashInteractor {

    SplashRepository repository;

    public SplashInteractorImpl(SplashRepository repository) {
        this.repository = repository;
    }

    @Override
    public void updateLocalDB(int limit) {
        repository.retrieveNewestApps(limit);
    }

}
