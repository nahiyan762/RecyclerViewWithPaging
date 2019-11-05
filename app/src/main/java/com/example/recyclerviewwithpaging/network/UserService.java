package com.example.recyclerviewwithpaging.network;

import androidx.lifecycle.MutableLiveData;

import com.example.recyclerviewwithpaging.api.ApiClient;
import com.example.recyclerviewwithpaging.model.User;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserService {
    private static UserService userService;

    public static UserService getInstance() {
        if(userService == null){
           return userService = new UserService();
        } else {
            return userService;
        }
    }

    public MutableLiveData<List<User>> getUserMutableLiveData(MutableLiveData<List<User>> userMutableLiveData, long userId , int perPage) {
        Call<List<User>> call = ApiClient.getApi().getUsers(userId, perPage);
        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                if (response.isSuccessful()){
                    userMutableLiveData.postValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                List<User> userArrayList = new ArrayList<>();
                userMutableLiveData.postValue(userArrayList);
            }
        });

        return userMutableLiveData;
    }

}
