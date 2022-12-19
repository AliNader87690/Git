package com.example.WatchlistAppServer.modul;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Medium {
    @Id
    @GeneratedValue
    private Long id;
    private int erscheinungsjahr;
    @Column(name = "Beschreibung", length = 600)
    private String beschreibung;
    private String titel;
    private String embeddedcode;
    private double dauer;
    @Column(name = "ImageUrl", length = 400)
    private String imageUrl;
    private double bewertung;

    /*
    @OneToMany(mappedBy = "medium", fetch = FetchType.LAZY)
    private List<MediumInWatchlist> mediumInWatchlist = new LinkedList<>();
*/
    public  Medium(){}

    public Medium(int erscheinungsjahr, String beschreibung, String titel, String embeddedcode, double dauer, String imageUrl, double bewertung) {
        this.erscheinungsjahr = erscheinungsjahr;
        this.beschreibung = beschreibung;
        this.titel = titel;
        this.embeddedcode = embeddedcode;
        this.dauer = dauer;
        this.imageUrl = imageUrl;
        this.bewertung = bewertung;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getErscheinungsjahr() {
        return erscheinungsjahr;
    }

    public void setErscheinungsjahr(int erscheinungsjahr) {
        this.erscheinungsjahr = erscheinungsjahr;
    }

    public String getBeschreibung() {
        return beschreibung;
    }

    public void setBeschreibung(String beschreibung) {
        this.beschreibung = beschreibung;
    }

    public String getTitel() {
        return titel;
    }

    public void setTitel(String titel) {
        this.titel = titel;
    }

    public String getEmbeddedcode() {
        return embeddedcode;
    }

    public void setEmbeddedcode(String embeddedcode) {
        this.embeddedcode = embeddedcode;
    }

    public double getDauer() {
        return dauer;
    }

    public void setDauer(double dauer) {
        this.dauer = dauer;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public double getBewertung() {
        return bewertung;
    }

    public void setBewertung(double bewertung) {
        this.bewertung = bewertung;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Medium medium = (Medium) o;

        if (erscheinungsjahr != medium.erscheinungsjahr) return false;
        if (Double.compare(medium.dauer, dauer) != 0) return false;
        if (Double.compare(medium.bewertung, bewertung) != 0) return false;
        if (id != null ? !id.equals(medium.id) : medium.id != null) return false;
        if (beschreibung != null ? !beschreibung.equals(medium.beschreibung) : medium.beschreibung != null)
            return false;
        if (titel != null ? !titel.equals(medium.titel) : medium.titel != null) return false;
        if (embeddedcode != null ? !embeddedcode.equals(medium.embeddedcode) : medium.embeddedcode != null)
            return false;
        return imageUrl != null ? imageUrl.equals(medium.imageUrl) : medium.imageUrl == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id != null ? id.hashCode() : 0;
        result = 31 * result + erscheinungsjahr;
        result = 31 * result + (beschreibung != null ? beschreibung.hashCode() : 0);
        result = 31 * result + (titel != null ? titel.hashCode() : 0);
        result = 31 * result + (embeddedcode != null ? embeddedcode.hashCode() : 0);
        temp = Double.doubleToLongBits(dauer);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (imageUrl != null ? imageUrl.hashCode() : 0);
        temp = Double.doubleToLongBits(bewertung);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}

