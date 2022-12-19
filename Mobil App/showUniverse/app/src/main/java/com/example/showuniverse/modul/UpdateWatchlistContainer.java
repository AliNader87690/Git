package com.example.showuniverse.modul;

public class UpdateWatchlistContainer {

    private Long benutzerID;
    private Long watchlistID;
    private String wachlistTitel;

    public UpdateWatchlistContainer(Long benutzerID, Long watchlistID, String wachlistTitel) {
        this.benutzerID = benutzerID;
        this.watchlistID = watchlistID;
        this.wachlistTitel = wachlistTitel;
    }

    public Long getBenutzerID() {
        return benutzerID;
    }

    public Long getWatchlistID() {
        return watchlistID;
    }

    public String getWachlistTitel() {
        return wachlistTitel;
    }
}
