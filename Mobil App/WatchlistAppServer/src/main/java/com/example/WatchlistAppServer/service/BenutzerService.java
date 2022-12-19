package com.example.WatchlistAppServer.service;

import com.example.WatchlistAppServer.modul.Benutzer;
import com.example.WatchlistAppServer.modul.Profil;
import com.example.WatchlistAppServer.repository.BenutzerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Service
public class BenutzerService {
    @Autowired
    BenutzerRepository benutzerRepository;

    public void addBenutzer(Benutzer benutzer){
        benutzerRepository.save(benutzer);
    }

    public List<Benutzer> getAll(){
        List<Benutzer> list = new LinkedList<>();
        Streamable.of(benutzerRepository.findAll()).forEach(list::add);
        return  list;
    }

    public Benutzer getById(Long id){
        return benutzerRepository.findById(id).get();
    }

    public void deleteBenutzer(Long id){
        benutzerRepository.deleteById(id);
    }


    public boolean checkEmail(String email) {
        List<Benutzer> benutzerList =  benutzerRepository.findAll();
        for(Benutzer b : benutzerList) {
            if(b.getEmail() == email)
                return true;
        }
        return false;
    }

}
