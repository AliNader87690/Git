package com.example.swtprojekt2.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@Entity
@Data

public class Tuersensor extends Sensor{
    private boolean geschlossen;

    public Tuersensor(Long id, boolean geschlossen) {
        super(id);
        this.geschlossen = geschlossen;
    }

    public Tuersensor() {
        super();
    }
}
