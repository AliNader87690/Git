package modul;

import  jakarta.persistence.*;

import java.util.Date;


@Entity
public class Privatkunde extends Kunde {

    @Column(name = "vorname", nullable = false)
    private String vorname;

    @Column(name = "nachname", nullable = false)
    private  String nachname;

    @Column(name = "anrede", nullable = false)
    private String anrede;

    @Column(name = "geburtsdatum", nullable = false)
    private Date geburtsdatum;

    public Privatkunde( String telefonnummer, String vorname, String nachname, String anrede, Date geburtsdatum) {
        super( telefonnummer);
        this.vorname = vorname;
        this.nachname = nachname;
        this.anrede = anrede;
        this.geburtsdatum = geburtsdatum;
    }

    public void setGeburtsdatum(Date geburtsdatum) {
        this.geburtsdatum = geburtsdatum;
    }

    public Privatkunde() {
      super();
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

    public String getAnrede() {
        return anrede;
    }

    public void setAnrede(String anrede) {
        this.anrede = anrede;
    }

    public Date getGeburtsdatum() {
        return geburtsdatum;
    }

    @Override
    public String toString() {
        return "Privatkunde: " +" Kundennummer= " + super.getKundennummer() +
                " vorname= '" + vorname + '\'' +
                ", nachname= '" + nachname + '\'' +
                ", anrede= '" + anrede + '\'' +
                ", geburtsdatum= " + geburtsdatum + " TelefonListe= "+ super.getTelefonnummer();
    }
}
