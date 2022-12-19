package modul;

import  jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * AUTO (Standard): Weist Doctrine an, die Strategie zu wählen, die von der verwendeten Datenbankplattform bevorzugt wird.
 * Die bevorzugten Strategien sind IDENTITY für MySQL, SQLite und MsSQL und SEQUENCE für Oracle und PostgreSQL.
 * Diese Strategie bietet volle Portabilität.
 *
 * SEQUENCE: Weist Doctrine an, eine Datenbanksequenz für die ID-Generierung zu verwenden.
 * Diese Strategie bietet derzeit keine vollständige Portabilität. Sequenzen werden von Oracle und PostgreSql und SQL Anywhere unterstützt.
 *
 * IDENTITY: Weist Doctrine an, spezielle Identitätsspalten in der Datenbank zu verwenden,
 * die beim Einfügen einer Zeile einen Wert erzeugen. Diese Strategie bietet derzeit keine vollständige Portabilität und
 */


@Entity
public class Adresse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "adresseID")
    private Long adresseID;

    @Column(name = "strasse", nullable = false)
    private String strasse;

    @Column(name = "stadt", nullable = false)
    private String stadt;

    @Column(name = "land", nullable = false)
    private String land;

    @Column(name = "plz", nullable = false)
    private String plz;

    @Column(name = "hausnummer")
    private String hausnummer;

    //Adresse ist obligatorisch heisst Wenn eine Adresse geloescht wird, wuessen alle dazu gehoerigen Zaehler auch mitgeloeschet
    @OneToMany(mappedBy = "adresse", cascade = CascadeType.REMOVE)
    private Set<Zaehler> zaehlerList = new HashSet<>();

    public Adresse(String strasse, String stadt, String land, String plz, String hausnummer) {
        this.strasse = strasse;
        this.stadt = stadt;
        this.land = land;
        this.plz = plz;
        this.hausnummer = hausnummer;
    }

    public Adresse() {}



    public void addZaehler(Zaehler z){
        assert (z != null);
        z.setAdresse(this);
        zaehlerList.add(z);
    }

    public Long getAdresseID() {
        return adresseID;
    }

    public void setAdresseID(Long adresseID) {
        this.adresseID = adresseID;
    }

    public String getStrasse() {
        return strasse;
    }

    public void setStrasse(String strasse) {
        this.strasse = strasse;
    }

    public String getStadt() {
        return stadt;
    }

    public void setStadt(String stadt) {
        this.stadt = stadt;
    }

    public String getLand() {
        return land;
    }

    public void setLand(String land) {
        this.land = land;
    }

    public String getPlz() {
        return plz;
    }

    public void setPlz(String plz) {
        this.plz = plz;
    }

    public String getHausnummer() {
        return hausnummer;
    }

    public void setHausnummer(String hausnummer) {
        this.hausnummer = hausnummer;
    }

    public Set<Zaehler> getZaehlerList() {
        return zaehlerList;
    }

    public void setZaehlerList(Set<Zaehler> zaehlerList) {
        this.zaehlerList = zaehlerList;
    }

    @Override
    public String toString() {
        return "Adresse: " +
                " adresseID= " + adresseID +
                ", strasse= '" + strasse + '\'' +
                ", stadt= '" + stadt + '\'' +
                ", land= '" + land + '\'' +
                ", plz= '" + plz + '\'' +
                ", hausnummer= '" + hausnummer + '\'' +
                ", zaehlerList= " + zaehlerList;
    }

    public String showAdresse() {
        return strasse +" " +hausnummer + " " + plz + " " +stadt +" "+ land;
    }
}
