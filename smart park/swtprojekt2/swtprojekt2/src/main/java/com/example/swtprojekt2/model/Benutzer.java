package com.example.swtprojekt2.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Data
public abstract class Benutzer {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String vorname;
    private String nachname;
    private String fhMail;
    private Date geburtsdatum;
    private String fhKennung;
    private String fhPasswort;

    public Benutzer(String vorname, String nachname, String fhMail, Date geburtsdatum, String fhKennung, String fhPasswort) {
        this.vorname = vorname;
        this.nachname = nachname;
        this.fhMail = fhMail;
        this.geburtsdatum = geburtsdatum;
        this.fhKennung = fhKennung;
        this.fhPasswort = fhPasswort;
    }

    public Benutzer() {
    }
}
