package com.example.swtprojekt2.service;

import com.example.swtprojekt2.model.*;
import com.example.swtprojekt2.repository.AktorRepositiory;
import com.example.swtprojekt2.repository.SensorRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Data
@Service
public class AktorService {
    @Autowired
    private AktorRepositiory aktorRepositiory;

    @Autowired
    private SensorRepository sensorRepository;


    public void aktorenInitialisieren(){
       aktorSpeichern(new Aktor(1l,new Naehrungsensor(1l,false)));
        aktorSpeichern(new Aktor(2l,new Naehrungsensor(2l,false)));
        aktorSpeichern(new Aktor(3l,new Naehrungsensor(3l,false)));
        aktorSpeichern(new Aktor(4l,new Naehrungsensor(4l,false)));
        aktorSpeichern(new Aktor(5l,new Naehrungsensor(5l,false)));
        aktorSpeichern(new Aktor(6l,new Naehrungsensor(6l,false)));
        aktorSpeichern(new Aktor(7l,new Naehrungsensor(7l,false)));
        aktorSpeichern(new Aktor(8l,new Naehrungsensor(8l,false)));
        aktorSpeichern(new Aktor(9l,new Naehrungsensor(9l,false)));
        aktorSpeichern( new Aktor(10l,new Naehrungsensor(10l,false)));
        aktorSpeichern(new Aktor(14l, new Tuersensor(14l,true)));
        aktorSpeichern(new Aktor(15l,new Waermesensor(15l,26.3,822)));
        aktorSpeichern(new Aktor(16l,new Waermesensor(16l,22,699)));
        aktorSpeichern(new Aktor(17l,new Waermesensor(17l,27.5,923)));
        aktorSpeichern(new Aktor(18l,new Waermesensor(18l,24,750)));
    }

    private void aktorSpeichern(Aktor aktor){
        sensorRepository.save(aktor.getSensor());
        aktorRepositiory.save(aktor);
    }
}
