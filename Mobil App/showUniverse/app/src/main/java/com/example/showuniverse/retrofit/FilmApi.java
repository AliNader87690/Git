package com.example.showuniverse.retrofit;

import java.util.List;

import com.example.showuniverse.modul.Film;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface FilmApi {
    @GET("/film/get-all")
    Call<List<Film>> getAll();

    @POST("/film/add")
    Call<Film> addFilm(@Body Film film);

    @POST("/film/delete")
    Call<Film> deleteFilm(@Body Film film);

}