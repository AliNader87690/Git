package modul;

import  jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;


/**
 * Mit dieser Strategie wird jede Unterklasse eine eigene Tabelle abgebildet.
 * In allen Tabellen werden die Attributen Kundennummer und Telefonnummer in den Sub-Klassen hinzugefügt.
 *
 */

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Kunde {
    @Id
    @GeneratedValue
    @Column(name = "kundennummer")
    private Long kundennummer;

    @Column(name = "telefonnummer")
    private String telefonnummer;

    @OneToMany(mappedBy = "kunde", cascade = CascadeType.REMOVE)
    private Set<Zaehler> zaehlerList = new HashSet<>();

    public Kunde(String telefonnummer) {
        this.telefonnummer = telefonnummer;
    }

    public Kunde() {

    }

    public void addZaehler(Zaehler z){
        z.setKunde(this);
        zaehlerList.add(z);
    }

    public Long getKundennummer() {
        return kundennummer;
    }

    public String getTelefonnummer() {
        return telefonnummer;
    }

    public void setTelefonnummer(String telefonnummer) {
        this.telefonnummer = telefonnummer;
    }

    public void setKundennummer(Long kundennummer) {
        this.kundennummer = kundennummer;
    }

    public Set<Zaehler> getZaehlerList() {
        return zaehlerList;
    }

    public void setZaehlerList(Set<Zaehler> zaehlerList) {
        this.zaehlerList = zaehlerList;
    }



    @Override
    public String toString() {
        return "Kunde: " +
                " kundennummer= " + kundennummer +
                ", telefonnummer= " + telefonnummer +
                ", zaehlerList= " + zaehlerList;
    }


}
