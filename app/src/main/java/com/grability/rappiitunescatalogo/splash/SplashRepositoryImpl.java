package com.grability.rappiitunescatalogo.splash;

import com.grability.rappiitunescatalogo.appslist.event.AppslistEvent;
import com.grability.rappiitunescatalogo.domain.entities.ApiResponse;
import com.grability.rappiitunescatalogo.domain.entities.Entry;
import com.grability.rappiitunescatalogo.domain.entities.ImImage;
import com.grability.rappiitunescatalogo.libs.base.EventBus;
import com.grability.rappiitunescatalogo.model.db.tables.App;
import com.grability.rappiitunescatalogo.model.db.tables.AppImage;
import com.grability.rappiitunescatalogo.model.db.tables.Artist;
import com.grability.rappiitunescatalogo.model.db.tables.Category;
import com.grability.rappiitunescatalogo.model.rest.ITunesApiService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by wilmer on 15/01/17.
 */

public class SplashRepositoryImpl implements SplashRepository {

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

    public void updateAppsFromAPI(ApiResponse apiResponse) {
        for (Entry e : apiResponse.getFeed().getEntry()) {

            Artist artist = new Artist();
            artist.setHref(e.getImArtist().getAttributes().getHref());
            artist.setName(e.getImArtist().getLabel());
            artist.save();

            Category category = new Category();
            category.setId(e.getCategory().getAttributes().getImId());
            category.setLabel(e.getCategory().getAttributes().getLabel());
            category.setScheme(e.getCategory().getAttributes().getScheme());
            category.setTerm(e.getCategory().getAttributes().getTerm());
            category.save();

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
            if (appEntry.save()) {
                for (ImImage imImage : e.getImImage()) {
                    AppImage image = new AppImage();
                    image.setApp(appEntry);
                    image.setHeight(imImage.getAttributes().getHeight());
                    image.setSource(imImage.getLabel());
                    if (!image.exists()) {
                        image.save();
                    }
                }
            }
        }
        post(AppslistEvent.UPDATE_EVENT);
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
