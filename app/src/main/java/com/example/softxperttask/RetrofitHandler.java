package com.example.softxperttask;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitHandler {
    private static final String BASE_URL = "https://demo1585915.mockable.io/api/v1/";
    private static RetrofitHandler mInstance;
    private Retrofit retrofit;


    private RetrofitHandler() {
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static synchronized RetrofitHandler getInstance() {
        if (mInstance == null) {
            mInstance = new RetrofitHandler();
        }
        return mInstance;
    }

    public Api getApi() {
        return retrofit.create(Api.class);
    }
}

