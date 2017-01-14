package com.grability.rappiitunescatalogo.model.rest.adapters;

import com.google.gson.Gson;
import com.grability.rappiitunescatalogo.domain.entities.Feed;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * Created by wilmer on 13/01/17.
 */

public class FeedInterceptor implements Interceptor {

    public static final MediaType JSON = MediaType.parse("application/json; charset=UTF-8");
    public static final Gson GSON = new Gson();

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Response response = chain.proceed(request);
        final ResponseBody body = response.body();
        System.out.println(body.string());
        Feed apiResponse = GSON.fromJson(body.charStream().toString(), Feed.class);
        body.close();

        final Response.Builder newResponse = response.newBuilder()
                .body(ResponseBody.create(JSON, apiResponse.getAuthor().toString()));
        return newResponse.build();
    }
}
