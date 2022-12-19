package com.example.swtprojekt2.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@Entity
@Data
public class Waermesensor extends Sensor{
    private double tempratur;
    private double co2wert;

    public Waermesensor(Long id, double tempratur, double co2wert) {
        super(id);
        this.tempratur = tempratur;
        this.co2wert = co2wert;
    }
    public Waermesensor() {
        super();
    }


}
