
package com.example.WatchlistAppServer.modul;

import javax.persistence.Entity;

@Entity
public class Serie extends Medium{

    private int aktuelleStaffel;
    private int anzahlEpisoden;

    public Serie() {
        super();
    }

    public Serie(int erscheinungsjahr, String beschreibung, String titel, String embeddedcode, double dauer, String imageUrl, double bewertung, int aktuelleStaffel, int anzahlEpisoden) {
        super(erscheinungsjahr, beschreibung, titel, embeddedcode, dauer, imageUrl, bewertung);
        this.aktuelleStaffel = aktuelleStaffel;
        this.anzahlEpisoden = anzahlEpisoden;
    }

    public int getAktuelleStaffel() {
        return aktuelleStaffel;
    }

    public void setAktuelleStaffel(int aktuelleStaffel) {
        this.aktuelleStaffel = aktuelleStaffel;
    }

    public int getAnzahlEpisoden() {
        return anzahlEpisoden;
    }

    public void setAnzahlEpisoden(int anzahlEpisoden) {
        this.anzahlEpisoden = anzahlEpisoden;
    }
}