package com.example.WatchlistAppServer.repository;

import com.example.WatchlistAppServer.modul.Film;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FilmRepository extends JpaRepository<Film,Long> {
}
