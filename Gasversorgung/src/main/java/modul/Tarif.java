package modul;

import  jakarta.persistence.*;
import java.util.*;
import java.util.ArrayList;


@Entity
public class Tarif {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tarifID")
    private Long tarifID;

    @Column(name = "tarifbezeichnung", nullable = false)
    private String tarifbezeichnung;

    @Column(name = "grundPreisProJahr", nullable = false)
    private double grundpreis;

    @Column(name = "arbeitProKWH", nullable = false)
    private double arbeitKWH;

    @Column(name = "mindeslaufzeit", nullable = false)
    private int mindestlaufzeit;

    @Column(name = "kuendigungsfrist", nullable = false)
    private int kuendigungsfrist;

    @Column(name = "preisgarantie", nullable = false)
    private boolean preisgarantie;

    @Column(name = "neukundenBonus")
    private double neukundenBonus;

    @OneToMany(mappedBy = "tarif")
    private Set<Angebot> tarifList = new HashSet<>();

    public Tarif(String tarifbezeichnung, double grundpreis, double arbeitKWH, int mindestlaufzeit, int kuendigungsfrist, boolean preisgarantie, double neukundenBonus) {
        this.tarifbezeichnung = tarifbezeichnung;
        this.grundpreis = grundpreis;
        this.arbeitKWH = arbeitKWH;
        this.mindestlaufzeit = mindestlaufzeit;
        this.kuendigungsfrist = kuendigungsfrist;
        this.preisgarantie = preisgarantie;
        this.neukundenBonus = neukundenBonus;
    }

    public Tarif() {

    }

    public Long getTarifID() {
        return tarifID;
    }

    public void addAngebot(Angebot a) {
        a.setTarif(this);
        tarifList.add(a);
    }

    public void setTarifID(Long tarifID) {
        this.tarifID = tarifID;
    }


    public String getTarifbezeichnung() {
        return tarifbezeichnung;
    }

    public void setTarifbezeichnung(String tarifbezeichnung) {
        this.tarifbezeichnung = tarifbezeichnung;
    }

    public Set<Angebot> getTarifList() {
        return tarifList;
    }

    public void setTarifList(Set<Angebot> tarifList) {
        this.tarifList = tarifList;
    }

    public double getGrundpreis() {
        return grundpreis;
    }

    public void setGrundpreis(double grundpreis) {
        this.grundpreis = grundpreis;
    }

    public double getArbeitKWH() {
        return arbeitKWH;
    }

    public void setArbeitKWH(double arbeitKWH) {
        this.arbeitKWH = arbeitKWH;
    }

    public int getMindestlaufzeit() {
        return mindestlaufzeit;
    }

    public void setMindestlaufzeit(int mindestlaufzeit) {
        this.mindestlaufzeit = mindestlaufzeit;
    }

    public int getKuendigungsfrist() {
        return kuendigungsfrist;
    }

    public void setKuendigungsfrist(int kuendigungsfrist) {
        this.kuendigungsfrist = kuendigungsfrist;
    }

    public boolean isPreisgarantie() {
        return preisgarantie;
    }

    public void setPreisgarantie(boolean preisgarantie) {
        this.preisgarantie = preisgarantie;
    }

    public double getNeukundenBonus() {
        return neukundenBonus;
    }

    public void setNeukundenBonus(double neukundenBonus) {
        this.neukundenBonus = neukundenBonus;
    }

    public Set<Angebot> getTarif_angebotList() {
        return tarifList;
    }

    public void setTarif_angebotList(Set<Angebot> tarif_angebotList) {
        this.tarifList = tarif_angebotList;
    }


    @Override
    public String toString() {
        return "Tarif: " +
                " tarifID= " + tarifID +
                ", tarifbezeichnung= '" + tarifbezeichnung + '\'' +
                ", grundpreis= " + grundpreis +
                ", arbeitKWH= " + arbeitKWH +
                ", mindestlaufzeit= " + mindestlaufzeit +
                ", kuendigungsfrist= " + kuendigungsfrist +
                ", preisgarantie= " + preisgarantie +
                ", neukundenBonus= " + neukundenBonus;
    }
}
