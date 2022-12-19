package modul;

import  jakarta.persistence.*;
import java.io.Serializable;
import java.util.*;


@Embeddable
public class Angebot_PK implements Serializable {


    @Column(name = "anbieter_id")
    private Long anbieterID;
    @Column(name = "tarif_id")
    private Long tarifID;

    public Angebot_PK() {}

    public Angebot_PK(Long anbieterID, Long tarifID) {
        this.anbieterID = anbieterID;
        this.tarifID = tarifID;
    }

    @Override
    public boolean equals(Object o) {
        if(o instanceof Angebot_PK) {
            final Angebot_PK otherPK = (Angebot_PK) o;
            final boolean areEqual = (otherPK.anbieterID.equals(this.anbieterID)) && (otherPK.tarifID.equals(this.tarifID));
            return  areEqual;
        }

        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(anbieterID, tarifID);
    }

    @Override
    public String toString() {
        return "Angebot_PK: " +
                " anbieterID= " + anbieterID +
                ", tarifID= " + tarifID;
    }

    public Long getAnbieterID() {
        return anbieterID;
    }

    public void setAnbieterID(Long anbieterID) {
        this.anbieterID = anbieterID;
    }

    public Long getTarifID() {
        return tarifID;
    }

    public void setTarifID(Long tarifID) {
        this.tarifID = tarifID;
    }
}
