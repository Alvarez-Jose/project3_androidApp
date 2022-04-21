package com.example.project3_androidapp.repos;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.project3_androidapp.apis.SearchService;
import com.example.project3_androidapp.db.UserEntity;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.converter.gson.GsonConverterFactory;

public class UserRepository {
    public static final String SEARCH_SERVICE_BASE_URL = "https://omdbapi.com/";

    private SearchService mSearchService;
    private MutableLiveData<UserEntity> mResponseLiveData;

    public UserRepository() {
        mResponseLiveData = new MutableLiveData<UserEntity>();

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.level(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        // getting Retrofit ready
        mSearchService = new retrofit2.Retrofit.Builder()
                .baseUrl(SEARCH_SERVICE_BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(SearchService.class);
    }

    // search for user by userId
    public void searchUserByUser_Id(String userId) {
        mSearchService.searchUserById(userId)
                .enqueue(new Callback<UserEntity>() {
                    @Override
                    public void onResponse(Call<UserEntity> call, Response<UserEntity> response) {
                        if (response.body() != null) {
                            mResponseLiveData.postValue(response.body());
                            System.out.println(response);
                        }
                    }

                    @Override
                    public void onFailure(Call<UserEntity> call, Throwable t) {
                        mResponseLiveData.postValue(null);
                    }
                });
    }

    // return API response
    public LiveData<UserEntity> getResponseLiveData() {
        return mResponseLiveData;
    }
}
