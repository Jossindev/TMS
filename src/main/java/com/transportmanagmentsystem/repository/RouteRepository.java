package com.transportmanagmentsystem.repository;

import com.transportmanagmentsystem.entity.Route;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RouteRepository extends JpaRepository<Route, Long> {
    Optional<Route> findByName(String name);
}
