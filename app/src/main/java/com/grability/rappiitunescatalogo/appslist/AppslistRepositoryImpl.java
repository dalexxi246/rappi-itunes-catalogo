package com.grability.rappiitunescatalogo.appslist;

import android.support.annotation.NonNull;

import com.grability.rappiitunescatalogo.appslist.event.AppslistEvent;
import com.grability.rappiitunescatalogo.libs.base.EventBus;
import com.grability.rappiitunescatalogo.model.db.tables.App;
import com.grability.rappiitunescatalogo.model.db.tables.App_Table;
import com.grability.rappiitunescatalogo.model.db.tables.Category_Table;
import com.grability.rappiitunescatalogo.model.rest.ITunesApiService;
import com.raizlabs.android.dbflow.sql.language.CursorResult;
import com.raizlabs.android.dbflow.sql.language.From;
import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.raizlabs.android.dbflow.structure.database.transaction.QueryTransaction;

import java.util.List;

/**
 * Created by wilmer on 15/01/17.
 */

public class AppslistRepositoryImpl implements AppslistRepository {

    private EventBus eventBus;
    private ITunesApiService apiService;

    public AppslistRepositoryImpl(EventBus eventBus, ITunesApiService apiService) {
        this.eventBus = eventBus;
        this.apiService = apiService;
    }

    @Override
    public void getSavedApps(int limit, int category_code) {
        From from = SQLite.select().from(App.class);
        if (category_code > 0) {
            from.innerJoin(Category_Table.class)
                    .on(Category_Table.id.withTable().eq(App_Table.category_id.withTable()))
                    .where(Category_Table.id.eq(category_code));
        }
        from.async().queryResultCallback(new QueryTransaction.QueryResultCallback<App>() {
            @Override
            public void onQueryResult(QueryTransaction transaction, @NonNull CursorResult tResult) {
                List apps = tResult.toListClose();
                post(apps);
            }
        }).execute();
    }

    private void post(int updateEvent) {
        AppslistEvent event = new AppslistEvent();
        event.setType(updateEvent);
        eventBus.post(event);
    }

    private void post(String message, int updateEvent) {
        AppslistEvent event = new AppslistEvent();
        event.setType(updateEvent);
        event.setErrorMsg(message);
        eventBus.post(event);
    }

    private void post(List<App> apps) {
        AppslistEvent event = new AppslistEvent();
        event.setType(AppslistEvent.READ_EVENT);
        event.setApps(apps);
        eventBus.post(event);
    }
}
