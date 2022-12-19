package com.example.WatchlistAppServer.controller;


import com.example.WatchlistAppServer.modul.Serie;
import com.example.WatchlistAppServer.service.SerieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
public class SerieController {
    @Autowired
    SerieService serieService;

    @GetMapping("/serie/get-all")
    public List<Serie> getAll(){
        return serieService.getAll();
    }

    @PostMapping("/serie/add")
    public void addSerie(@RequestBody Serie serie){
        serieService.addSerie(serie);
    }

    @PostMapping("/serie/delete")
    public void deleteSerie(@RequestBody Serie serie){
        serieService.deleteSerie(serie.getId());
    }



}
