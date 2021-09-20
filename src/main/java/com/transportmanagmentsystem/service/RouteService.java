package com.transportmanagmentsystem.service;

import com.transportmanagmentsystem.dto.RouteDTO;
import com.transportmanagmentsystem.entity.Route;

import java.util.Optional;

public interface RouteService extends Service<RouteDTO, Route> {
    Optional<Route> findByName(String departurePoint, String arrivalPoint);
}
