package com.example.WatchlistAppServer.repository;


import com.example.WatchlistAppServer.modul.Watchlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WatchlistRepository extends JpaRepository<Watchlist,Long> {
}
