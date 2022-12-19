
package com.example.showuniverse.modul;


import java.util.LinkedList;
import java.util.List;



public class Watchlist {

    private Long watchlistId;
    private String titel;
    private Benutzer benutzer;


    private List<MediumInWatchlist> mediumInWatchlists = new LinkedList<>();

    public Watchlist(){

    }

    public Watchlist(String titel, Benutzer benutzer) {
        this.titel = titel;
        this.benutzer = benutzer;
        this.benutzer.getWatchlisten().add(this);

    }

    public void addMediumInWatchlist(MediumInWatchlist medium){
        this.mediumInWatchlists.add(medium);
    }

    public Long getWatchlistId() {
        return watchlistId;
    }

    public void setWatchlistId(Long watchlistId) {
        this.watchlistId = watchlistId;
    }

    public String getTitel() {
        return titel;
    }

    public void setTitel(String titel) {
        this.titel = titel;
    }

    public Benutzer getBenutzer() {
        return benutzer;
    }

    public void setBenutzer(Benutzer benutzer) {
        this.benutzer = benutzer;
    }

    public List<MediumInWatchlist> getMediumInWatchlists() {
        return mediumInWatchlists;
    }

    public void setMediumInWatchlists(List<MediumInWatchlist> mediumInWatchlists) {
        this.mediumInWatchlists = mediumInWatchlists;
    }


    @Override
    public String toString() {
        return "Watchlist{" +
                "watchlistId=" + watchlistId +
                ", titel='" + titel + '\'' +
                ", mediumInWatchlists=" + mediumInWatchlists +
                '}';
    }
}
