package com.example.WatchlistAppServer.modul;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Profil {
    @Id
    @GeneratedValue
    private Long id;

    private String password;

    public Profil(String password) {

        this.password = password;
    }

    public Profil() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}
