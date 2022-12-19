package com.example.showuniverse.controller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.showuniverse.R;
import com.example.showuniverse.modul.Benutzer;
import com.example.showuniverse.retrofit.BenutzerApi;
import com.example.showuniverse.retrofit.RetrofitService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private TextView txtKontoErstellen;
    private EditText editTxtBenutzername, editTextTxtPasswort;
    private Button btnAnmelden;
    private Benutzer b;
    private SharedPreferences sp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anmleden);
        txtKontoErstellen = findViewById(R.id.txtKontoErstellen);
        editTxtBenutzername = findViewById(R.id.editTxtBenutzername);
        editTextTxtPasswort = findViewById(R.id.editTextTxtPasswort);
        btnAnmelden = findViewById(R.id.btnAnmelden);


        txtKontoErstellen.setOnClickListener(view -> {
            Intent intent = new Intent(LoginActivity.this, RegistrierungActivity.class);
            onPause();
            startActivity(intent);
        });

        btnAnmelden.setOnClickListener(view -> {
            loadBenutzer();
        });

    }

    private void loadBenutzer() {
        RetrofitService retrofitService = new RetrofitService();
        BenutzerApi benutzerApi = retrofitService.getRetrofit().create(BenutzerApi.class);
        benutzerApi.getAll().enqueue(new Callback<List<Benutzer>>() {
            @Override
            public void onResponse(Call<List<Benutzer>> call, Response<List<Benutzer>> response) {
                b = benutzerListView(response.body());
                if(b != null){
                    sp = getSharedPreferences("userPrefs", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sp.edit();
                    editor.putLong("benutzerId",b.getId());
                    editor.commit();
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    onDestroy();
                    startActivity(intent);
                } else {
                    Toast.makeText(LoginActivity.this,"Benutzername oder Passwort ungültig",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<List<Benutzer>> call, Throwable t) {
                Toast.makeText(LoginActivity.this,"Anmelden Fehlgeschlagen",Toast.LENGTH_SHORT).show();
            }
        });
    }

    private Benutzer benutzerListView(List<Benutzer> body) {
        for (Benutzer b: body) {
            if(b.getEmail().equalsIgnoreCase(editTxtBenutzername.getText().toString()) && b.getPassword().equals(editTextTxtPasswort.getText().toString())) {
                return  b;
            }
        }
        return  null;
    }


}