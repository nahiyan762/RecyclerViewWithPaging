package com.example.recyclerviewwithpaging.network;

import androidx.lifecycle.MutableLiveData;

import com.example.recyclerviewwithpaging.api.ApiClient;
import com.example.recyclerviewwithpaging.model.StackApiResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StackOverFlowService {
    private static StackOverFlowService stackOverFlowService;

    public static StackOverFlowService getInstance() {
        if(stackOverFlowService == null){
            return stackOverFlowService = new StackOverFlowService();
        } else {
            return stackOverFlowService;
        }
    }

    public MutableLiveData<StackApiResponse> getStackApiResponse(MutableLiveData<StackApiResponse> apiResponseMutableLiveData, int page, int pageSize, String site) {
        Call<StackApiResponse> call = ApiClient.getApi().getAnswers(page, pageSize, site);
        call.enqueue(new Callback<StackApiResponse>() {
            @Override
            public void onResponse(Call<StackApiResponse> call, Response<StackApiResponse> response) {
                if (response.isSuccessful()) {
                    apiResponseMutableLiveData.postValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<StackApiResponse> call, Throwable t) {
                StackApiResponse stackApiResponse = new StackApiResponse("0", "0", false, new ArrayList<>());
                apiResponseMutableLiveData.postValue(stackApiResponse);
            }
        });

        return apiResponseMutableLiveData;
    }
}
