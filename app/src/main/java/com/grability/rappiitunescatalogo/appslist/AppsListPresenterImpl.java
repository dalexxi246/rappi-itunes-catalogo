package com.grability.rappiitunescatalogo.appslist;

import com.grability.rappiitunescatalogo.appslist.event.AppslistEvent;
import com.grability.rappiitunescatalogo.appslist.view.AppsListView;
import com.grability.rappiitunescatalogo.libs.base.EventBus;
import com.grability.rappiitunescatalogo.model.db.tables.App;
import com.grability.rappiitunescatalogo.model.db.tables.Category;

/**
 * Created by wilmer on 16/01/17.
 */

public class AppsListPresenterImpl implements AppsListPresenter {

    private EventBus eventBus;
    private AppsListView view;
    private AppslistInteractor interactor;

    public AppsListPresenterImpl(EventBus eventBus, AppsListView view, AppslistInteractor interactor) {
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
    public void getApps(int limit, Category c, App a) {
        if (view != null) {
            view.showProgressBar();
        }
        interactor.getApps(limit, c, a);
    }

    @Override
    public void onEventMainThreat(AppslistEvent evt) {
        if (view != null) {
            switch (evt.getType()) {
                case AppslistEvent.READ_EVENT:
                    if (evt.getErrorMsg() != null) {
                        view.hideProgressBar();
                        view.setAppList(evt.getApps());
                    } else {
                        view.onError(evt.getErrorMsg());
                    }
                    break;
            }
        }
    }
}
