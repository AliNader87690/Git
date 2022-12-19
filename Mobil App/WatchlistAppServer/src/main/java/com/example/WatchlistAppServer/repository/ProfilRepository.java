package com.example.WatchlistAppServer.repository;
import com.example.WatchlistAppServer.modul.Profil;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfilRepository extends JpaRepository<Profil,Long> {
}

