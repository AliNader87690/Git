package com.example.WatchlistAppServer.repository;

import com.example.WatchlistAppServer.modul.Medium;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MediumRepository extends JpaRepository<Medium,Long> {
}