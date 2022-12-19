package com.example.showuniverse.controller;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.showuniverse.R;
import com.example.showuniverse.modul.Benutzer;
import com.example.showuniverse.modul.Medium;
import com.example.showuniverse.modul.MediumInWatchlist;
import com.example.showuniverse.modul.Watchlist;
import com.example.showuniverse.retrofit.BenutzerApi;
import com.example.showuniverse.retrofit.RetrofitService;

import java.util.LinkedList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddMediumToWatchlistActivity extends AppCompatActivity {

    private ImageView imageView;
    private TextView txtBewertung, txtLogoMediumInformationen;
    private Button btnNeueWatchlistEinfuegen;
    private ConstraintLayout layoutZurWatchlistAdd;
    private RecyclerView recViewWatchlisten;
    private AddToWatcglistAdapter adapter;
    private List<Watchlist> watchlists = new LinkedList<>();
    private Intent intent;
    static Medium neuMedium;
    static Benutzer benutzer;
    private boolean enthalten = false;
    private SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_medium_to_watchlist);
        imageView = findViewById(R.id.imageView);
        txtBewertung = findViewById(R.id.txtBewertung);
        btnNeueWatchlistEinfuegen = findViewById(R.id.btnNeueWatchlistEinfuegen);
        layoutZurWatchlistAdd = findViewById(R.id.layoutZurWatchlistAdd);
        txtLogoMediumInformationen = findViewById(R.id.txtLogoMediumInformationen);
        recViewWatchlisten = findViewById(R.id.recViewWatchlisten);
        //////
         sp = getApplicationContext().getSharedPreferences("userPrefs", Context.MODE_PRIVATE);
         ///////

        getBenutzer(sp.getLong("benutzerId",0l));

        txtLogoMediumInformationen.setText(neuMedium.getTitel());
        txtBewertung.setText("IMDB: " + neuMedium.getBewertung());
        Glide.with(this).asBitmap().load(neuMedium.getImageUrl()).into(imageView);

        btnNeueWatchlistEinfuegen.setOnClickListener(view -> {
                intent = new Intent(this, NeueWatchlistErstellenActivity.class);
                startActivity(intent);
        });

        layoutZurWatchlistAdd.setOnClickListener(view -> {
            intent = new Intent(this, NeueWatchlistErstellenActivity.class);
            startActivity(intent);
        });


        loadWatchlist();

    }

    private void loadWatchlist(){
        RetrofitService retrofitService = new RetrofitService();
        BenutzerApi api = retrofitService.getRetrofit().create(BenutzerApi.class);
        api.getById(sp.getLong("benutzerId",0l)).enqueue(new Callback<Benutzer>() {
            @Override
            public void onResponse(Call<Benutzer> call, Response<Benutzer> response) {

                if(response.body().getWatchlisten() != null){
                    for (Watchlist w: response.body().getWatchlisten()){
                        enthalten = false;
                        for(MediumInWatchlist m: w.getMediumInWatchlists()){
                            if(m.getMediumId().equals(neuMedium.getId())){
                                enthalten = true;
                            }
                        }
                        if(!enthalten){
                            watchlists.add(w);
                        }

                    }
                }
                adapter = new AddToWatcglistAdapter(AddMediumToWatchlistActivity.this);
                adapter.setWatchlisten(watchlists);
                recViewWatchlisten.setAdapter(adapter);
                recViewWatchlisten.setLayoutManager(new LinearLayoutManager(AddMediumToWatchlistActivity.this, RecyclerView.VERTICAL, false));

            }

            @Override
            public void onFailure(Call<Benutzer> call, Throwable t) {

            }
        });
    }

    public void getBenutzer(Long id){
        RetrofitService retrofitService = new RetrofitService();
        BenutzerApi benutzerApi = retrofitService.getRetrofit().create(BenutzerApi.class);
        benutzerApi.getById(id).enqueue(new Callback<Benutzer>() {
            @Override
            public void onResponse(Call<Benutzer> call, Response<Benutzer> response) {
                benutzer = response.body();
            }

            @Override
            public void onFailure(Call<Benutzer> call, Throwable t) {
                System.out.println("Throw: " + t.getMessage());
            }
        });
    }

}