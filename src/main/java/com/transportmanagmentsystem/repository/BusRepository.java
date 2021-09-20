package com.transportmanagmentsystem.repository;

import com.transportmanagmentsystem.entity.Bus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BusRepository extends JpaRepository<Bus, Long> {
}
