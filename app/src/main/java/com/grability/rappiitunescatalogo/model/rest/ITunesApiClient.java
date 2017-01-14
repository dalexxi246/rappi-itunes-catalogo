package com.grability.rappiitunescatalogo.model.rest;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by wilmer on 12/01/17.
 */

public class ITunesApiClient {

    public static final String API_BASE_URL = "https://itunes.apple.com/us/rss/";
    private Retrofit retrofit;
//  private OkHttpClient client;

    public ITunesApiClient() {
//      this.client = new OkHttpClient.Builder()
//              .addNetworkInterceptor(new FeedInterceptor())
//              .build();
        this.retrofit = new Retrofit.Builder()
//              .client(client)
                .baseUrl(API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public ITunesApiService getITunesApiService() {
        return retrofit.create(ITunesApiService.class);
    }
}
