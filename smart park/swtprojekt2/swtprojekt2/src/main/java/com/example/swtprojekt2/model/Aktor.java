package com.example.swtprojekt2.model;

import com.example.swtprojekt2.repository.SensorChecker;
import com.example.swtprojekt2.repository.SensorPerformer;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name="Aktor")
@Data
@NoArgsConstructor
public class Aktor {
    @Id
    private Long id;
    @OneToOne
    private Sensor sensor;
    public Aktor(Long id,Sensor sensor){
        this.id=id;
        this.sensor=sensor;
    }
    public boolean checkStatus(SensorChecker sensorChecker){
        return sensorChecker.checkSensor();
    }
    public void doAktion(SensorPerformer sensorPerformer){
       sensorPerformer.changeValue();
    }
}
