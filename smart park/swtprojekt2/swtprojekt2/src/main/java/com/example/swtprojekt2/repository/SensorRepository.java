package com.example.swtprojekt2.repository;

import com.example.swtprojekt2.model.Sensor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SensorRepository extends JpaRepository<Sensor, Long> {
}
