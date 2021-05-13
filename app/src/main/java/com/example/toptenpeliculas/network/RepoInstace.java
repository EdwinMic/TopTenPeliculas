package com.example.toptenpeliculas.network;

import com.example.toptenpeliculas.service.APIService;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RepoInstace {


    public static String BASE_URL = "https://api.themoviedb.org/";



    private static APIService apiService = null;
    public static APIService getListPeliculas() {
        if (apiService == null){
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            apiService = retrofit.create(APIService.class);
        }

        return apiService;

    }

}
