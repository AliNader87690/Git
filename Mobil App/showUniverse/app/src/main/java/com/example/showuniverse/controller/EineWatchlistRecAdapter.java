package com.example.showuniverse.controller;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.showuniverse.R;
import com.example.showuniverse.modul.Medium;
import com.example.showuniverse.modul.MediumInWatchlist;
import com.example.showuniverse.modul.Watchlist;
import com.example.showuniverse.retrofit.RetrofitService;
import com.example.showuniverse.retrofit.WatchlistApi;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EineWatchlistRecAdapter extends RecyclerView.Adapter<EineWatchlistRecAdapter.ViewHolder> {

    private Context context;
    private String titel;
    private List<MediumInWatchlist> media;
    private Watchlist watchlist;
    private List<Medium> mediumList;


    public EineWatchlistRecAdapter(Context context,Watchlist watchlist, List<MediumInWatchlist> media, List<Medium> mediumList) {
        this.context = context;
        this.watchlist = watchlist;
        this.media =  media;
        this.mediumList = mediumList;
    }

    @NonNull
    @Override
    public EineWatchlistRecAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.watchlist_item, parent, false);
        EineWatchlistRecAdapter.ViewHolder holder = new EineWatchlistRecAdapter.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull EineWatchlistRecAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        //Wichtig jedes Mal wird eine MediumInWatchlist aus der Klasse Watchlist gezogen (!Die Benutzerklasse enthält eine Liste von Watchlisten, die der Reihe nach rausgezogen werden)
        if(media != null) {
            titel = watchlist.getTitel();

            holder.txtWatchlistName.setText(mediumList.get(position).getTitel());
            holder.txtStatus.setText(String.valueOf(media.get(position).getStatus()));
            Glide.with(context).asBitmap().load(mediumList.get(position).getImageUrl()).into(holder.imageViewWatchlist);

            holder.imageViewDelete.setOnClickListener(view -> {
                RetrofitService service = new RetrofitService();
                WatchlistApi watchlistApi = service.getRetrofit().create(WatchlistApi.class);
                watchlistApi.deleteItem(media.get(position).getId()).enqueue(new Callback<Integer>() {
                    @Override
                    public void onResponse(Call<Integer> call, Response<Integer> response) {
                        System.out.println("Elemet geloescht");
                    }

                    @Override
                    public void onFailure(Call<Integer> call, Throwable t) {

                    }
                });
                Intent intent = new Intent(context, MeineWatchlistenActivity.class);
                context.startActivity(intent);
        });

            holder.parent.setOnClickListener(view -> {
                Toast.makeText(context,mediumList.get(position).getTitel() +" Selected", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(context, MediumInformationenActivity.class);
                MediumInformationenActivity.medium = mediumList.get(position);
                context.startActivity(intent);
            });
        }
    }

    @Override
    public int getItemCount() {
        return media.size();
    }

    public void setMedia(List<MediumInWatchlist> media) {
        this.media = media;
        notifyDataSetChanged();
    }

    public String getTitel() {
        return titel;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private CardView parent;
        private TextView txtWatchlistName;
        private TextView txtStatus;
        private ImageView imageViewWatchlist;
        private ImageView imageViewDelete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            parent = itemView.findViewById(R.id.watchlistItemParent);
            txtWatchlistName = itemView.findViewById(R.id.txtNameWatchlistItem);
            txtStatus = itemView.findViewById(R.id.txtStatus);
            imageViewWatchlist = itemView.findViewById(R.id.imageMediumWatchlist);
            imageViewDelete = itemView.findViewById(R.id.imageViewDelete);
        }


    }
}
