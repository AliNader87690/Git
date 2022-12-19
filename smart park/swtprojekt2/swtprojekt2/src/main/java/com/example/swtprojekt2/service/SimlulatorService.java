package com.example.swtprojekt2.service;

import com.example.swtprojekt2.model.Aktor;
import com.example.swtprojekt2.model.Naehrungsensor;
import com.example.swtprojekt2.model.Tuersensor;
import com.example.swtprojekt2.model.Waermesensor;
import com.example.swtprojekt2.repository.AktorRepositiory;
import com.example.swtprojekt2.repository.SensorPerformer;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Data
public class SimlulatorService {
    @Autowired
    AktorRepositiory aktorRepositiory;
    public void TuersensorAendern(){
        aktorRepositiory.getById(14l).doAktion(() -> {
            Aktor tueraktor=aktorRepositiory.getById(14l);
            Tuersensor ts=(Tuersensor) tueraktor.getSensor();
            ts.setGeschlossen(!ts.isGeschlossen());
            aktorRepositiory.save(tueraktor);
        });
    }
    public void NaehrungssensorAendern(long id){
        aktorRepositiory.getById(id).doAktion(() -> {
            Aktor naehrungsaktor=aktorRepositiory.getById(id);
            Naehrungsensor ns=(Naehrungsensor) naehrungsaktor.getSensor();
            ns.setBelegt(!ns.isBelegt());
            aktorRepositiory.save(naehrungsaktor);
        });
    }
    public void WaermesensorAendern(long id, double temp, double CO2wert){
        aktorRepositiory.getById(id).doAktion(() -> {
            Aktor waermeaktor=aktorRepositiory.getById(id);
            Waermesensor ws=(Waermesensor) waermeaktor.getSensor();
            ws.setTempratur(temp);
            ws.setCo2wert(CO2wert);
            aktorRepositiory.save(waermeaktor);
        });
    }

}
