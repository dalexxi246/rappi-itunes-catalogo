package com.grability.rappiitunescatalogo.model.rest;

import com.grability.rappiitunescatalogo.domain.entities.Feed;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by wilmer on 12/01/17.
 */

public class TestAPI {

    ITunesApiService service;
    ITunesApiClient client;

    public TestAPI() {
        // Impl - attributes
        this.client = new ITunesApiClient();
        this.service = this.client.getITunesApiService();
    }

    public static void main(String... args) {
        System.out.println("aaaaaaaaaaaaaaaa");
        TestAPI testAPI = new TestAPI();
        testAPI.getList(20);
    }

    private void getList(int limit) {
        Call<Feed> call = service.getFeed(limit);
        call.enqueue(new Callback<Feed>() {
            @Override
            public void onResponse(Call<Feed> call, Response<Feed> response) {
                Feed f = response.body();
                if (f != null) {
                    System.out.println(f);
                } else {
                    System.out.println("Nothing");
                }
            }

            @Override
            public void onFailure(Call<Feed> call, Throwable t) {
                System.out.println(t.getLocalizedMessage());
            }
        });
    }
}
