package com.example.showuniverse.modul;


import java.util.LinkedList;
import java.util.List;



public class Benutzer extends Profil {



    private String vorname;

    private String nachname;

    private String email;

    private boolean gesperrt;


    private List<Watchlist> watchlisten = new LinkedList<>();

    public Benutzer() {
        super();
    }

    public Benutzer(String password, String vorname, String nachname, String email) {
        super(password);
        this.vorname = vorname;
        this.nachname=nachname;
        this.email = email;
    }

    public String getVorname() {
        return vorname;
    }

    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    public String getNachname() {
        return nachname;
    }

    public void setNachname(String nachname) {
        this.nachname = nachname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isGesperrt() {
        return gesperrt;
    }

    public void setGesperrt(boolean gesperrt) {
        this.gesperrt = gesperrt;
    }

    public List<Watchlist> getWatchlisten() {
        return watchlisten;
    }

    public void setWatchlisten(List<Watchlist> watchlisten) {
        this.watchlisten = watchlisten;
    }

    @Override
    public String toString() {
        return "Benutzer{" +
                " id " + getId() + " "+
                "vorname='" + vorname + '\'' +
                ", nachname='" + nachname + '\'' +
                ", email='" + email + '\'' +
                ", gesperrt=" + gesperrt +
                ", watchlisten=" + watchlisten +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Benutzer benutzer = (Benutzer) o;

        if (gesperrt != benutzer.gesperrt) return false;
        if (!vorname.equals(benutzer.vorname)) return false;
        if (!nachname.equals(benutzer.nachname)) return false;
        if (!email.equals(benutzer.email)) return false;
        return watchlisten != null ? watchlisten.equals(benutzer.watchlisten) : benutzer.watchlisten == null;
    }

    @Override
    public int hashCode() {
        int result = vorname.hashCode();
        result = 31 * result + nachname.hashCode();
        result = 31 * result + email.hashCode();
        result = 31 * result + (gesperrt ? 1 : 0);
        result = 31 * result + (watchlisten != null ? watchlisten.hashCode() : 0);
        return result;
    }
}
