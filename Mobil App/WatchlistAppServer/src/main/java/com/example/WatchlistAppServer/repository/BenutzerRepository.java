package com.example.WatchlistAppServer.repository;

import com.example.WatchlistAppServer.modul.Benutzer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BenutzerRepository extends JpaRepository<Benutzer,Long> {
}
