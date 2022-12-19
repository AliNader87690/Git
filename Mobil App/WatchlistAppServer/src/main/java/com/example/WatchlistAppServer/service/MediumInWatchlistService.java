package com.example.WatchlistAppServer.service;

import com.example.WatchlistAppServer.modul.MediumInWatchlist;
import com.example.WatchlistAppServer.repository.MediumInWatchlistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;


@Service
public class MediumInWatchlistService {



    @Autowired
    MediumInWatchlistRepository watchlistRepository;

    public void addMInWatchlist(MediumInWatchlist m){
        watchlistRepository.save(m);
    }

    public List<MediumInWatchlist> getAll(){
        List<MediumInWatchlist> list = new LinkedList<>();
        Streamable.of(watchlistRepository.findAll()).forEach(list::add);
        return  list;
    }

    public MediumInWatchlist getById(Long id){
        return watchlistRepository.findById(id).get();
    }

    public void deleteMInWatchlist(Long id){
        watchlistRepository.deleteById(id);
    }
}
