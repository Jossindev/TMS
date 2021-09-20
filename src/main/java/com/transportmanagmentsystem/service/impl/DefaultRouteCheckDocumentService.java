package com.transportmanagmentsystem.service.impl;

import com.transportmanagmentsystem.dto.RouteCheckDocumentDTO;
import com.transportmanagmentsystem.dto.RouteCheckDocumentUpdateDTO;
import com.transportmanagmentsystem.entity.*;
import com.transportmanagmentsystem.exception.EntityNotFoundException;
import com.transportmanagmentsystem.repository.RouteCheckDocumentRepository;
import com.transportmanagmentsystem.service.BusService;
import com.transportmanagmentsystem.service.CustomUserDetailsService;
import com.transportmanagmentsystem.service.RouteCheckDocumentService;
import com.transportmanagmentsystem.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DefaultRouteCheckDocumentService implements RouteCheckDocumentService {

    private final RouteCheckDocumentRepository routeCheckDocumentRepository;
    private final CustomUserDetailsService customUserDetailsService;
    private final BusService busService;
    private final ScheduleService scheduleService;

    @Override
    public RouteCheckDocument save(RouteCheckDocumentDTO routeCheckDocumentDTO) {

        CustomUser driver = customUserDetailsService.findById(routeCheckDocumentDTO.getDriverId())
                .orElseThrow(() -> new EntityNotFoundException(String.format("%s entity with id %s was not found",
                        CustomUser.class, routeCheckDocumentDTO.getDriverId())));

        Bus bus = busService.findById(routeCheckDocumentDTO.getBusId())
                .orElseThrow(() -> new EntityNotFoundException(String.format("%s entity with id %s was not found",
                        Bus.class, routeCheckDocumentDTO.getBusId())));

        Schedule schedule = scheduleService.findById(routeCheckDocumentDTO.getScheduleId())
                .orElseThrow(() -> new EntityNotFoundException(String.format("%s entity with id %s was not found",
                        Schedule.class, routeCheckDocumentDTO.getScheduleId())));

        return routeCheckDocumentRepository.save(RouteCheckDocument.builder()
                .driver(driver)
                .bus(bus)
                .filledFuel(0F)
                .soldSeat(0L)
                .tripStatus(RouteCheckDocument.TripStatus.DRIVER_ACCEPTANCE_WAITING)
                .schedule(schedule)
                .build());
    }

    @Override
    public Optional<RouteCheckDocument> findById(Long id) {
        return routeCheckDocumentRepository.findById(id);
    }

    @Override
    public List<RouteCheckDocument> findAll() {
        return routeCheckDocumentRepository.findAll();
    }

    @Override
    public RouteCheckDocument update(RouteCheckDocumentUpdateDTO routeCheckDocumentUpdateDTO) {

        RouteCheckDocument routeCheckDocument = routeCheckDocumentRepository.findById(routeCheckDocumentUpdateDTO.getTripIdUpdate()).
                orElseThrow(() -> new EntityNotFoundException(String.format("%s entity with id %s was not found",
                        RouteCheckDocument.class, routeCheckDocumentUpdateDTO.getTripIdUpdate())));

        CustomUser driver = customUserDetailsService.findById(routeCheckDocumentUpdateDTO.getDriverIdUpdate())
                .orElseThrow(() -> new EntityNotFoundException(String.format("%s entity with id %s was not found",
                        CustomUser.class, routeCheckDocumentUpdateDTO.getDriverIdUpdate())));

        Bus bus = busService.findById(routeCheckDocumentUpdateDTO.getBusIdUpdate())
                .orElseThrow(() -> new EntityNotFoundException(String.format("%s entity with id %s was not found",
                        Bus.class, routeCheckDocumentUpdateDTO.getBusIdUpdate())));

        routeCheckDocument.setDriver(driver);
        routeCheckDocument.setBus(bus);
        routeCheckDocument.setTripStatus(RouteCheckDocument.TripStatus.forName(routeCheckDocumentUpdateDTO.getTripStatusUpdate()));
        routeCheckDocument.setFilledFuel(routeCheckDocumentUpdateDTO.getFilledFuelUpdate());
        return routeCheckDocumentRepository.save(routeCheckDocument);
    }

    @Override
    public List<RouteCheckDocument> findAllByDriverAndTripStatus(CustomUser driver, RouteCheckDocument.TripStatus tripStatus) {
        return routeCheckDocumentRepository.findAllByDriverAndAndTripStatus(driver, tripStatus);
    }

    @Override
    public List<RouteCheckDocument> findAllByDateAndRoute(LocalDateTime departureDateTime, Route route) {
        return null;
    }

    @Override
    public List<RouteCheckDocument> findAllByScheduleIn(List<Schedule> schedules) {
        List<RouteCheckDocument> routeCheckDocuments = routeCheckDocumentRepository.findAllByScheduleIn(schedules);

        return routeCheckDocuments.stream()
                .filter(routeCheckDocument -> routeCheckDocument.getSoldSeat() < routeCheckDocument.getBus().getSeatAmount())
                .collect(Collectors.toList());
    }
}
