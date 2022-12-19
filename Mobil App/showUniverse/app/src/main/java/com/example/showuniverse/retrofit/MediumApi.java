package com.example.showuniverse.retrofit;

import com.example.showuniverse.modul.Medium;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface MediumApi {

    @GET("/medium/get-all")
    Call<List<Medium>> getAll();
}
