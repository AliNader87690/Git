package com.example.showuniverse.controller;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import com.example.showuniverse.R;
import com.example.showuniverse.modul.Film;
import com.example.showuniverse.modul.Serie;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class MedienUebersichtActivity extends AppCompatActivity {

    static String jahr;
    private RecyclerView recyclerViewFilmeUebersicht;
    private TextView txtLogoMedienÜbersicht;
    private BottomNavigationView navigationbar;
    private Intent intent;
    static ArrayList<Serie> subListSerien22, subListSerien21;
    static ArrayList<Film> subListFilme22, subListFilme21;
    private FilmRecViewAdapter adapterFilm;
    private SerieRecViewAdapter adapterSerie;
    private SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medien_uebersicht);

        recyclerViewFilmeUebersicht = findViewById(R.id.recyclerViewFilmUebersicht);
        navigationbar = findViewById(R.id.navigationbar);
        txtLogoMedienÜbersicht = findViewById(R.id.txtLogoMedienÜbersicht);
        txtLogoMedienÜbersicht.setText(jahr);
        navigationbar = findViewById(R.id.navigationbar);

        ////
        sp = getApplicationContext().getSharedPreferences("userPrefs",MODE_PRIVATE);
        ////

        navigationbar.setOnItemSelectedListener(item -> {

            switch (item.getItemId()) {
                case R.id.itemHome:
                    intent = new Intent(MedienUebersichtActivity.this, MainActivity.class);
                    startActivity(intent);
                    break;
                case R.id.itemSuche:
                    intent = new Intent(MedienUebersichtActivity.this, SucheActivity.class);
                    startActivity(intent);
                    break;
                case R.id.itemWatchlist:
                    if(sp.getLong("benutzerId", 0l) >  0l){
                        intent = new Intent(MedienUebersichtActivity.this, MeineWatchlistenActivity.class);
                        startActivity(intent);
                        break;
                    }else {
                        intent = new Intent(MedienUebersichtActivity.this, LoginActivity.class);
                        startActivity(intent);
                        break;
                    }
                case R.id.itemProfil:
                    if(sp.getLong("benutzerId", 0l) >  0l){
                        intent = new Intent(MedienUebersichtActivity.this, ProfilActivity.class);
                        startActivity(intent);
                        break;
                    }else {
                        intent = new Intent(MedienUebersichtActivity.this, LoginActivity.class);
                        startActivity(intent);
                        break;
                    }
            }
            return true;
        });
        navigationbar.getMenu().getItem(0).setChecked(true);

        Intent iin = getIntent();
        if(iin.getStringExtra("data").contains("Filme22")){
            adapterFilm = new FilmRecViewAdapter(this);
            adapterFilm.setMedia(subListFilme22);
            recyclerViewFilmeUebersicht.setAdapter(adapterFilm);
            recyclerViewFilmeUebersicht.setLayoutManager(new GridLayoutManager(this, 3));
        }else if(iin.getStringExtra("data").contains("Filme21")){
            adapterFilm = new FilmRecViewAdapter(this);
            adapterFilm.setMedia(subListFilme21);
            recyclerViewFilmeUebersicht.setAdapter(adapterFilm);
            recyclerViewFilmeUebersicht.setLayoutManager(new GridLayoutManager(this, 3));
        }else if(iin.getStringExtra("data").contains("Serien22")) {
            adapterSerie = new SerieRecViewAdapter(this);
            adapterSerie.setMedia(subListSerien22);
            recyclerViewFilmeUebersicht.setAdapter(adapterSerie);
            recyclerViewFilmeUebersicht.setLayoutManager(new GridLayoutManager(this, 3));
        }else if(iin.getStringExtra("data").contains("Serien21")) {
            adapterSerie = new SerieRecViewAdapter(this);
            adapterSerie.setMedia(subListSerien21);
            recyclerViewFilmeUebersicht.setAdapter(adapterSerie);
            recyclerViewFilmeUebersicht.setLayoutManager(new GridLayoutManager(this, 3));
        }else {
            System.out.println("Sonssssst");
        }
    }
}