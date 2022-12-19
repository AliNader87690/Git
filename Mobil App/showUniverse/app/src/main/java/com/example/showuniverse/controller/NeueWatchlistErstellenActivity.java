package com.example.showuniverse.controller;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.showuniverse.R;
import com.example.showuniverse.modul.Benutzer;
import com.example.showuniverse.modul.Container;
import com.example.showuniverse.modul.Watchlist;
import com.example.showuniverse.retrofit.BenutzerApi;
import com.example.showuniverse.retrofit.RetrofitService;
import com.example.showuniverse.retrofit.WatchlistApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NeueWatchlistErstellenActivity extends AppCompatActivity {

    private Button btnSpeichernNeueWatchlist;
    private EditText editTxtNeueListe;
    private SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_neue_liste_erstellen);
        btnSpeichernNeueWatchlist = findViewById(R.id.btnSpeichernNeueWatchlist);
        editTxtNeueListe = findViewById(R.id.editTxtNeueListe);
        sp =  getSharedPreferences("userPrefs",MODE_PRIVATE);


        RetrofitService retrofitService1 = new RetrofitService();
        BenutzerApi benutzerApi = retrofitService1.getRetrofit().create(BenutzerApi.class);
        final Benutzer[] b = new Benutzer[1];
        benutzerApi.getById(sp.getLong("benutzerId", 0l)).enqueue(new Callback<Benutzer>() {
            @Override
            public void onResponse(Call<Benutzer> call, Response<Benutzer> response) {
                b[0] = response.body();
                System.out.println("Benutzer geholt : " + b[0]);
            }

            @Override
            public void onFailure(Call<Benutzer> call, Throwable t) {

            }
        });

        btnSpeichernNeueWatchlist.setOnClickListener(view -> {

            if(editTxtNeueListe.getText().toString().length() > 0) {
                RetrofitService retrofitService = new RetrofitService();
                Watchlist w = new Watchlist();
                w.setTitel(editTxtNeueListe.getText().toString());
                Container c = new Container(b[0], w);
                WatchlistApi watchlistApi = retrofitService.getRetrofit().create(WatchlistApi.class);



                watchlistApi.addWatchlist(c).enqueue(new Callback<Watchlist>() {
                    @Override
                    public void onResponse(Call<Watchlist> call, Response<Watchlist> response) {
                        System.out.println("Erfolg");

                    }

                    @Override
                    public void onFailure(Call<Watchlist> call, Throwable t) {
                        System.out.println("Throw: " + t.getMessage());
                    }
                });
                Intent intent = new Intent(NeueWatchlistErstellenActivity.this, MeineWatchlistenActivity.class);
                startActivity(intent);
            } else {
                Toast.makeText(NeueWatchlistErstellenActivity.this, "Der Watchlistname darf nicht leer sein!", Toast.LENGTH_LONG).show();
            }

        });
    }
}