package com.example.showuniverse.controller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.showuniverse.R;
import com.example.showuniverse.modul.Benutzer;
import com.example.showuniverse.retrofit.BenutzerApi;
import com.example.showuniverse.retrofit.RetrofitService;
import com.example.showuniverse.retrofit.WatchlistApi;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfilActivity extends AppCompatActivity {

    private BottomNavigationView navigationbar;
    private ImageView imageViewEditWatchlist;
    private Intent intent;
    private TextView txtVornameProfil;
    private TextView txtNachnameProfil;
    private TextView txtEmailProfil;
    private TextView txtPasswortProfil;
    private Button btnAbmelden;
    private SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);

        navigationbar = findViewById(R.id.navigationbar);
        imageViewEditWatchlist =  findViewById(R.id.imageViewEditWatchlist);
        txtVornameProfil = findViewById(R.id.txtVornameProfil);
        txtNachnameProfil = findViewById(R.id.txtNachnameProfil);
        txtEmailProfil = findViewById(R.id.txtEmailProfil);
        txtPasswortProfil = findViewById(R.id.txtPasswortProfil);
        btnAbmelden = findViewById(R.id.btnAbmelden);
        sp = getApplicationContext().getSharedPreferences("userPrefs", Context.MODE_PRIVATE);


        RetrofitService retrofit = new RetrofitService();
        WatchlistApi api1 = retrofit.getRetrofit().create(WatchlistApi.class);
        BenutzerApi   api = retrofit.getRetrofit().create(BenutzerApi.class);


        api.getById(sp.getLong("benutzerId",0l)).enqueue(new Callback<Benutzer>() {
            @Override
            public void onResponse(Call<Benutzer> call, Response<Benutzer> response) {
                txtVornameProfil.setText(response.body().getVorname());
                txtNachnameProfil.setText(response.body().getNachname());
                txtEmailProfil.setText(response.body().getEmail());
                txtPasswortProfil.setText(response.body().getPassword());
            }

            @Override
            public void onFailure(Call<Benutzer> call, Throwable t) {
                Toast.makeText(ProfilActivity.this,"Profil failed",Toast.LENGTH_SHORT).show();
            }
        });

        btnAbmelden.setOnClickListener(view -> {

            SharedPreferences.Editor editor = sp.edit();
            editor.putLong("benutzerId",0L);
            editor.commit();
            intent = new Intent(this, MainActivity.class);
            onPause();
            startActivity(intent);

        });

        imageViewEditWatchlist.setOnClickListener(view -> {
            intent = new Intent(this, ProfilBearbeitenActivity.class);
            startActivity(intent);
        });

        navigationbar.setOnItemSelectedListener(item -> {

            switch (item.getItemId()) {
                case R.id.itemHome:
                    intent = new Intent(ProfilActivity.this, MainActivity.class);
                    startActivity(intent);
                    break;
                case R.id.itemSuche:
                    intent = new Intent(ProfilActivity.this, SucheActivity.class);
                    startActivity(intent);
                    break;
                case R.id.itemWatchlist:
                    if(sp.getLong("benutzerId", 0l) >  0l){
                        intent = new Intent(ProfilActivity.this, MeineWatchlistenActivity.class);
                        startActivity(intent);
                        break;
                    }else {
                        intent = new Intent(ProfilActivity.this, LoginActivity.class);
                        startActivity(intent);
                        break;
                    }
                case R.id.itemProfil:
                    if(sp.getLong("benutzerId", 0l) > 0l){
                        intent = new Intent(ProfilActivity.this, ProfilActivity.class);
                        startActivity(intent);
                        break;
                    }else {
                        intent = new Intent(ProfilActivity.this, LoginActivity.class);
                        startActivity(intent);
                        break;
                    }
            }
            return true;
        });
        navigationbar.getMenu().getItem(3).setChecked(true);





    }
}