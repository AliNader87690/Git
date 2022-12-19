package com.example.showuniverse.controller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.showuniverse.R;
import com.example.showuniverse.modul.Benutzer;
import com.example.showuniverse.retrofit.BenutzerApi;
import com.example.showuniverse.retrofit.RetrofitService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegistrierungActivity extends AppCompatActivity {

    private EditText editTxtVorname;
    private EditText editTxtNachname;
    private EditText editTxtEmail;
    private EditText editTxtPasswrot;
    private EditText editTxtPasswrotBestaetigen;
    private Button btnRegistrieren;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrierung);

        editTxtVorname = findViewById(R.id.editTxtVorname);
        editTxtNachname = findViewById(R.id.editTxtNachname);
        editTxtEmail = findViewById(R.id.editTxtEmail);
        editTxtPasswrot = findViewById(R.id.editTxtPasswrot);
        editTxtPasswrotBestaetigen = findViewById(R.id.editTxtPasswrotBestaetigen);
        btnRegistrieren = findViewById(R.id.btnRegistrieren);


        btnRegistrieren.setOnClickListener(view -> {
            if(!editTxtPasswrot.getText().toString().equals(editTxtPasswrotBestaetigen.getText().toString())) {
                Toast.makeText(RegistrierungActivity.this, "Passwörter stimmen nicht überein!", Toast.LENGTH_LONG).show();
            } else {
                RetrofitService service = new RetrofitService();
                BenutzerApi benutzerApi = service.getRetrofit().create(BenutzerApi.class);
                Call<Benutzer> call = benutzerApi.addBenutzer(new Benutzer(editTxtPasswrot.getText().toString(), editTxtVorname.getText().toString(),editTxtNachname.getText().toString(),editTxtEmail.getText().toString()));

                call.enqueue(new Callback<Benutzer>() {
                    @Override
                    public void onResponse(Call<Benutzer> call, Response<Benutzer> response) {
                        System.out.println("Neu " + response.body().toString());
                    }

                    @Override
                    public void onFailure(Call<Benutzer> call, Throwable t) {
                        System.out.println("Fehler " + t.getMessage());
                    }
                });

                Intent intent = new Intent(this,MainActivity.class);
                startActivity(intent);
            }
        });












    }
}