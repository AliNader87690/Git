package com.example.WatchlistAppServer.service;

import com.example.WatchlistAppServer.modul.Medium;
import com.example.WatchlistAppServer.modul.Watchlist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;
import com.example.WatchlistAppServer.repository.MediumRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class MediumService {
    @Autowired
    MediumRepository mediumRepository;


    public List<Medium> getAll(){
        List<Medium> list=new ArrayList<>();
        Streamable.of(mediumRepository.findAll()).forEach(list::add);
        return list;
    }

    public Medium getById(Long id){
        return mediumRepository.findById(id).get();
    }

    public void addMedium(Medium medium){
        mediumRepository.save(medium);
    }

    public void deleteMedium(Long id){
        mediumRepository.deleteById(id);
    }
}

