package com.grability.rappiitunescatalogo.splash;

import com.grability.rappiitunescatalogo.appslist.event.AppslistEvent;
import com.grability.rappiitunescatalogo.domain.entities.ApiResponse;
import com.grability.rappiitunescatalogo.domain.entities.Entry;
import com.grability.rappiitunescatalogo.domain.entities.ImImage;
import com.grability.rappiitunescatalogo.libs.base.EventBus;
import com.grability.rappiitunescatalogo.model.db.AppsCatalogDatabase;
import com.grability.rappiitunescatalogo.model.db.tables.App;
import com.grability.rappiitunescatalogo.model.db.tables.AppImage;
import com.grability.rappiitunescatalogo.model.db.tables.Artist;
import com.grability.rappiitunescatalogo.model.db.tables.Category;
import com.grability.rappiitunescatalogo.model.rest.ITunesApiService;
import com.raizlabs.android.dbflow.config.DatabaseDefinition;
import com.raizlabs.android.dbflow.config.FlowManager;
import com.raizlabs.android.dbflow.structure.database.DatabaseWrapper;
import com.raizlabs.android.dbflow.structure.database.transaction.ProcessModelTransaction;
import com.raizlabs.android.dbflow.structure.database.transaction.Transaction;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by wilmer on 15/01/17.
 */

public class SplashRepositoryImpl implements SplashRepository {

    private boolean[] updates = new boolean[4];

    private EventBus eventBus;
    private ITunesApiService apiService;

    public SplashRepositoryImpl(EventBus eventBus, ITunesApiService apiService) {
        this.eventBus = eventBus;
        this.apiService = apiService;
    }

    @Override
    public void retrieveNewestApps(int limit) {
        Call<ApiResponse> call = apiService.getFeed(limit);
        call.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                ApiResponse r = response.body();
                if (r != null) {
                    updateAppsFromAPI(r);
                } else {
                    post(response.message(), AppslistEvent.UPDATE_EVENT);
                }
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                post(t.getLocalizedMessage(), AppslistEvent.UPDATE_EVENT);
            }
        });
    }

    private void updateAppsFromAPI(ApiResponse apiResponse) {

        // Pasar del Api a los objetos de la DB
        List<Entry> api_entries = apiResponse.getFeed().getEntry();

        List<Artist> db_artists = new ArrayList<>();
        List<Category> db_Categories = new ArrayList<>();
        List<App> db_Apps = new ArrayList<>();
        List<AppImage> db_AppImages = new ArrayList<>();

        for (Entry e : api_entries) {
            Artist artist = new Artist();
            artist.setHref(e.getImArtist().getAttributes().getHref());
            artist.setName(e.getImArtist().getLabel());
            db_artists.add(artist);

            Category category = new Category();
            category.setId(e.getCategory().getAttributes().getImId());
            category.setLabel(e.getCategory().getAttributes().getLabel());
            category.setScheme(e.getCategory().getAttributes().getScheme());
            category.setTerm(e.getCategory().getAttributes().getTerm());
            db_Categories.add(category);

            App appEntry = new App();
            appEntry.setArtist(artist);
            appEntry.setCategory(category);
            appEntry.setCurrency(e.getImPrice().getAttributes().getAmount());
            appEntry.setCurrencyType(e.getImPrice().getAttributes().getCurrency());
            appEntry.setId(e.getId().getAttributes().getImId());
            appEntry.setId_package(e.getId().getAttributes().getImBundleId());
            appEntry.setName(e.getImName().getLabel());
            appEntry.setReleaseDate(e.getImReleaseDate().getAttributes().getLabel());
            appEntry.setReleaseTimestamp(e.getImReleaseDate().getLabel());
            appEntry.setRights(e.getRights().getLabel());
            appEntry.setSummary(e.getSummary().getLabel());
            appEntry.setTitle(e.getTitle().getLabel());
            appEntry.setWeb_link(e.getLink().getAttributes().getHref());
            db_Apps.add(appEntry);

            for (ImImage imImage : e.getImImage()) {
                AppImage image = new AppImage();
                image.setApp(appEntry);
                image.setHeight(imImage.getAttributes().getHeight());
                image.setSource(imImage.getLabel());
                db_AppImages.add(image);
            }
        }

        // Indicador de actualizaciones completadas en la BD (Se Limpian)
        for (int i = 0; i < updates.length; i++) {
            updates[i] = false;
        }

        //Construccion de las sentencias de actualizacion asincronas
        DatabaseDefinition database = FlowManager.getDatabase(AppsCatalogDatabase.class);

        ProcessModelTransaction<Artist> artistProcessModelTransaction =
                new ProcessModelTransaction.Builder<>(new ProcessModelTransaction.ProcessModel<Artist>() {
                    @Override
                    public void processModel(Artist artist, DatabaseWrapper wrapper) {
                        if (!artist.exists(wrapper)) {
                            artist.save(wrapper);
                        }
                    }
                }).addAll(db_artists).build();

        ProcessModelTransaction<Category> categoryProcessModelTransaction =
                new ProcessModelTransaction.Builder<>(new ProcessModelTransaction.ProcessModel<Category>() {
                    @Override
                    public void processModel(Category category, DatabaseWrapper wrapper) {
                        if (!category.exists(wrapper)) {
                            category.save(wrapper);
                        }
                    }
                }).addAll(db_Categories).build();

        ProcessModelTransaction<App> appProcessModelTransaction =
                new ProcessModelTransaction.Builder<>(new ProcessModelTransaction.ProcessModel<App>() {
                    @Override
                    public void processModel(App app, DatabaseWrapper wrapper) {
                        if (!app.exists()) {
                            app.save(wrapper);
                        }
                    }
                }).addAll(db_Apps).build();

        ProcessModelTransaction<AppImage> imageProcessModelTransaction =
                new ProcessModelTransaction.Builder<>(new ProcessModelTransaction.ProcessModel<AppImage>() {
                    @Override
                    public void processModel(AppImage image, DatabaseWrapper wrapper) {
                        if (!image.exists()) {
                            image.save(wrapper);
                        }
                    }
                }).addAll(db_AppImages).build();

        // Definicion de Callbacks para actualizaciones cuando sean exitosas y cuando hayan errores
        Transaction t1 = database.beginTransactionAsync(artistProcessModelTransaction)
                .success(new Transaction.Success() {
                    @Override
                    public void onSuccess(Transaction transaction) {
                        updates[0] = true;
                        verifyCompleteUpdate(updates);
                    }
                }).error(new Transaction.Error() {
                    @Override
                    public void onError(Transaction transaction, Throwable error) {
                        error.printStackTrace();
                        post(error.getLocalizedMessage(), AppslistEvent.UPDATE_EVENT);
                    }
                }).build();

        Transaction t2 = database.beginTransactionAsync(categoryProcessModelTransaction)
                .success(new Transaction.Success() {
                    @Override
                    public void onSuccess(Transaction transaction) {
                        updates[1] = true;
                        verifyCompleteUpdate(updates);
                    }
                }).error(new Transaction.Error() {
                    @Override
                    public void onError(Transaction transaction, Throwable error) {
                        error.printStackTrace();
                        post(error.getLocalizedMessage(), AppslistEvent.UPDATE_EVENT);
                    }
                }).build();
        Transaction t3 = database.beginTransactionAsync(appProcessModelTransaction)
                .success(new Transaction.Success() {
                    @Override
                    public void onSuccess(Transaction transaction) {
                        updates[2] = true;
                        verifyCompleteUpdate(updates);
                    }
                }).error(new Transaction.Error() {
                    @Override
                    public void onError(Transaction transaction, Throwable error) {
                        error.printStackTrace();
                        post(error.getLocalizedMessage(), AppslistEvent.UPDATE_EVENT);
                    }
                }).build();
        Transaction t4 = database.beginTransactionAsync(imageProcessModelTransaction)
                .success(new Transaction.Success() {
                    @Override
                    public void onSuccess(Transaction transaction) {
                        updates[3] = true;
                        verifyCompleteUpdate(updates);
                    }
                }).error(new Transaction.Error() {
                    @Override
                    public void onError(Transaction transaction, Throwable error) {
                        error.printStackTrace();
                        post(error.getLocalizedMessage(), AppslistEvent.UPDATE_EVENT);
                    }
                }).build();

        // Ejecutar
        t1.execute();
        t2.execute();
        t3.execute();
        t4.execute();

    }

    private void verifyCompleteUpdate(boolean[] updates) {
        int count_updates = 0;
        for (boolean update : updates) {
            if (update) count_updates++;
        }
        if (count_updates == updates.length) {
            post(AppslistEvent.UPDATE_EVENT);
        }
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
