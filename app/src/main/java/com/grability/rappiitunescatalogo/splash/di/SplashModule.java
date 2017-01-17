package com.grability.rappiitunescatalogo.splash.di;

import com.grability.rappiitunescatalogo.libs.base.EventBus;
import com.grability.rappiitunescatalogo.model.rest.ITunesApiClient;
import com.grability.rappiitunescatalogo.model.rest.ITunesApiService;
import com.grability.rappiitunescatalogo.splash.SplashInteractor;
import com.grability.rappiitunescatalogo.splash.SplashInteractorImpl;
import com.grability.rappiitunescatalogo.splash.SplashPresenter;
import com.grability.rappiitunescatalogo.splash.SplashPresenterImpl;
import com.grability.rappiitunescatalogo.splash.SplashRepository;
import com.grability.rappiitunescatalogo.splash.SplashRepositoryImpl;
import com.grability.rappiitunescatalogo.splash.ui.SplashView;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by wilmer on 16/01/17.
 */
@Module
public class SplashModule {
    SplashView view;

    public SplashModule(SplashView view) {
        this.view = view;
    }

    @Provides
    @Singleton
    SplashView provideSplashView() {
        return this.view;
    }

    @Provides
    @Singleton
    SplashPresenter provideSplashPresenter(EventBus eventBus, SplashView view, SplashInteractor splashInteractor) {
        return new SplashPresenterImpl(eventBus, view, splashInteractor);
    }

    @Provides
    @Singleton
    SplashInteractor provideSplashInteractor(SplashRepository repository) {
        return new SplashInteractorImpl(repository);
    }

    @Provides
    @Singleton
    SplashRepository provideSplashRepository(EventBus eventBus, ITunesApiService apiService) {
        return new SplashRepositoryImpl(eventBus, apiService);
    }

    @Provides
    @Singleton
    ITunesApiService provideITunesApiService() {
        ITunesApiClient client = new ITunesApiClient();
        return client.getITunesApiService();
    }

}

//TODO: No reutilizar metodos de otros modulos, podria complicar la inyeccion