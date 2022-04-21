package com.example.project3_androidapp.apis;

import com.example.project3_androidapp.db.UserEntity;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface  SearchService {

//    @GET("?")
//    Call<APIValues> searchValues(
//            @Query("s") String title
//    );

    @GET("?")
    Call<UserEntity> searchUserById(
            @Query("userId") String userId
    );
}
