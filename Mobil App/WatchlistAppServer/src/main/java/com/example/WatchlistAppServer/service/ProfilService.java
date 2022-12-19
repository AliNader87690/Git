package com.example.WatchlistAppServer.service;
import com.example.WatchlistAppServer.modul.Profil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;
import com.example.WatchlistAppServer.repository.ProfilRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProfilService {
    @Autowired
    ProfilRepository pr;

    public void addProfil(Profil benutzer){
        pr.save(benutzer);
    }

    public List<Profil> getAll(){
        List<Profil> list=new ArrayList<>();
        Streamable.of(pr.findAll()).forEach(list::add);
        return  list;
    }

    public void deleteProfil(Long id){
        pr.deleteById(id);
    }



}
