package com.example.WatchlistAppServer.modul;

public class Container {
    private Benutzer benutzer;
    private Watchlist watchlist;

    public Container(Benutzer benutzer, Watchlist watchlist) {
        this.benutzer = benutzer;
        this.watchlist = watchlist;
    }

    public Benutzer getBenutzer() {
        return benutzer;
    }

    public Watchlist getWatchlist() {
        return watchlist;
    }
}
