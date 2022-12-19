package com.example.swtprojekt2.model;

import lombok.Data;

import javax.persistence.Entity;
import java.util.Date;

@Entity
@Data
public class Student extends Benutzer{
    public Student(String vorname, String nachname, String fhMail, Date geburtsdatum, String fhKennung, String fhPasswort) {
        super(vorname, nachname, fhMail, geburtsdatum, fhKennung, fhPasswort);
    }

    public Student() {
    }
}
