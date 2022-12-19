package com.example.WatchlistAppServer.service;

import com.example.WatchlistAppServer.modul.Film;
import com.example.WatchlistAppServer.repository.FilmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Service
public class FilmService {
    @Autowired
    FilmRepository filmRepository;

    public void addFilm(Film film){
        filmRepository.save(film);
    }

    public List<Film> getAll(){
        List<Film> list=new LinkedList<>();
        Streamable.of(filmRepository.findAll()).forEach(list::add);
        return  list;
    }

    public void deleteFilm(Long id){
        filmRepository.deleteById(id);
    }
}
