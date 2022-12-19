package com.example.swtprojekt2.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name="sensor")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Data
public abstract class Sensor {
    @Id
    private Long id;

    public Sensor(Long id) {
        this.id = id;
    }
    public Sensor() {
    }

}
