package com.transportmanagmentsystem.repository;

import com.transportmanagmentsystem.entity.TripAcceptanceLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TripAcceptanceRepository extends JpaRepository<TripAcceptanceLog, Long> {
}
