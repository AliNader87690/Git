package com.example.swtprojekt2.repository;

import com.example.swtprojekt2.model.Benutzer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BenutzerRepository extends JpaRepository<Benutzer, Long> {
}
