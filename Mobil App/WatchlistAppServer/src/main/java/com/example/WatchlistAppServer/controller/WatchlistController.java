package com.example.WatchlistAppServer.controller;


import com.example.WatchlistAppServer.modul.*;
import com.example.WatchlistAppServer.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
public class WatchlistController {
    @Autowired
    WatchlistServiece watchlistServiece;

    @Autowired
    BenutzerService benutzerService;

    @Autowired
    FilmService filmService;

    @Autowired
    MediumInWatchlistService mediumInWatchlistService;

    @Autowired
    MediumService mediumService;

    @GetMapping("/watchlist/get-all")
    public List<Watchlist> getAll() {
        return watchlistServiece.getAll();
    }

    @PostMapping("/watchlist/add")
    public void addWatchlist(@RequestBody Container container) {
        System.out.println("Geschafffft neue Watchlist");
        //Neue Watchlist wird erstellt und die dazugehörigen Beziehungen werden aufgebaut
        container.getWatchlist().setBenutzer(container.getBenutzer());
        container.getBenutzer().getWatchlisten().add(container.getWatchlist());
        watchlistServiece.addWatchlist(container.getWatchlist());
        benutzerService.addBenutzer(container.getBenutzer());
    }

    @PostMapping("/watchlist/addMedium")
    public void addMediumInWatchlist(@RequestBody SpecialContainer container) {
        System.out.println("Benutzer: " + container.getBenutzerId() + "\n" + "Watchlist: " + container.getWatchlistId() + "\n");
        Benutzer b = benutzerService.getById(container.getBenutzerId());
        Watchlist w = watchlistServiece.getById(container.getWatchlistId());
        MediumInWatchlist mw = new MediumInWatchlist(container.getStatus(), container.getMediumTyp(), container.getAktuelleEpisode(), w, container.getMediumId());

        w.addMediumInWatchlist(mw);
        mw.setWatchlist(w);
        mediumInWatchlistService.addMInWatchlist(mw);
        watchlistServiece.addWatchlist(w);
        benutzerService.addBenutzer(b);
        System.out.println("Geschafffft Medium in Watchlist");
    }

    @PostMapping("/watchlist/delete")
    public Integer deleteWatchlist(@RequestBody Long id) {
        System.out.println("delete");
        watchlistServiece.deleteWatchlist(id);

        return 10;
    }

    @PostMapping("/watchlist/deleteItem")
    public Integer deleteItem(@RequestBody Long mediumInWatchlistId) {
        mediumInWatchlistService.deleteMInWatchlist(mediumInWatchlistId);

        return 3;
    }

    @PostMapping("/watchlist/getById")
   public Watchlist getById(@RequestBody Long id){
        return watchlistServiece.getById(id);
    }

    @PostMapping("/watchlist/update")
    public Watchlist updateWatchlist(@RequestBody UpdateWatchlistContainer container) {

       Watchlist w = watchlistServiece.getById(container.getWatchlistID());
       w.setTitel(container.getWachlistTitel());
       watchlistServiece.addWatchlist(w);
       return w;
    }

}
