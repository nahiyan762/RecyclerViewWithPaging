package com.example.recyclerviewwithpaging.api;

import androidx.lifecycle.MutableLiveData;

import com.example.recyclerviewwithpaging.model.StackApiResponse;
import com.example.recyclerviewwithpaging.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Api {
    @GET("/users")
    Call<List<User>> getUsers(@Query("since") long userId, @Query("per_page") int perPage);

    @GET("answers")
    Call<StackApiResponse> getAnswers(@Query("page") int page, @Query("pagesize") int pageSize, @Query("site") String site);

}
