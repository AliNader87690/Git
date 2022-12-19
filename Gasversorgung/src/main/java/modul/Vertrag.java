package modul;

import  jakarta.persistence.*;
import java.util.Date;

@Entity
public class Vertrag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vertragsnummer")
    private Long vertragsnummer;

    @Column(name = "rechnungsadresse", nullable = false)
    private String rechnungsadresse;

    @Column(name = "vertragsbeginn")
    private Date vertragsbeginn;

    @Column(name = "Abschlagsbetrag")
    private double abschlagsbetrag;

    /*
    * Wenn ein Vertrag wird geloescht, dann ändert sich nichts an die Datenbank
    * aber nur das Vertrag-Objekt wird aus den 3 Listen (anbeiter, Tarif, Kunde) in Java-Code entfernt
    * */

    @ManyToOne
    @JoinColumns({@JoinColumn(name = "anbieter", referencedColumnName = "ANBIETER_ANBIETERID"), @JoinColumn(name = "tarif", referencedColumnName = "TARIF_TARIFID")})
    private Angebot angebot;

    @OneToOne
    @JoinColumns({@JoinColumn(name = "Zaehlernummer"), @JoinColumn(name = "adresseID")})
    private Zaehler zaehler;

    public Vertrag(String rechnungsadresse, Date vertragsbeginn, Angebot angebot, double abschlagsbetrag, Zaehler zaehler) {
        this.rechnungsadresse = rechnungsadresse;
        this.vertragsbeginn = vertragsbeginn;
        this.abschlagsbetrag = abschlagsbetrag;
        addAngebot(angebot);
        addZaehler(zaehler);
    }

    public Vertrag () {}

    public void addAngebot(Angebot angebot) {
        angebot.getVertragset().add(this);
        this.angebot = angebot;
    }



    public void addZaehler(Zaehler zaehler) {
        zaehler.setVertrag(this);
        this.zaehler = zaehler;
    }

    public Angebot getAngebot() {
        return angebot;
    }

    public void setAngebot(Angebot angebot) {
        this.angebot = angebot;
    }

    public Zaehler getZaehler() {
        return zaehler;
    }

    public void setZaehler(Zaehler zaehler) {
        this.zaehler = zaehler;
    }

    public Long getVertragsnummer() {
        return vertragsnummer;
    }

    public void setVertragsnummer(Long vertragsnummer) {
        this.vertragsnummer = vertragsnummer;
    }

    public String getRechnungsadresse() {
        return rechnungsadresse;
    }

    public void setRechnungsadresse(String rechnungsadresse) {
        this.rechnungsadresse = rechnungsadresse;
    }

    public Date getVertragsbeginn() {
        return vertragsbeginn;
    }

    public void setVertragsbeginn(Date vertragsbeginn) {
        this.vertragsbeginn = vertragsbeginn;
    }

    public double getAbschlagsbetrag() {
        return abschlagsbetrag;
    }

    public void setAbschlagsbetrag(double abschlagsbetrag) {
        this.abschlagsbetrag = abschlagsbetrag;
    }

    @Override
    public String toString() {
        return "Vertrag: " +
                " vertragsnummer= " + vertragsnummer +
                ", rechnungsadresse= '" + rechnungsadresse + '\'' +
                ", vertragsbeginn= " + vertragsbeginn +
                ", abschlagsbetrag= " + abschlagsbetrag;
    }

}