package com.example.swtprojekt2.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@Entity
@Data
public class Naehrungsensor extends Sensor{
    private boolean belegt;


    public Naehrungsensor(Long id, boolean belegt) {
        super(id);
        this.belegt = belegt;
    }

    public Naehrungsensor() {
        super();
    }
}
