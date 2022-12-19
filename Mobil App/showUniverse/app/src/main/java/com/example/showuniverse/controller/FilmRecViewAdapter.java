package com.example.showuniverse.controller;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.showuniverse.R;
import com.example.showuniverse.modul.Film;

import java.util.ArrayList;
import java.util.List;

public class FilmRecViewAdapter extends RecyclerView.Adapter<FilmRecViewAdapter.ViewHolder> {

    private Context context;
    private List<Film> filmList = new ArrayList<>();

    public FilmRecViewAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.medium_list_item, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txtName.setText(filmList.get(position).getTitel());
        holder.txtJahr.setText(String.valueOf(filmList.get(position).getErscheinungsjahr()));
        Glide.with(context).asBitmap().load(filmList.get(position).getImageUrl()).into(holder.imageMedium);

        holder.parent.setOnClickListener(view -> {
            Intent intent = new Intent(context, MediumInformationenActivity.class);
            MediumInformationenActivity.medium = filmList.get(position);
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return filmList.size();
    }

    public void setMedia(List<Film> media) {
        this.filmList = media;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private CardView parent;
        private TextView txtName;
        private TextView txtJahr;
        private ImageView imageMedium;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            parent = itemView.findViewById(R.id.parent);
            txtName = itemView.findViewById(R.id.txtName);
            txtJahr = itemView.findViewById(R.id.txtjahr);
            imageMedium = itemView.findViewById(R.id.imageMedium);
        }
    }


}
