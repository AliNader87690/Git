package com.example.showuniverse.retrofit;

import com.example.showuniverse.modul.Container;
import com.example.showuniverse.modul.SpecialContainer;
import com.example.showuniverse.modul.UpdateWatchlistContainer;
import com.example.showuniverse.modul.Watchlist;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface WatchlistApi {


    @GET("/watchlist/get-all")
    Call<List<Watchlist>> getAll();

    @POST("/watchlist/getById")
    Call<Watchlist> getById(@Body Long id);

    @POST("/watchlist/add")
    Call<Watchlist> addWatchlist(@Body Container container);

    @POST("/watchlist/addMedium")
    Call<SpecialContainer> addMediumInWatchlist(@Body SpecialContainer container);

    @POST("/watchlist/delete")
    Call<Integer> deleteWatchlist(@Body Long id);

    @POST("/watchlist/deleteItem")
    Call<Integer> deleteItem(@Body Long mediumInWatchlistId);

    @POST("/watchlist/update")
    Call<Watchlist> updateWatchlist(@Body UpdateWatchlistContainer container);

}
