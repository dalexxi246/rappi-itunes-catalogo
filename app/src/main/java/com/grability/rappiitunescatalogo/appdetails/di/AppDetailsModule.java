package com.grability.rappiitunescatalogo.appdetails.di;

import com.grability.rappiitunescatalogo.appdetails.AppDetailsInteractor;
import com.grability.rappiitunescatalogo.appdetails.AppDetailsInteractorImpl;
import com.grability.rappiitunescatalogo.appdetails.AppDetailsPresenter;
import com.grability.rappiitunescatalogo.appdetails.AppDetailsPresenterImpl;
import com.grability.rappiitunescatalogo.appdetails.AppDetailsRepository;
import com.grability.rappiitunescatalogo.appdetails.AppDetailsRepositoryImpl;
import com.grability.rappiitunescatalogo.appdetails.view.AppDetailsView;
import com.grability.rappiitunescatalogo.libs.base.EventBus;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by wilmer on 19/01/17.
 */

@Module
public class AppDetailsModule {

    AppDetailsView view;

    public AppDetailsModule(AppDetailsView view) {
        this.view = view;
    }

    @Provides
    @Singleton
    AppDetailsView provideAppDetailsView() {
        return this.view;
    }

    @Provides
    @Singleton
    AppDetailsPresenter provideAppDetailsPresenter(EventBus eventBus, AppDetailsView view, AppDetailsInteractor listInteractor) {
        return new AppDetailsPresenterImpl(eventBus, view, listInteractor);
    }

    @Provides
    @Singleton
    AppDetailsInteractor provideAppDetailsInteractor(AppDetailsRepository repository) {
        return new AppDetailsInteractorImpl(repository);
    }

    @Provides
    @Singleton
    AppDetailsRepository provideAppDetailsRepository(EventBus eventBus) {
        return new AppDetailsRepositoryImpl(eventBus);
    }

}
