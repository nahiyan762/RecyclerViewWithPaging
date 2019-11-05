package com.example.recyclerviewwithpaging.api;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    private static Retrofit retrofit = null;
    private static final int REQUEST_TIMEOUT = 2;
    private static final String BASE_URL_GITHUB_API = "https://api.github.com/";
    private static final String BASE_URL_STACK_OVERFLOW_API = "https://api.stackexchange.com/2.2/";

    private static Retrofit getApiClient() {
        if (retrofit == null) {
            OkHttpClient.Builder okHttpClientBuilder = new OkHttpClient.Builder();
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
            okHttpClientBuilder.addInterceptor(logging);

            okHttpClientBuilder
                    .connectTimeout(REQUEST_TIMEOUT, TimeUnit.MINUTES)
                    .readTimeout(REQUEST_TIMEOUT, TimeUnit.MINUTES)
                    .writeTimeout(REQUEST_TIMEOUT, TimeUnit.MINUTES)
                    .build();

            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL_STACK_OVERFLOW_API)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(okHttpClientBuilder.build())
                    .build();
        }

        return retrofit;
    }

    public static Api getApi() {
        return ApiClient.getApiClient().create(Api.class);
    }
}
