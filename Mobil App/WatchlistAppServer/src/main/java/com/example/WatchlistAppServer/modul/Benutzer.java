package com.example.WatchlistAppServer.modul;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;


@Entity
public class Benutzer extends Profil {


    @Column(name = "Vorname", nullable = false)
    private String vorname;
    @Column(name = "Nachname", nullable = false)
    private String nachname;
    @Column(name = "Email", nullable = false)
    private String email;
    @Column(name = "Gesperrt", nullable = false)
    private boolean gesperrt;

    @OneToMany(mappedBy = "benutzer",fetch = FetchType.LAZY)
    @JsonManagedReference
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
        return Objects.equals(watchlisten, benutzer.watchlisten);
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
