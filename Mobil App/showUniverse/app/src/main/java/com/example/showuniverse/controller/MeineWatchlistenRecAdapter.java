package com.example.showuniverse.controller;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.showuniverse.R;
import com.example.showuniverse.modul.Watchlist;

import java.util.List;

public class MeineWatchlistenRecAdapter extends RecyclerView.Adapter<MeineWatchlistenRecAdapter.ViewHolder> {
    private Context context;
    private List<Watchlist> watchlisten;
    private Intent intent;

    public MeineWatchlistenRecAdapter(Context context, List<Watchlist> list) {
        this.context = context;
        setWatchlisten(list);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.eine_watchlist_item, parent, false);
        ViewHolder holder = new MeineWatchlistenRecAdapter.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.btnEineWatchlistItem.setText(watchlisten.get(position).getTitel());


        holder.btnEineWatchlistItem.setOnClickListener(view -> {
            intent = new Intent(context, EineWatchlistActivity.class);
            EineWatchlistActivity.watchlist = watchlisten.get(position);
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return watchlisten.size();
    }

    public void setWatchlisten(List<Watchlist> watchlisten) {
        this.watchlisten = watchlisten;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private CardView eineWatchlistItem;
        private Button btnEineWatchlistItem;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            eineWatchlistItem = itemView.findViewById(R.id.eineWatchlistItem);
            btnEineWatchlistItem = itemView.findViewById(R.id.btnEineWatchlistItem);
        }
    }
}
