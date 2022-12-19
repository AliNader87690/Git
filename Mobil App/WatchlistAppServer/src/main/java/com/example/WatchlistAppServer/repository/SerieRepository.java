package com.example.WatchlistAppServer.repository;

import com.example.WatchlistAppServer.modul.Serie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SerieRepository extends JpaRepository<Serie,Long> {
}
