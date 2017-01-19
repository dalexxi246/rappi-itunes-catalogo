package com.grability.rappiitunescatalogo.appdetails;

import com.grability.rappiitunescatalogo.appdetails.events.AppDetailsEvent;
import com.grability.rappiitunescatalogo.libs.base.EventBus;
import com.grability.rappiitunescatalogo.model.db.tables.App;

/**
 * Created by wilmer on 15/01/17.
 */

public class AppDetailsRepositoryImpl implements AppDetailsRepository {

    private EventBus eventBus;

    public AppDetailsRepositoryImpl(EventBus eventBus) {
        this.eventBus = eventBus;
    }

    @Override
    public void getAppInfo(int app_id) {
        App app = new App();
        app.setId(app_id);
        app.load();
        post(app);
    }

    private void post(App app) {
        AppDetailsEvent event = new AppDetailsEvent();
        event.setType(AppDetailsEvent.READ_EVENT);
        event.setApp(app);
        eventBus.post(event);
    }
}
