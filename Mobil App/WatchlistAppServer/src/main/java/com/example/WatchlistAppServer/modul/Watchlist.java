
package com.example.WatchlistAppServer.modul;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Watchlist {
    @Id
    @GeneratedValue
    private Long watchlistId;

    @Column(name = "titel", nullable = false)
    private String titel;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id", referencedColumnName = "id", nullable = false)
    @JsonBackReference
    private Benutzer benutzer;

    @OneToMany(mappedBy = "watchlist", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    @JsonManagedReference
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
