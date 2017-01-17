package com.grability.rappiitunescatalogo.appslist.di;

import com.grability.rappiitunescatalogo.appslist.AppsListPresenter;
import com.grability.rappiitunescatalogo.appslist.AppsListPresenterImpl;
import com.grability.rappiitunescatalogo.appslist.AppslistInteractor;
import com.grability.rappiitunescatalogo.appslist.AppslistInteractorImpl;
import com.grability.rappiitunescatalogo.appslist.AppslistRepository;
import com.grability.rappiitunescatalogo.appslist.AppslistRepositoryImpl;
import com.grability.rappiitunescatalogo.appslist.view.AppsListView;
import com.grability.rappiitunescatalogo.appslist.view.adapter.AppsListAdapter;
import com.grability.rappiitunescatalogo.appslist.view.adapter.OnAppListItemClickListener;
import com.grability.rappiitunescatalogo.libs.base.EventBus;
import com.grability.rappiitunescatalogo.libs.base.ImageLoader;
import com.grability.rappiitunescatalogo.model.db.tables.App;
import com.grability.rappiitunescatalogo.model.rest.ITunesApiClient;
import com.grability.rappiitunescatalogo.model.rest.ITunesApiService;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by wilmer on 16/01/17.
 */
@Module
public class AppsListModule {
    AppsListView view;
    OnAppListItemClickListener onAppListItemClickListener;

    public AppsListModule(AppsListView view, OnAppListItemClickListener onAppListItemClickListener) {
        this.view = view;
        this.onAppListItemClickListener = onAppListItemClickListener;
    }

    @Provides
    @Singleton
    AppsListView provideAppsListView() {
        return this.view;
    }

    @Provides
    @Singleton
    AppsListPresenter provideAppsListPresenter(EventBus eventBus, AppsListView view, AppslistInteractor listInteractor) {
        return new AppsListPresenterImpl(eventBus, view, listInteractor);
    }

    @Provides
    @Singleton
    AppslistInteractor provideAppsListInteractor(AppslistRepository repository) {
        return new AppslistInteractorImpl(repository);
    }

    @Provides
    @Singleton
    AppslistRepository provideAppsListRepository(EventBus eventBus, ITunesApiService apiService) {
        return new AppslistRepositoryImpl(eventBus, apiService);
    }

    @Provides
    @Singleton
    ITunesApiService provideITunesApiService() {
        ITunesApiClient client = new ITunesApiClient();
        return client.getITunesApiService();
    }

    @Provides
    @Singleton
    AppsListAdapter provideAppsListAdapter(List<App> appList, ImageLoader imageLoader, OnAppListItemClickListener onItemClickListener) {
        return new AppsListAdapter(appList, imageLoader, onItemClickListener);
    }

    @Provides
    @Singleton
    OnAppListItemClickListener provideOnAppListItemClickListener() {
        return this.onAppListItemClickListener;
    }

    @Provides
    @Singleton
    List<App> provideAppList() {
        return new ArrayList<App>();
    }
}