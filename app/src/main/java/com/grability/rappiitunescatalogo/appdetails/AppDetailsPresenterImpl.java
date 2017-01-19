package com.grability.rappiitunescatalogo.appdetails;

import com.grability.rappiitunescatalogo.appdetails.events.AppDetailsEvent;
import com.grability.rappiitunescatalogo.appdetails.view.AppDetailsView;
import com.grability.rappiitunescatalogo.libs.base.EventBus;

import org.greenrobot.eventbus.Subscribe;

/**
 * Created by wilmer on 16/01/17.
 */

public class AppDetailsPresenterImpl implements AppDetailsPresenter {

    private EventBus eventBus;
    private AppDetailsView view;
    private AppDetailsInteractor interactor;

    public AppDetailsPresenterImpl(EventBus eventBus, AppDetailsView view, AppDetailsInteractor interactor) {
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
    public void getAppInfo(int app_id) {
        interactor.getApp(app_id);
    }

    @Override
    @Subscribe
    public void onEventMainThreat(AppDetailsEvent evt) {
        if (view != null) {
            switch (evt.getType()) {
                case AppDetailsEvent.READ_EVENT:
                    if (evt.getErrorMsg() == null) {
                        view.onAppLoaded(evt.getApp());
                    }
                    break;
            }
        }
    }
}
