package com.example.showuniverse.controller;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.showuniverse.R;
import com.example.showuniverse.modul.Film;
import com.example.showuniverse.modul.Medium;
import com.example.showuniverse.modul.Serie;
import com.example.showuniverse.retrofit.FilmApi;
import com.example.showuniverse.retrofit.RetrofitService;
import com.example.showuniverse.retrofit.SerieApi;

import java.util.LinkedList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SucheActivity extends AppCompatActivity {

    private ImageView btnImageSuche;
    private EditText editTxtSuchen;
    private List<Medium> mediumList, suchList;
    private RecyclerView recyclerViewSuche;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suche);
        editTxtSuchen = findViewById(R.id.editTxtSuchen);
        btnImageSuche = findViewById(R.id.btnImageSuche);
        recyclerViewSuche = findViewById(R.id.recyclerViewSuche);
        mediumList = new LinkedList<>();
        suchList = new LinkedList<>();

        RetrofitService service = new RetrofitService();
        FilmApi filmApi = service.getRetrofit().create(FilmApi.class);
        SerieApi serieApi = service.getRetrofit().create(SerieApi.class);
        filmApi.getAll().enqueue(new Callback<List<Film>>() {
            @Override
            public void onResponse(Call<List<Film>> call, Response<List<Film>> response) {
                for(Film f: response.body()) {
                    mediumList.add(f);
                }

                serieApi.getAll().enqueue(new Callback<List<Serie>>() {
                    @Override
                    public void onResponse(Call<List<Serie>> call, Response<List<Serie>> response) {
                        for(Serie s: response.body()) {
                            mediumList.add(s);
                        }


                    }

                    @Override
                    public void onFailure(Call<List<Serie>> call, Throwable t) {
                        System.out.println("Serien Suche fehler");
                    }
                });
            }

            @Override
            public void onFailure(Call<List<Film>> call, Throwable t) {
                System.out.println("Such Film Fehler");
            }
        });



        btnImageSuche.setOnClickListener(view -> {
            suchList.clear();
            if(editTxtSuchen.getText().toString().length() != 0) {
                for(Medium m: mediumList) {
                    if(m.getTitel().contains(editTxtSuchen.getText().toString())) {
                        suchList.add(m);
                    }
                }
                if(suchList.size() > 0) {
                    System.out.println("Suchlist: " + suchList);
                    MediumRecAdapter adapter = new MediumRecAdapter(SucheActivity.this);
                    adapter.setMedia(suchList);
                    recyclerViewSuche.setAdapter(adapter);
                    recyclerViewSuche.setLayoutManager(new LinearLayoutManager(SucheActivity.this,RecyclerView.VERTICAL,false));
                } else {
                    Toast.makeText(SucheActivity.this, "Das gesuchte Element \"" + editTxtSuchen.getText().toString() + "\" existiert nicht!", Toast.LENGTH_LONG ).show();
                }


            } else {
                Toast.makeText(this,"Die Suche ist leer!!", Toast.LENGTH_SHORT).show();
            }
        });

    }
}