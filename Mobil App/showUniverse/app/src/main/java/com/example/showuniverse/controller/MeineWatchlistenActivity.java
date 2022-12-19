package com.example.showuniverse.controller;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.example.showuniverse.R;
import com.example.showuniverse.modul.Benutzer;
import com.example.showuniverse.modul.Watchlist;
import com.example.showuniverse.retrofit.BenutzerApi;
import com.example.showuniverse.retrofit.RetrofitService;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.LinkedList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MeineWatchlistenActivity extends AppCompatActivity {

    private RecyclerView recViewWatchlisten;
    private Button btnNeueWatchlistEinfuegen;
    private TextView txtAnzahlWatchlisten;
    private MeineWatchlistenRecAdapter adapter;
    private BottomNavigationView navigationbar;
    private List<Watchlist> secondList = new LinkedList<>();
    private Intent intent;
    private  SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meine_watchlisten);
        txtAnzahlWatchlisten= findViewById(R.id.txtAnzahlWatchlisten);
        recViewWatchlisten = findViewById(R.id.recViewWatchlisten);
        btnNeueWatchlistEinfuegen = findViewById(R.id.btnNeueWatchlistEinfuegen);
        navigationbar = findViewById(R.id.navigationbar);
        sp = getApplicationContext().getSharedPreferences("userPrefs", Context.MODE_PRIVATE);

        navigationbar.setOnItemSelectedListener(item -> {

            switch (item.getItemId()) {
                case R.id.itemHome:
                    intent = new Intent(MeineWatchlistenActivity.this, MainActivity.class);
                    startActivity(intent);
                    break;
                case R.id.itemSuche:
                    intent = new Intent(MeineWatchlistenActivity.this, SucheActivity.class);
                    startActivity(intent);
                    break;
                case R.id.itemWatchlist:
                    if(sp.getLong("benutzerId", 0l) > 0l){
                        intent = new Intent(MeineWatchlistenActivity.this, MeineWatchlistenActivity.class);
                        startActivity(intent);
                        break;
                    }else {
                        intent = new Intent(MeineWatchlistenActivity.this, LoginActivity.class);
                        startActivity(intent);
                        break;
                    }
                case R.id.itemProfil:
                    if(sp.getLong("benutzerId", 0l) > 0l){
                        intent = new Intent(MeineWatchlistenActivity.this, ProfilActivity.class);
                        startActivity(intent);
                        break;
                    }else {
                        intent = new Intent(MeineWatchlistenActivity.this, LoginActivity.class);
                        startActivity(intent);
                        break;
                    }
            }
            return true;
        });

        navigationbar.getMenu().getItem(2).setChecked(true);

        btnNeueWatchlistEinfuegen.setOnClickListener(view -> {
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
                System.out.println("Benutzer Watchlist: " + response.body());
                if(response.body().getWatchlisten() != null){
                    for (Watchlist w: response.body().getWatchlisten()){
                        secondList.add(w);
                    }
                }

                txtAnzahlWatchlisten.setText(String.valueOf(secondList.size()) + " Watchlisten");
                adapter = new MeineWatchlistenRecAdapter(MeineWatchlistenActivity.this, secondList);
                recViewWatchlisten.setAdapter(adapter);
                recViewWatchlisten.setLayoutManager(new LinearLayoutManager(MeineWatchlistenActivity.this,RecyclerView.VERTICAL, false));

            }

            @Override
            public void onFailure(Call<Benutzer> call, Throwable t) {

            }
        });
    }
}





