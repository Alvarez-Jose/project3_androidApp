package com.example.project3_androidapp.repos;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class Repo {
    public static final String SEARCH_SERVICE_BASE_URL = "https://omdbapi.com/";

    private SearchService mSearchService;
    private MutableLiveData<MovieEntity> mResponseLiveData;

    public MovieRepository() {
        mResponseLiveData = new MutableLiveData<MovieEntity>();

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

    // search for movie by imdbId
    public void searchMovieByIMDB_Id(String imdbId) {
        mSearchService.searchValuesByIMDB_Id(imdbId)
                .enqueue(new Callback<MovieEntity>() {
                    @Override
                    public void onResponse(Call<MovieEntity> call, Response<MovieEntity> response) {
                        if (response.body() != null) {
                            mResponseLiveData.postValue(response.body());
                            System.out.println(response);
                        }
                    }

                    @Override
                    public void onFailure(Call<MovieEntity> call, Throwable t) {
                        mResponseLiveData.postValue(null);
                    }
                });
    }

    // return API response
    public LiveData<MovieEntity> getResponseLiveData() {
        return mResponseLiveData;
    }
}
