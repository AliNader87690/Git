package com.example.swtprojekt2.controller;

import com.example.swtprojekt2.model.PassData;
import com.example.swtprojekt2.model.Student;
import com.example.swtprojekt2.service.BenutzerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

@RestController
public class BenutzerController {
    @Autowired
    BenutzerService bs;
    boolean falscheEingabe=false;
    @GetMapping("/")
    public RedirectView init(){
        return new RedirectView("/initialSensoren");
    }

    @GetMapping("/initialBenutzer")
    public RedirectView initial(){
        bs.benutzerInitialisieren();
        return new RedirectView("/home");
    }
    @GetMapping("/home")
    public ModelAndView home() {
        ModelAndView mav=new ModelAndView("Home");
        PassData passdata=new PassData();
        mav.addObject("passdata",passdata);
        mav.addObject("falscheEingabe",falscheEingabe);
        falscheEingabe=false;
        return mav;
    }

    @PostMapping(value = "/anmeldung")
    public RedirectView pruefeAnmedlungsDaten(@ModelAttribute PassData passData) {
        String fhkennung=passData.getFhkennung();
        String fhpasswort=passData.getFhpasswort();
        if (bs.gefunden(fhkennung, fhpasswort)) {
            falscheEingabe=false;
           return new RedirectView("/signal");
        }
        falscheEingabe=true;
        return new RedirectView("/home");
    }

    @GetMapping("/error")
    public ModelAndView error(){
        return new ModelAndView("error");
    }

}
