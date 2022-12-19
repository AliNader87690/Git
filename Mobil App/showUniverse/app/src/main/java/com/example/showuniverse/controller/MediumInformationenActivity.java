package com.example.showuniverse.controller;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.showuniverse.R;
import com.example.showuniverse.modul.Medium;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

public class MediumInformationenActivity extends AppCompatActivity {

    private TextView txtErscheinungsjahr, txtBeschreibung, txtBewertung, txtLogoMediumInformationen;
    private YouTubePlayerView videoEmbeded;
    private ImageView imageViewMediumPoster;
    private Button btnZurWatchlistHinzufuegen;
    private ConstraintLayout layoutZurWatchlistAdd;
    private Intent intent;
    static Medium medium;
    private SharedPreferences sp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medium_informationen);

        txtErscheinungsjahr = findViewById(R.id.txtErscheinungsjahr);
        videoEmbeded = findViewById(R.id.videoEmbeded);
        imageViewMediumPoster = findViewById(R.id.imageViewMediumPoster);
        txtBeschreibung = findViewById(R.id.txtBeschreibung);
        txtBewertung = findViewById(R.id.txtBewertung);
        txtLogoMediumInformationen = findViewById(R.id.txtLogoMediumInformationen);
        btnZurWatchlistHinzufuegen = findViewById(R.id.btnZurWatchlistHinzufuegen);
        layoutZurWatchlistAdd = findViewById(R.id.layoutZurWatchlistAdd);
        sp = getSharedPreferences("userPrefs",MODE_PRIVATE);

        layoutZurWatchlistAdd.setOnClickListener(view -> {
            if(sp.getLong("benuterId",0l) > 0l){
                intent = new Intent(this, AddMediumToWatchlistActivity.class);
                AddMediumToWatchlistActivity.neuMedium = medium;
                AddToWatcglistAdapter.mediumInAdd = medium;
                onPause();
                startActivity(intent);
            } else {
                intent = new Intent(this, LoginActivity.class);
                startActivity(intent);
            }
        });

        btnZurWatchlistHinzufuegen.setOnClickListener(view -> {
            if(sp.getLong("benutzerId",0l) > 0l){
                intent = new Intent(this, AddMediumToWatchlistActivity.class);
                AddMediumToWatchlistActivity.neuMedium = medium;
                AddToWatcglistAdapter.mediumInAdd = medium;
                startActivity(intent);
            } else {
                intent = new Intent(this, LoginActivity.class);
                startActivity(intent);
            }
        });

        txtLogoMediumInformationen.setText(medium.getTitel());
        txtBewertung.setText("IMDB: " + medium.getBewertung());
        txtErscheinungsjahr.setText(medium.getErscheinungsjahr() + " Dauer: " + medium.getDauer() + " Min");
        txtBeschreibung.setText(medium.getBeschreibung());
        videoEmbeded.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
            @Override
            public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                youTubePlayer.loadVideo(medium.getEmbeddedcode(),0);
            }
        });

        Glide.with(this).asBitmap().load(medium.getImageUrl()).into(imageViewMediumPoster);
    }
}