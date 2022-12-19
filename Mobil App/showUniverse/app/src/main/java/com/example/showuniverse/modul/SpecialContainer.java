package com.example.showuniverse.modul;

public class SpecialContainer {
    private Long benutzerId;
    private Long watchlistId;
    private Long mediumId;
    private String status;
    private String mediumTyp;
    private int aktuelleEpisode;

    public SpecialContainer(Long benutzerId, Long watchlistId, Long mediumId, String status, String mediumTyp, int aktuelleEpisode) {
        this.benutzerId = benutzerId;
        this.watchlistId = watchlistId;
        this.mediumId = mediumId;
        this.status = status;
        this.mediumTyp = mediumTyp;
        this.aktuelleEpisode = aktuelleEpisode;
    }

    public Long getBenutzerId() {
        return benutzerId;
    }

    public Long getMediumId() {
        return mediumId;
    }

    public Long getWatchlistId() {
        return watchlistId;
    }

    public String getStatus() {
        return status;
    }

    public String getMediumTyp() {
        return mediumTyp;
    }

    public int getAktuelleEpisode() {
        return aktuelleEpisode;
    }
}
