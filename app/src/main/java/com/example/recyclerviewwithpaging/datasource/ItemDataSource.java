package com.example.recyclerviewwithpaging.datasource;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.PageKeyedDataSource;

import com.example.recyclerviewwithpaging.api.ApiClient;
import com.example.recyclerviewwithpaging.model.Item;
import com.example.recyclerviewwithpaging.model.StackApiResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ItemDataSource extends PageKeyedDataSource<Integer, Item>  {

    private static final int PAGE_SIZE = 15;
    private static final int PAGE = 1;
    private static final String SITE_NAME = "stackoverflow";


    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull LoadInitialCallback<Integer, Item> callback) {

        ApiClient.getApi().getAnswers(PAGE,PAGE_SIZE, SITE_NAME).enqueue(new Callback<StackApiResponse>() {
            @Override
            public void onResponse(Call<StackApiResponse> call, Response<StackApiResponse> response) {
                if (response.isSuccessful()){
                    callback.onResult(response.body().getItems(), null, PAGE + 1);
                    NetworkState.getInstance().setStatus("LOADED");
                } else {
                    NetworkState.getInstance().setStatus("FAILED");
                }
            }

            @Override
            public void onFailure(Call<StackApiResponse> call, Throwable t) {
                NetworkState.getInstance().setStatus("FAILED");
            }
        });
    }

    @Override
    public void loadBefore(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, Item> callback) {
        ApiClient.getApi().getAnswers(params.key,PAGE_SIZE, SITE_NAME).enqueue(new Callback<StackApiResponse>() {
            @Override
            public void onResponse(Call<StackApiResponse> call, Response<StackApiResponse> response) {

                if (response.isSuccessful()){
                    Integer key = (params.key > 1) ? params.key - 1 : null;
                    callback.onResult(response.body().getItems(), key);
                }
            }

            @Override
            public void onFailure(Call<StackApiResponse> call, Throwable t) {

            }
        });
    }

    @Override
    public void loadAfter(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, Item> callback) {
        ApiClient.getApi().getAnswers(params.key,PAGE_SIZE, SITE_NAME).enqueue(new Callback<StackApiResponse>() {
            @Override
            public void onResponse(Call<StackApiResponse> call, Response<StackApiResponse> response) {
                if (response.isSuccessful()){
                    Integer key = response.body().getHas_more() ? params.key : null;
                    callback.onResult(response.body().getItems(), key);
                    NetworkState.getInstance().setStatus("LOADED");
                } else {
                    NetworkState.getInstance().setStatus("FAILED");
                }
            }

            @Override
            public void onFailure(Call<StackApiResponse> call, Throwable t) {
                NetworkState.getInstance().setStatus("FAILED");
            }
        });
    }
}
