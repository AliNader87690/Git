package modul;

import  jakarta.persistence.*;


@Entity
public class Zaehler {

    @EmbeddedId
    private Zaehler_AdressePK pk;

    @Column(name = "zaehlerstand")
    private Long zaehlerstand;

    /*
    *Zaehler ist optional heisst Wenn ein Zaehler geloescht wird, wird er aus der Liste in der Adresse Entitaet geloescht (Beziehung aufheben).
    * --> Aber das Adresse-Objekt selber bleibt in der Tabelle Adresse enthalten.
     */
    @ManyToOne
    @MapsId("adresseID")
    private Adresse adresse;

    @ManyToOne
    @JoinColumn(name = "kunde", nullable = false)
    private Kunde kunde;


    //Vertrag existiert nicht Ohne Zaehler --> Wenn ein Zaehler geloescht wird, muss dazu gehoeriger Vertrag auch mitgeloescht
    @OneToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "Vertragsnummer")
    private Vertrag vertrag;

    public Zaehler(Adresse adrsse, Long zaehlerstand, Kunde kunde) {
        pk = new Zaehler_AdressePK(adrsse.getAdresseID());
        addAdresse(adrsse);
        addKunde(kunde);
        this.zaehlerstand=zaehlerstand;
    }

    public Zaehler() {

    }

    public void addKunde(Kunde k) {
        k.getZaehlerList().add(this);
        kunde = k;
    }

    public void addAdresse(Adresse a) {
        a.addZaehler(this);
        adresse = a;
    }

    public Vertrag getVertrag() {
        return vertrag;
    }

    public void setVertrag(Vertrag vertrag) {
        this.vertrag = vertrag;
    }

    public Zaehler_AdressePK getPk() {
        return pk;
    }

    public void setPk(Zaehler_AdressePK pk) {
        if(this.pk == null) {
            this.pk = pk;

        }
        this.pk.setZaehlernummer(pk.getZaehlernummer());
        this.pk.setAdresseID(pk.getAdresseID());
    }

    public void setTeilSchluessel(Long adresseID) {
        pk.setAdresseID(adresseID);
    }

    public void setPrimaryKey(Long adresseID) {
        pk = new Zaehler_AdressePK(adresseID);
    }

    public void addVertrag(Vertrag vertrag) {
        assert (vertrag != null);
        vertrag.setZaehler(this);
        this.vertrag = vertrag;
    }

    public Long getZaehlerstand() {
        return zaehlerstand;
    }

    public void setZaehlerstand(Long zaehlerstand) {
        this.zaehlerstand = zaehlerstand;
    }

    public Adresse getAdresse() {
        return adresse;
    }

    public void setAdresse(Adresse adresse) {
        this.adresse = adresse;
    }

    public Kunde getKunde() {
        return kunde;
    }

    public void setKunde(Kunde kunde) {
        this.kunde = kunde;
    }

    @Override
    public String toString() {
        return "Zaehler: " +
                " zaehlernummer= " + pk +
                ", zaehlerstand= " + zaehlerstand;
    }

}