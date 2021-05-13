package com.example.toptenpeliculas.service;

import com.example.toptenpeliculas.model.PeliculaModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface APIService {

    @GET
    Call<PeliculaModel> getPeliculaListInterface(@Url String url);

}
