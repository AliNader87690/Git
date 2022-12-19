package com.example.showuniverse.retrofit;

import java.util.List;

import com.example.showuniverse.modul.Benutzer;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface BenutzerApi {

    @GET("/benutzer/get-all")
    Call<List<Benutzer>> getAll();

    @POST("/benutzer/add/")
    Call<Benutzer> addBenutzer(@Body Benutzer benutzer);

    @POST("/benutzer/delete")
    Call<Benutzer> deleteBenutzer(@Body Benutzer benutzer);

    @POST("/benutzer/getById")
    Call<Benutzer> getById(@Body Long id);

}
