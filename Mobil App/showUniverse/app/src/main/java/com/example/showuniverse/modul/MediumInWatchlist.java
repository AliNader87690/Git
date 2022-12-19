package com.example.showuniverse.modul;




public class MediumInWatchlist {


    private Long id;
    private String status;
    private String mediumTyp;
    private int aktuelleEpisode;
    private Watchlist watchlist;
    private Long mediumId;

    public MediumInWatchlist() {
    }

    public MediumInWatchlist(String status, String mediumTyp, int aktuelleEpisode, Watchlist watchlist, Long mediumId) {
        this.status = status;
        this.mediumTyp = mediumTyp;
        this.aktuelleEpisode = aktuelleEpisode;
        this.watchlist = watchlist;
        this.watchlist.addMediumInWatchlist(this);
        this.mediumId = mediumId;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMediumTyp() {
        return mediumTyp;
    }

    public void setMediumTyp(String mediumTyp) {
        this.mediumTyp = mediumTyp;
    }

    public int getAktuelleEpisode() {
        return aktuelleEpisode;
    }

    public void setAktuelleEpisode(int aktuelleEpisode) {
        this.aktuelleEpisode = aktuelleEpisode;
    }

    public Watchlist getWatchlist() {
        return watchlist;
    }

    public void setWatchlist(Watchlist watchlist) {
        this.watchlist = watchlist;
    }

    public Long getMediumId() {
        return mediumId;
    }

    public void setMediumId(Long mediumId) {
        this.mediumId = mediumId;
    }


    @Override
    public String toString() {
        return "MediumInWatchlist{" +
                "id=" + id +
                ", status='" + status + '\'' +
                ", mediumTyp='" + mediumTyp + '\'' +
                ", aktuelleEpisode=" + aktuelleEpisode +
                ", watchlist=" + watchlist +
                ", mediumId=" + mediumId +
                '}';
    }
}