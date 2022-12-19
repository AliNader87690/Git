package com.example.WatchlistAppServer.controller;

import com.example.WatchlistAppServer.modul.Benutzer;
import com.example.WatchlistAppServer.modul.Watchlist;
import com.example.WatchlistAppServer.service.BenutzerService;
import com.example.WatchlistAppServer.service.WatchlistServiece;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
public class BenutzerController {
    @Autowired
    BenutzerService benutzerService;

    @Autowired
    WatchlistServiece watchlistServiece;

    @GetMapping("/benutzer/get-all")
    public List<Benutzer> getAll(){
        return benutzerService.getAll();
    }

    @GetMapping("/benutzer/add/{benutzer}")
    public void addBenutzer(@PathVariable Benutzer benutzer){
        System.out.println("BBBB: " + benutzer);
        for(Watchlist w:  benutzer.getWatchlisten()) {
            watchlistServiece.addWatchlist(w);
        }
        benutzerService.addBenutzer(benutzer);
    }

    @PostMapping("/benutzer/add/")
    public Benutzer addBenutzer2(@RequestBody Benutzer benutzer){
        System.out.println("BBBB: " + benutzer);
        for(Watchlist w:  benutzer.getWatchlisten()) {
            watchlistServiece.addWatchlist(w);
        }
        benutzerService.addBenutzer(benutzer);
        return benutzer;
    }

    @PostMapping("/benutzer/delete")
    public void deleteBenutzer(@RequestBody Benutzer benutzer){
        benutzerService.deleteBenutzer(benutzer.getId());
    }


    @PostMapping("/benutzer/getById")
    public Benutzer getById(@RequestBody Long id) {
        return benutzerService.getById(id);
    }

    @PostMapping("/benuter/update")
    public Benutzer updateBenutzer(@RequestBody Benutzer benutzer) {
        Benutzer orign = benutzerService.getById(benutzer.getId());
        if(orign != null && !benutzerService.checkEmail(benutzer.getEmail())) {
            orign.setVorname(benutzer.getVorname());
            orign.setNachname(benutzer.getNachname());
            orign.setEmail(benutzer.getEmail());
            orign.setPassword(benutzer.getPassword());
            System.out.println(orign);
            return orign;
        }
        return null;
    }
}
