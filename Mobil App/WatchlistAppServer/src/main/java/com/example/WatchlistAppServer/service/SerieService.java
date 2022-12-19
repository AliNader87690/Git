package com.example.WatchlistAppServer.service;

import com.example.WatchlistAppServer.modul.Film;
import com.example.WatchlistAppServer.modul.Serie;
import com.example.WatchlistAppServer.repository.FilmRepository;
import com.example.WatchlistAppServer.repository.SerieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
@Service
public class SerieService {

    @Autowired
    SerieRepository serieRepository;

    public void addSerie(Serie serie){
        serieRepository.save(serie);
    }

    public List<Serie> getAll(){
        List<Serie> list=new LinkedList<>();
        Streamable.of(serieRepository.findAll()).forEach(list::add);
        return  list;
    }

    public void deleteSerie(Long id){
        serieRepository.deleteById(id);
    }
}

