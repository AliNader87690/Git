package com.example.showuniverse.controller;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.showuniverse.R;
import com.example.showuniverse.modul.Film;
import com.example.showuniverse.modul.Medium;
import com.example.showuniverse.modul.MediumInWatchlist;
import com.example.showuniverse.modul.Serie;
import com.example.showuniverse.modul.Watchlist;
import com.example.showuniverse.retrofit.FilmApi;
import com.example.showuniverse.retrofit.MediumApi;
import com.example.showuniverse.retrofit.RetrofitService;
import com.example.showuniverse.retrofit.SerieApi;

import java.util.LinkedList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EineWatchlistActivity extends AppCompatActivity {

    private RecyclerView recyclerViewEineWatchlist;
    private TextView textAnzahlMedien, txtLogoEineWachlist;
    private static List<MediumInWatchlist> watchlistItems;
    private EineWatchlistRecAdapter adapter;
    private ImageView imageViewEditWatchlist;
    private Intent intent;
    static Watchlist watchlist;
    private List<Medium> mediumList = new LinkedList<>();
    private List<Film> films = new LinkedList<>();
    private List<Serie> series = new LinkedList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eine_watchlist);

        recyclerViewEineWatchlist = findViewById(R.id.recyclerViewEineWachlist);
        textAnzahlMedien = findViewById(R.id.textAnzahlMedien);
        txtLogoEineWachlist = findViewById(R.id.txtLogoEineWatchlist);
        imageViewEditWatchlist = findViewById(R.id.imageViewEditWatchlist);


        imageViewEditWatchlist.setOnClickListener(view -> {
            intent = new Intent(this, WatchlisteBearbeitenActivity.class);
            intent.putExtra("watchlistName", txtLogoEineWachlist.getText().toString());
            intent.putExtra("watchlistId", watchlist.getWatchlistId());
            startActivity(intent);
        });

        watchlistItems = watchlist.getMediumInWatchlists();

        RetrofitService service = new RetrofitService();
        FilmApi filmApi = service.getRetrofit().create(FilmApi.class);
        filmApi.getAll().enqueue(new Callback<List<Film>>() {
            @Override
            public void onResponse(Call<List<Film>> call, Response<List<Film>> response) {
                films = response.body();
                SerieApi serieApi = service.getRetrofit().create(SerieApi.class);
                serieApi.getAll().enqueue(new Callback<List<Serie>>() {
                    @Override
                    public void onResponse(Call<List<Serie>> call, Response<List<Serie>> response) {

                        series = response.body();
                        sortMedien(films, series);

                        adapter = new EineWatchlistRecAdapter(EineWatchlistActivity.this, watchlist, watchlistItems, mediumList);
                        txtLogoEineWachlist.setText(watchlist.getTitel());
                        adapter.setMedia(watchlistItems);

                        recyclerViewEineWatchlist.setAdapter(adapter);
                        recyclerViewEineWatchlist.setLayoutManager(new LinearLayoutManager(EineWatchlistActivity.this, RecyclerView.VERTICAL, false));

                        System.out.println("Watchlist Medien: " + watchlistItems);

                    }

                    @Override
                    public void onFailure(Call<List<Serie>> call, Throwable t) {
                        System.out.println("Serien nicht gefunden");
                    }
                });
            }

            @Override
            public void onFailure(Call<List<Film>> call, Throwable t) {
                System.out.println("Filme nicht gefunden");
            }
        });

        textAnzahlMedien.setText(String.valueOf(watchlistItems.size() + " Elemente"));


    }


    public void sortMedien(List<Film> films, List<Serie> series) {
        for(MediumInWatchlist ml : watchlistItems) {
            for(Film f : films) {
                if(ml.getMediumId().equals(f.getId())){
                    mediumList.add(f);
                }
            }

            for(Serie s: series ) {
                if(ml.getMediumId().equals(s.getId())){
                    mediumList.add(s);
                }
            }
        }
    }

}