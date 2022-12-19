package com.example.showuniverse.controller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.showuniverse.R;
import com.example.showuniverse.modul.Benutzer;
import com.example.showuniverse.modul.Container;
import com.example.showuniverse.modul.UpdateWatchlistContainer;
import com.example.showuniverse.modul.Watchlist;
import com.example.showuniverse.retrofit.BenutzerApi;
import com.example.showuniverse.retrofit.RetrofitService;
import com.example.showuniverse.retrofit.WatchlistApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WatchlisteBearbeitenActivity extends AppCompatActivity {

    private EditText editTxtAktuellerListName;
    private Button btnListSpeichern, btnListLoeschen;
    private Intent intent;
    private Watchlist watchlist;
    private Benutzer benutzer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste_bearbeiten);
        editTxtAktuellerListName = findViewById(R.id.editTxtAktuellerListName);
        btnListSpeichern = findViewById(R.id.btnListSpeichern);
        btnListLoeschen = findViewById(R.id.btnListLoeschen);
        intent = getIntent();
        editTxtAktuellerListName.setText(intent.getStringExtra("watchlistName"));


        RetrofitService service = new RetrofitService();
        WatchlistApi watchlistApi = service.getRetrofit().create(WatchlistApi.class);
        watchlistApi.getById(intent.getLongExtra("watchlistId", 0l)).enqueue(new Callback<Watchlist>() {
            @Override
            public void onResponse(Call<Watchlist> call, Response<Watchlist> response) {
                watchlist = response.body();
            }

            @Override
            public void onFailure(Call<Watchlist> call, Throwable t) {

            }
        });

        BenutzerApi  benutzerApi = service.getRetrofit().create(BenutzerApi.class);
        benutzerApi.getById(getApplicationContext().getSharedPreferences("userPrefs",MODE_PRIVATE).getLong("benutzerId",0L)).enqueue(new Callback<Benutzer>() {
            @Override
            public void onResponse(Call<Benutzer> call, Response<Benutzer> response) {
                benutzer = response.body();
            }

            @Override
            public void onFailure(Call<Benutzer> call, Throwable t) {

            }
        });


        btnListSpeichern.setOnClickListener(view -> {
            watchlist.setTitel(editTxtAktuellerListName.getText().toString());
            watchlistApi.updateWatchlist(new UpdateWatchlistContainer(benutzer.getId(), watchlist.getWatchlistId(),editTxtAktuellerListName.getText().toString())).enqueue(new Callback<Watchlist>() {
                @Override
                public void onResponse(Call<Watchlist> call, Response<Watchlist> response) {
                    System.out.println("Resturn " + response.body());
                }

                @Override
                public void onFailure(Call<Watchlist> call, Throwable t) {

                }
            });
            intent = new Intent(this,MeineWatchlistenActivity.class);
            startActivity(intent);
        });

        btnListLoeschen.setOnClickListener(view -> {
            watchlistApi.deleteWatchlist(intent.getLongExtra("watchlistId",0l)).enqueue(new Callback<Integer>() {
                @Override
                public void onResponse(Call<Integer> call, Response<Integer> response) {
                    System.out.println("DELETE erfolg ");
                }

                @Override
                public void onFailure(Call<Integer> call, Throwable t) {

                }
            });
            intent = new Intent(WatchlisteBearbeitenActivity.this,MeineWatchlistenActivity.class);
            startActivity(intent);
        });

    }
}