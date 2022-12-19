package modul;

import  jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;


@Entity
public class Angebot {

    @EmbeddedId
    private Angebot_PK id;

    @ManyToOne()
    @MapsId("anbieterID")
    private Anbieter anbieter;

    @ManyToOne
    @MapsId("tarifID")
    private Tarif tarif;

    @OneToMany(mappedBy = "angebot")
    private Set<Vertrag> vertragset = new HashSet<>();

    public Angebot() {}

    public Angebot(Anbieter anbieter, Tarif tarif) {
        this.id = new Angebot_PK(anbieter.getAnbieterID(), tarif.getTarifID());
        addAnbieter(anbieter);
        addTarif(tarif);
    }

    public void addAnbieter(Anbieter anbieter) {
        anbieter.getAnbieter_angebotList().add(this);
        this.anbieter = anbieter;
    }

    public void addTarif(Tarif tarif) {
        tarif.getTarif_angebotList().add(this);
        this.tarif = tarif;
    }

    public Set<Vertrag> getVertragset() {
        return vertragset;
    }

    public void setVertragset(Set<Vertrag> vertragset) {
        this.vertragset = vertragset;
    }

    public Angebot_PK getId() {
        return id;
    }

    public void setId(Angebot_PK id) {
        this.id = id;
    }

    public Anbieter getAnbieter() {
        return anbieter;
    }

    public void setAnbieter(Anbieter anbieter) {
        this.anbieter = anbieter;
    }

    public Tarif getTarif() {
        return tarif;
    }

    public void setTarif(Tarif tarif) {
        this.tarif = tarif;
        this.id.setTarifID(tarif.getTarifID());
    }

    @Override
    public String toString() {
        return "Angebot: " +
                " id= " + id;
    }
}
