package com.example.showuniverse.controller;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.showuniverse.R;
import com.example.showuniverse.modul.Film;
import com.example.showuniverse.modul.Serie;
import com.example.showuniverse.retrofit.FilmApi;
import com.example.showuniverse.retrofit.RetrofitService;
import com.example.showuniverse.retrofit.SerieApi;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView1, recyclerView2;
    private BottomNavigationView navigationView, filmSerieNavi;
    private LinearLayout linearLayout2022, linearLayout2021;
    private TextView txtFilmeKategorie2022, txtFilmeKategorie2021;
    private ArrayList<Serie> subListSerien22, subListSerien21;
    private ArrayList<Film> subListFilme22, subListFilme21;
    private Intent intent;
    private SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ///////////////////////////////////
        sp = getApplicationContext().getSharedPreferences("userPrefs", Context.MODE_PRIVATE);
        //////////////////////////////////

        recyclerView1 = findViewById(R.id.recyclerView1);
        recyclerView2 = findViewById(R.id.recyclerView2);
        navigationView = findViewById(R.id.navigationbar);
        filmSerieNavi = findViewById(R.id.fileSerieNavi);
        linearLayout2022 = findViewById(R.id.linearLayoutKategorie2022);
        linearLayout2021 = findViewById(R.id.linearLayoutKategorie2021);
        txtFilmeKategorie2022 = findViewById(R.id.txtFilmeKategorie2022);
        txtFilmeKategorie2021 = findViewById(R.id.txtFilmeKategorie2021);

        navigationView.getMenu().getItem(0).setChecked(true);

        loadFilme();


        navigationView.setOnItemSelectedListener(item -> {

            switch (item.getItemId()) {
                case R.id.itemHome:
                    intent = new Intent(MainActivity.this, MainActivity.class);
                    startActivity(intent);
                    break;
                case R.id.itemSuche:
                    intent = new Intent(MainActivity.this, SucheActivity.class);
                    startActivity(intent);
                    break;
                case R.id.itemWatchlist:
                    if(sp.getLong("benutzerId", 0l) > 0l){
                        intent = new Intent(MainActivity.this, MeineWatchlistenActivity.class);
                        startActivity(intent);
                        break;
                    }else {
                        intent = new Intent(MainActivity.this, LoginActivity.class);
                        startActivity(intent);
                        break;
                    }
                case R.id.itemProfil:
                    if(sp.getLong("benutzerId", 0l) > 0l){
                        intent = new Intent(MainActivity.this, ProfilActivity.class);
                        startActivity(intent);
                        break;
                    }else {
                        intent = new Intent(MainActivity.this, LoginActivity.class);
                        startActivity(intent);
                        break;
                    }

            }
            return true;
        });

        linearLayout2022.setOnClickListener(view -> {
            intent = new Intent(MainActivity.this, MedienUebersichtActivity.class);
            if(txtFilmeKategorie2022.getText().toString().contains("Filme 2022")){
                MedienUebersichtActivity.jahr = txtFilmeKategorie2022.getText().toString();
                MedienUebersichtActivity.subListFilme22 = subListFilme22;
                intent.putExtra("data","Filme22");
                startActivity(intent);
            }else if(txtFilmeKategorie2022.getText().toString().contains("Serien 2022")) {
                MedienUebersichtActivity.jahr = txtFilmeKategorie2022.getText().toString();
                MedienUebersichtActivity.subListSerien22 = subListSerien22;
                intent.putExtra("data","Serien22");
                startActivity(intent);
            }
        });

        linearLayout2021.setOnClickListener(view -> {
            intent = new Intent(MainActivity.this, MedienUebersichtActivity.class);

            if(txtFilmeKategorie2021.getText().toString().contains("Filme 2021")) {
                MedienUebersichtActivity.jahr = txtFilmeKategorie2021.getText().toString();
                MedienUebersichtActivity.subListFilme21 = subListFilme21;
                intent.putExtra("data","Filme21");
                startActivity(intent);
            }else if(txtFilmeKategorie2021.getText().toString().contains("Serien 2021")) {
                MedienUebersichtActivity.jahr = txtFilmeKategorie2021.getText().toString();
                MedienUebersichtActivity.subListSerien21 = subListSerien21;
                intent.putExtra("data","Serien21");
                startActivity(intent);
            }
        });

        filmSerieNavi.setOnItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.fileNavi:
                    txtFilmeKategorie2022.setText("Englisch Filme 2022");
                    txtFilmeKategorie2021.setText("Englisch Filme 2021");
                    loadFilme();
                    break;
                case R.id.serieNavi:
                    txtFilmeKategorie2022.setText("Englisch Serien 2022");
                    txtFilmeKategorie2021.setText("Englisch Serien 2021");
                    loadSerien();
                    break;
                default:
                    break;
            }
            return true;
        });

    }

    private void loadFilme() {
        RetrofitService retrofitService = new RetrofitService();
        FilmApi filmApi = retrofitService.getRetrofit().create(FilmApi.class);
        filmApi.getAll().enqueue(new Callback<List<Film>>() {
            @Override
            public void onResponse(Call<List<Film>> call, Response<List<Film>> response) {
                filmeListView(response.body());
            }

            @Override
            public void onFailure(Call<List<Film>> call, Throwable t) {
                Toast.makeText(MainActivity.this,"Failed",Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void filmeListView(List<Film> body) {
        FilmRecViewAdapter adapter22 = new FilmRecViewAdapter(this);
        FilmRecViewAdapter adapter21 = new FilmRecViewAdapter(this);
        subListFilme22 = new ArrayList<>();
        subListFilme21 = new ArrayList<>();
        for (Film s: body) {
            if(s.getErscheinungsjahr() == 2022){
                subListFilme22.add(s);
            } else if(s.getErscheinungsjahr() == 2021) {
                System.out.println(s);
                subListFilme21.add(s);
            }
        }
        adapter22.setMedia(subListFilme22);
        recyclerView1.setAdapter(adapter22);
        recyclerView1.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));

        adapter21.setMedia(subListFilme21);
        recyclerView2.setAdapter(adapter21);
        recyclerView2.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
    }

    private void loadSerien() {
        RetrofitService retrofitService = new RetrofitService();
        SerieApi serieApi = retrofitService.getRetrofit().create(SerieApi.class);
        serieApi.getAll().enqueue(new Callback<List<Serie>>() {
            @Override
            public void onResponse(Call<List<Serie>> call, Response<List<Serie>> response) {
                serienListView(response.body());
            }

            @Override
            public void onFailure(Call<List<Serie>> call, Throwable t) {
                Toast.makeText(MainActivity.this,"Failed",Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void serienListView(List<Serie> body) {
        SerieRecViewAdapter adapter22 = new SerieRecViewAdapter(this);
        SerieRecViewAdapter adapter21 = new SerieRecViewAdapter(this);
        subListSerien22 = new ArrayList<>();
        subListSerien21 = new ArrayList<>();
        for (Serie s: body) {
           if(s.getErscheinungsjahr() == 2022){
               subListSerien22.add(s);
           } else if(s.getErscheinungsjahr() == 2021) {
               subListSerien21.add(s);
           }
        }
        adapter22.setMedia(subListSerien22);
        recyclerView1.setAdapter(adapter22);
        recyclerView1.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));

        adapter21.setMedia(subListSerien21);
        recyclerView2.setAdapter(adapter21);
        recyclerView2.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
    }
}