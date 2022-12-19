package com.example.swtprojekt2.controller;

import com.example.swtprojekt2.service.SimlulatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Random;

@RestController
public class SimulatorController {
    @Autowired
    SimlulatorService simlulatorService;
    @GetMapping("/signal")
    public RedirectView simulation(){
        Random random=new Random();
        int id=random.nextInt(11-1)+1;
        simlulatorService.TuersensorAendern();
        simlulatorService.NaehrungssensorAendern(id);
        simlulatorService.WaermesensorAendern(15l,random.nextInt(45),random.nextInt(1200));
        simlulatorService.WaermesensorAendern(16l,random.nextInt(45),random.nextInt(1200));
        simlulatorService.WaermesensorAendern(17l,random.nextInt(45),random.nextInt(1200));
        simlulatorService.WaermesensorAendern(18l,random.nextInt(45),random.nextInt(1200));
        return new RedirectView("/Benutzerseite?angemeldet=true");
    }
}
