package com.example.showuniverse.controller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.showuniverse.R;
import com.example.showuniverse.modul.Benutzer;
import com.example.showuniverse.modul.Profil;
import com.example.showuniverse.retrofit.BenutzerApi;
import com.example.showuniverse.retrofit.RetrofitService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfilBearbeitenActivity extends AppCompatActivity {

    private EditText txtVorname;
    private EditText txtNachname;
    private EditText txtEmail;
    private EditText txtPasswort;
    private EditText txtPasswortCheck;
    private SharedPreferences sp;
    private Benutzer benutzer;
    private Button btnAbbrechenProfilBearbeiten;
    private Button btnSpeichernProfilBearbeiten;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil_bearbeiten);

        sp = getSharedPreferences("userPrefs",MODE_PRIVATE);
        txtVorname = findViewById(R.id.txtVorname);
        txtNachname = findViewById(R.id.txtNachname);
        txtEmail = findViewById(R.id.txtEmail);
        txtPasswort = findViewById(R.id.txtPasswort);
        txtPasswortCheck = findViewById(R.id.txtPasswortCheck);
        btnSpeichernProfilBearbeiten = findViewById(R.id.btnSpeichernProfilBearbeiten);
        btnAbbrechenProfilBearbeiten = findViewById(R.id.btnAbbrechenProfilBearbeiten);


        RetrofitService service = new RetrofitService();
        BenutzerApi benutzerApi = service.getRetrofit().create(BenutzerApi.class);
        
        benutzerApi.getById(sp.getLong("benutzerId", 0L)).enqueue(new Callback<Benutzer>() {
            @Override
            public void onResponse(Call<Benutzer> call, Response<Benutzer> response) {
                benutzer = response.body();

                if(benutzer != null) {
                    txtVorname.setText(benutzer.getVorname());
                    txtNachname.setText(benutzer.getNachname());
                    txtEmail.setText(benutzer.getEmail());
                    txtPasswort.setText(benutzer.getPassword());
                    txtPasswortCheck.setText(benutzer.getPassword());
                }
            }

            @Override
            public void onFailure(Call<Benutzer> call, Throwable t) {

            }
        });


        btnAbbrechenProfilBearbeiten.setOnClickListener(view -> {
            intent = new Intent(this, ProfilActivity.class);
            startActivity(intent);
        });

        btnSpeichernProfilBearbeiten.setOnClickListener(view -> {
            if(!txtPasswort.getText().toString().equals(txtPasswortCheck.getText().toString())){
                Toast.makeText(ProfilBearbeitenActivity.this, "Passwörter stimmen nicht überein!", Toast.LENGTH_LONG).show();
            }else {
                benutzer.setVorname(txtVorname.getText().toString());
                benutzer.setNachname(txtNachname.getText().toString());
                benutzer.setEmail(txtEmail.getText().toString());
                benutzer.setPassword(txtPasswort.getText().toString());
                benutzerApi.addBenutzer(benutzer).enqueue(new Callback<Benutzer>() {
                    @Override
                    public void onResponse(Call<Benutzer> call, Response<Benutzer> response) {
                        Toast.makeText(ProfilBearbeitenActivity.this,"Änderungen wurden erfolgreich gespeichert", Toast.LENGTH_LONG).show();
                        intent = new Intent(ProfilBearbeitenActivity.this, ProfilActivity.class);
                        startActivity(intent);
                    }

                    @Override
                    public void onFailure(Call<Benutzer> call, Throwable t) {
                        Toast.makeText(ProfilBearbeitenActivity.this,"Profil-Änderungen haben fehlgeschlagen!", Toast.LENGTH_LONG).show();
                    }
                });

            }

        });










    }
}