package com.grability.rappiitunescatalogo.model.rest;

import com.grability.rappiitunescatalogo.domain.entities.ApiResponse;

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
//        OkHttpClient cl = new OkHttpClient();
//        Request rq = new Request.Builder()
//                .url("https://itunes.apple.com/us/rss/topfreeapplications/limit=20/json")
//                .build();
//
//        cl.newCall(rq).enqueue(new okhttp3.Callback() {
//            @Override
//            public void onFailure(okhttp3.Call call, IOException e) {
//                e.printStackTrace();
//            }
//
//            @Override
//            public void onResponse(okhttp3.Call call, okhttp3.Response response) throws IOException {
//                if (response.isSuccessful()) {
//                    Gson gson = new Gson();
//                    String str_response = response.body().string();
//                    ApiResponse feed = gson.fromJson(str_response, ApiResponse.class);
//                    System.out.println(feed.toString());
//                } else {
//                    System.out.println("Fallo");
//                }
//            }
//        });
//
        TestAPI testAPI = new TestAPI();
        testAPI.getList(20);
    }

    private void getList(int limit) {
        Call<ApiResponse> call = service.getFeed(limit);
        call.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                ApiResponse f = response.body();
                if (f != null) {
                    System.out.println(f.getFeed().getEntry().get(8).getTitle().getLabel());
                } else {
                    System.out.println("Nothing");
                }
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                System.out.println(t.getLocalizedMessage());
            }
        });
    }
}
