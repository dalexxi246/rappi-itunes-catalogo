package com.grability.rappiitunescatalogo.model.rest;

import com.grability.rappiitunescatalogo.domain.entities.ApiResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by wilmer on 12/01/17.
 */

public interface ITunesApiService {

    @GET("topfreeapplications/limit={limit}/json")
    Call<ApiResponse> getFeed(@Path("limit") int limit);

}
