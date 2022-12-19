package modul;

import  jakarta.persistence.*;


@Entity
public class Geschaeftskunde extends Kunde {

    @Column(name = "ansprechpartner", nullable = false)
    private String ansprechpartner;

    @Column(name = "firmenname", nullable = false)
    private String firmenname;

    public Geschaeftskunde(String telefonnummer, String ansprechpartner, String firmenname) {
        super(telefonnummer);
        this.ansprechpartner = ansprechpartner;
        this.firmenname = firmenname;
    }

    public Geschaeftskunde() {super();}

    public String getAnsprechpartner() {
        return ansprechpartner;
    }

    public void setAnsprechpartner(String ansprechpartner) {
        this.ansprechpartner = ansprechpartner;
    }

    public String getFirmenname() {
        return firmenname;
    }

    public void setFirmenname(String firmenname) {
        this.firmenname = firmenname;
    }

    @Override
    public String toString() {
        return "Geschaeftskunde: " + " Kundennummer= " + super.getKundennummer() +
                " ansprechpartner= " + ansprechpartner +
                ", firmenname= " + firmenname +" TelefonListe= " + super.getTelefonnummer();
    }
}
