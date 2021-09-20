package com.transportmanagmentsystem.service.impl;

import com.transportmanagmentsystem.dto.RouteDTO;
import com.transportmanagmentsystem.entity.Route;
import com.transportmanagmentsystem.repository.RouteRepository;
import com.transportmanagmentsystem.service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DefaultRouteService implements RouteService {

    @Autowired
    private RouteRepository routeRepository;

    @Override
    public Route save(RouteDTO routeDTO) {
        return routeRepository.save(Route.builder()
                .name(routeDTO.getName())
                .number(routeDTO.getNumber())
                .departureAddress(routeDTO.getDepartureAddress())
                .arrivalAddress(routeDTO.getArrivalAddress())
                .distance(routeDTO.getDistance())
                .build());
    }

    @Override
    public Optional<Route> findById(Long id) {
        return routeRepository.findById(id);
    }

    @Override
    public List<Route> findAll() {
        return routeRepository.findAll();
    }

    @Override
    public Optional<Route> findByName(String departurePoint, String arrivalPoint) {
        return routeRepository.findByName(departurePoint + " - " + arrivalPoint);
    }
}
