package com.grability.rappiitunescatalogo.appslist;

import com.grability.rappiitunescatalogo.appslist.event.AppslistEvent;
import com.grability.rappiitunescatalogo.appslist.event.CategorieslistEvent;
import com.grability.rappiitunescatalogo.libs.base.EventBus;
import com.grability.rappiitunescatalogo.model.db.tables.App;
import com.grability.rappiitunescatalogo.model.db.tables.App_Table;
import com.grability.rappiitunescatalogo.model.db.tables.Category;
import com.grability.rappiitunescatalogo.model.rest.ITunesApiService;
import com.raizlabs.android.dbflow.sql.language.ConditionGroup;
import com.raizlabs.android.dbflow.sql.language.SQLite;

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
    public void getSavedApps(final int category_code, final String name) {
        ConditionGroup sqlWhere = ConditionGroup.clause();
        List<App> apps;
        if (name.length() > 0) {
            sqlWhere.and(App_Table.name.like("%" + name + "%"));
        }
        if (category_code != 0) {
            sqlWhere.and(App_Table.category_id.eq(category_code));
        }
        if (category_code > 0 || name.length() > 0) {
            apps = SQLite.select().from(App.class).where(sqlWhere).queryList();
        } else {
            apps = SQLite.select().from(App.class).queryList();
        }
        post(apps);
        /*
        from.async().queryResultCallback(new QueryTransaction.QueryResultCallback<App>() {
            @Override
            public void onQueryResult(QueryTransaction transaction, @NonNull CursorResult tResult) {
                List<App> apps = tResult.toListClose();
                if (category_code > 0 || name.length() > 0) {
                    Iterator<App> it = apps.iterator();
                    while (it.hasNext()){
                        App a = it.next();
                        if (a.getCategory().getId() != category_code){
                            it.remove();
                        }
                        if (!a.getTitle().contains(name)){
                            it.remove();
                        }
                    }
                }
                post(apps);
            }
        }).execute();
        */
    }

    @Override
    public void getSavedCategories() {
        List<Category> categories = SQLite.select().from(Category.class).queryList();
        postCategoriesReaded(categories);
    }

    private void postCategoriesReaded(List<Category> categories) {
        CategorieslistEvent event = new CategorieslistEvent();
        event.setCategories(categories);
        event.setType(CategorieslistEvent.READ_EVENT);
        eventBus.post(event);
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
        event.setType(AppslistEvent.READ_APPLIST_EVENT);
        event.setApps(apps);
        eventBus.post(event);
    }
}
