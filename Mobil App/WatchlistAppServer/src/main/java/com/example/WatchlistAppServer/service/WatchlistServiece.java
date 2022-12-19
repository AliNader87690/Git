package com.example.WatchlistAppServer.service;


import com.example.WatchlistAppServer.modul.Watchlist;
import com.example.WatchlistAppServer.repository.WatchlistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class WatchlistServiece {

    @Autowired
    WatchlistRepository watchlistRepository;


    public void addWatchlist(Watchlist watchlist) {
        watchlistRepository.save(watchlist);
    }

    public List<Watchlist> getAll() {
        List<Watchlist> list = new LinkedList<>();
        Streamable.of(watchlistRepository.findAll()).forEach(list::add);
        return list;
    }

    public Watchlist getById(Long id){
        return watchlistRepository.findById(id).get();
    }

    public void deleteWatchlist(Long id) {
        watchlistRepository.deleteById(id);
    }
}
