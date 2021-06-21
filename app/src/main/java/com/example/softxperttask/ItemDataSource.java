package com.example.softxperttask;

import androidx.annotation.NonNull;
import androidx.paging.PageKeyedDataSource;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ItemDataSource extends PageKeyedDataSource<Integer, CarItem> {
    private static final int PAGE = 1;

    private static final String SITE_NAME = "stackoverflow";


    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull final LoadInitialCallback<Integer, CarItem> callback) {
        RetrofitHandler.getInstance()
                .getApi().getCars(PAGE)
                .enqueue(new Callback<CarResponse>() {
                    @Override
                    public void onResponse(Call<CarResponse> call, Response<CarResponse> response) {
                        if (response.body() != null) {
                            callback.onResult(response.body().getData(), null, PAGE + 1);
                        }
                    }

                    @Override
                    public void onFailure(Call<CarResponse> call, Throwable t) {

                    }
                });
    }

    @Override
    public void loadBefore(@NonNull final LoadParams<Integer> params, @NonNull final LoadCallback<Integer, CarItem> callback) {
        RetrofitHandler.getInstance()
                .getApi().getCars(PAGE)
                .enqueue(new Callback<CarResponse>() {
                    @Override
                    public void onResponse(Call<CarResponse> call, Response<CarResponse> response) {

                        Integer adjacentKey = (params.key > 1) ? params.key - 1 : null;
                        if (response.body() != null) {
                            callback.onResult(response.body().getData(), adjacentKey);
                        }
                    }

                    @Override
                    public void onFailure(Call<CarResponse> call, Throwable t) {

                    }
                });
    }

    @Override
    public void loadAfter(@NonNull final LoadParams<Integer> params, @NonNull final LoadCallback<Integer, CarItem> callback) {
        RetrofitHandler.getInstance()
                .getApi()
                .getCars(PAGE)
                .enqueue(new Callback<CarResponse>() {
                    @Override
                    public void onResponse(Call<CarResponse> call, Response<CarResponse> response) {

                        if (response.body() != null) {
                            Integer key = response.body().getStatus() != "0" ? params.key + 1 : null;

                            callback.onResult(response.body().getData(), key);
                        }
                    }

                    @Override
                    public void onFailure(Call<CarResponse> call, Throwable t) {

                    }
                });
    }
}