package com.example.showuniverse.retrofit;

import com.example.showuniverse.modul.Serie;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface SerieApi {

    @GET("/serie/get-all")
    Call<List<Serie>> getAll();

    @POST("/serie/add")
    Call<Serie> addSerie(@Body Serie serie);

    @POST("/serie/delete")
    Call<Serie> deleteSerie(@Body Serie serie);

}
