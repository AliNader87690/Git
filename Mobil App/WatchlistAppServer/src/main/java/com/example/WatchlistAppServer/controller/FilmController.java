package com.example.WatchlistAppServer.controller;

import com.example.WatchlistAppServer.modul.Film;
import com.example.WatchlistAppServer.service.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class FilmController {

    @Autowired
    FilmService filmService;

    @GetMapping("/film/get-all")
    public List<Film> getAll(){
        return filmService.getAll();
    }

    @PostMapping("/film/add")
    public void addFilm(@RequestBody Film film){
        filmService.addFilm(film);
    }

    @PostMapping("/film/delete")
    public void deleteFilm(@RequestBody Film film){
        filmService.deleteFilm(film.getId());
    }


}
