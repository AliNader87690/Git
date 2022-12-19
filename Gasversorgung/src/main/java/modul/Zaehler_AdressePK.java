package modul;


import  jakarta.persistence.*;
import java.io.Serializable;
import java.util.Objects;


@Embeddable
public class Zaehler_AdressePK implements Serializable{

    private static Long count = 0L;

    @Column(name = "zaehlernummer")
    private Long zaehlernummer;

   @Column(name = "adresseID")
    private Long adresseID;

    public Zaehler_AdressePK(Long adresseID) {
        this.zaehlernummer = ++count;
        this.adresseID = adresseID;
    }

    public Zaehler_AdressePK() {}

    @Override
    public boolean equals(Object o) {
        if(o instanceof Zaehler_AdressePK) {
            final Zaehler_AdressePK otherPK = (Zaehler_AdressePK) o;
            final boolean areEqual = (otherPK.adresseID.equals(this.adresseID)) && (otherPK.zaehlernummer.equals(this.zaehlernummer));
            return  areEqual;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(zaehlernummer, adresseID);
    }

    public void setPrimaryKey(Long adresseID) {
        this.zaehlernummer = ++count;
        this.adresseID = adresseID;
    }
    public Long getZaehlernummer() {
        return zaehlernummer;
    }

    public void setZaehlernummer(Long zaehlernummer) {
        this.zaehlernummer = zaehlernummer;
    }

    public Long getAdresseID() {
        return adresseID;
    }

    public void setAdresseID(Long adresseID) {
        this.adresseID = adresseID;
    }

    @Override
    public String toString() {
        return "Zaehler_AdressePK: " +
                " zaehlernummer= " + zaehlernummer +
                ", adresseID= " + adresseID;
    }
}