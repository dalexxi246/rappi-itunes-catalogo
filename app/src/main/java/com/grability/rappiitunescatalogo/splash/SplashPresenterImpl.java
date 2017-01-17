package com.grability.rappiitunescatalogo.splash;

import com.grability.rappiitunescatalogo.appslist.event.AppslistEvent;
import com.grability.rappiitunescatalogo.libs.base.EventBus;
import com.grability.rappiitunescatalogo.splash.ui.SplashView;

import org.greenrobot.eventbus.Subscribe;

/**
 * Created by wilmer on 15/01/17.
 */

public class SplashPresenterImpl implements SplashPresenter {

    private EventBus eventBus;
    private SplashView view;
    private SplashInteractor interactor;

    public SplashPresenterImpl(EventBus eventBus, SplashView view, SplashInteractor interactor) {
        this.eventBus = eventBus;
        this.view = view;
        this.interactor = interactor;
    }

    @Override
    public void onCreate() {
        eventBus.register(this);
    }

    @Override
    public void onDestroy() {
        eventBus.unregister(this);
    }

    @Override
    public void getApps(int limit) {
        if (view != null) {
            view.showProgressBar();
        }
        interactor.updateLocalDB(limit);
    }

    @Override
    @Subscribe
    public void onEventMainThreat(AppslistEvent evt) {
        if (view != null) {
            switch (evt.getType()) {
                case AppslistEvent.UPDATE_EVENT:
                    if (evt.getErrorMsg() != null) {
                        view.hideProgressBar();
                        view.onCatalogUpdated();
                    } else {
                        view.onError(evt.getErrorMsg());
                    }
                    break;
            }
        }
    }
}
