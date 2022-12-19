package modul;

import java.util.*;
import java.util.ArrayList;
import  jakarta.persistence.*;

@Entity
public class Anbieter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "anbieterID")
    private Long anbieterID;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "ort")
    private String ort;

    /**
     * Es bedeutet, dass die Sammlung keine Sammlung von Entitäten ist, sondern eine Sammlung von einfachen Typen (Strings usw.)
     * oder eine Sammlung von einbettbaren Elementen (mit @Embeddable annotierte Klasse).
     * Das bedeutet auch, dass die Elemente vollständig im Besitz der enthaltenden Entitäten sind: Sie werden geändert,
     * wenn die Entität geändert wird, gelöscht, wenn die Entität gelöscht wird, usw. Sie können keinen eigenen Lebenszyklus haben.
     */

    @ElementCollection
    private List<String> telefonList = new ArrayList<>();

    @OneToMany(mappedBy = "anbieter")
    private Set<Angebot> anbieterList = new HashSet<>();

    public Anbieter(String name, String email, String ort) {
        this.name = name;
        this.email = email;
        this.ort = ort;
    }

    public Anbieter() {

    }

    public void addTelefonnummer(String telefonnummer){
        telefonList.add(telefonnummer);
    }
    public void addAngebot(Angebot t){
        t.setAnbieter(this);
        anbieterList.add(t);
    }


    public Long getAnbieterID() {
        return anbieterID;
    }

    public void setAnbieterID(Long anbieterID) {
        this.anbieterID = anbieterID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getOrt() {
        return ort;
    }

    public void setOrt(String ort) {
        this.ort = ort;
    }

    public List<String> getTelefonList() {
        return telefonList;
    }

    public void setTelefonList(List<String> telefonList) {
        this.telefonList = telefonList;
    }

    public Set<Angebot> getAnbieter_angebotList() {
        return anbieterList;
    }

    public void setAnbieter_angebotList(Set<Angebot> anbieter_angebotList) {
        this.anbieterList = anbieter_angebotList;
    }


    @Override
    public String toString() {
        return "Anbieter: " +
                " anbieterID= " + anbieterID +
                ", name= '" + name + '\'' +
                ", email= '" + email + '\'' +
                ", ort= '" + ort + '\'' +
                ", TelefonList= " + telefonList +
                ", anbieter_angebotList= " + anbieterList;
    }
}
