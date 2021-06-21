package com.example.softxperttask;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
public interface Api {
    @GET("cars")
    Call<CarResponse> getCars(@Query("page") int page);
}