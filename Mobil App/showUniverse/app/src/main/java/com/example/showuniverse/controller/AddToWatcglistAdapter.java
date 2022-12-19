package com.example.showuniverse.controller;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.showuniverse.R;
import com.example.showuniverse.modul.Benutzer;
import com.example.showuniverse.modul.Film;
import com.example.showuniverse.modul.Medium;
import com.example.showuniverse.modul.SpecialContainer;
import com.example.showuniverse.modul.Watchlist;
import com.example.showuniverse.retrofit.BenutzerApi;
import com.example.showuniverse.retrofit.RetrofitService;
import com.example.showuniverse.retrofit.WatchlistApi;


import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class AddToWatcglistAdapter extends RecyclerView.Adapter<AddToWatcglistAdapter.ViewHolder> implements AdapterView.OnItemSelectedListener {
    private Context context;
    private List<Watchlist> watchlisten;
    private Intent intent;
    private String status, mediumTyp;
    private SharedPreferences sp;
    private Benutzer benutzer;
    static Medium mediumInAdd;
    RetrofitService retrofitService = new RetrofitService();
    BenutzerApi benutzerApi = retrofitService.getRetrofit().create(BenutzerApi.class);
    WatchlistApi watchlistApi = retrofitService.getRetrofit().create(WatchlistApi.class);

    public AddToWatcglistAdapter(Context context) {
        this.context = context;
        sp = context.getSharedPreferences("userPrefs",Context.MODE_PRIVATE);
        getBenutzer();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.add_to_watchlist_item, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.btnAddToWachlist.setText(watchlisten.get(position).getTitel());
        holder.spinnerBar.setOnItemSelectedListener(AddToWatcglistAdapter.this);

        holder.btnAddToWachlist.setOnClickListener(view -> {
            mediumTyp = mediumTypIsFilm(mediumInAdd) ? "Film" : "Serie";
            benutzerApi.addBenutzer(benutzer).enqueue(new Callback<Benutzer>() {
                @Override
                public void onResponse(Call<Benutzer> call, Response<Benutzer> response) {
                    System.out.println("Benutzer erfolgreich serialisiert");
                }

                @Override
                public void onFailure(Call<Benutzer> call, Throwable t) {
                    System.out.println("Benutzer serialisierung fehlgeschlagen");
                }
            });

            watchlistApi.addMediumInWatchlist(new SpecialContainer(benutzer.getId(),watchlisten.get(position).getWatchlistId(),mediumInAdd.getId(), status,mediumTyp,0)).enqueue(new Callback<SpecialContainer>() {
                @Override
                public void onResponse(Call<SpecialContainer> call, Response<SpecialContainer> response) {
                    System.out.println("Medium In Watchlist erfolgreich serialisiert");
                }

                @Override
                public void onFailure(Call<SpecialContainer> call, Throwable t) {
                    System.out.println("Medium In Watchlist Fehlgeschlagen serialisiert");
                }
            });
            System.out.println("Erfolg Add.......");

            intent = new Intent(context, MeineWatchlistenActivity.class);
            context.startActivity(intent);
        });

    }

    private boolean mediumTypIsFilm(Medium m) {
        if(m.getClass() == Film.class) return true;
        return false;
    }

    @Override
    public int getItemCount() {
        return watchlisten.size();
    }

    public void setWatchlisten(List<Watchlist> watchlisten) {
        this.watchlisten = watchlisten;
        notifyDataSetChanged();
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        status = adapterView.getItemAtPosition(i).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        status = adapterView.getItemAtPosition(0).toString();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private CardView addToWatchlistItem;
        private Button btnAddToWachlist;
        private Spinner spinnerBar;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            addToWatchlistItem = itemView.findViewById(R.id.addToWatchlistItem);
            btnAddToWachlist = itemView.findViewById(R.id.btnAddToWachlist);
            spinnerBar = itemView.findViewById(R.id.spinnerBar);

            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(itemView.getContext(), R.array.status_array, android.R.layout.simple_spinner_item);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinnerBar.setAdapter(adapter);
        }
    }


    public void getBenutzer() {
        RetrofitService service = new RetrofitService();
        BenutzerApi benutzerApi = service.getRetrofit().create(BenutzerApi.class);
        benutzerApi.getById(sp.getLong("benutzerId", 0L)).enqueue(new Callback<Benutzer>() {
            @Override
            public void onResponse(Call<Benutzer> call, Response<Benutzer> response) {
                System.out.println("Benutzer in Add to Watchlist geholt");
                benutzer = response.body();
            }

            @Override
            public void onFailure(Call<Benutzer> call, Throwable t) {

            }
        });
    }
}
