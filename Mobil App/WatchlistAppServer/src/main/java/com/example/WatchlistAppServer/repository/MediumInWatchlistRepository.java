package com.example.WatchlistAppServer.repository;

import com.example.WatchlistAppServer.modul.MediumInWatchlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface MediumInWatchlistRepository extends JpaRepository<MediumInWatchlist, Long> {
}
