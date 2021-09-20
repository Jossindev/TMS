package com.transportmanagmentsystem.service.impl;

import com.transportmanagmentsystem.dto.ScheduleDTO;
import com.transportmanagmentsystem.entity.Route;
import com.transportmanagmentsystem.entity.Schedule;
import com.transportmanagmentsystem.exception.EntityNotFoundException;
import com.transportmanagmentsystem.repository.ScheduleRepository;
import com.transportmanagmentsystem.service.RouteService;
import com.transportmanagmentsystem.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DefaultScheduleService implements ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final RouteService routeService;

    @Override
    public Schedule save(ScheduleDTO scheduleDTO) {
        Route route = routeService.findById(scheduleDTO.getRouteId())
                .orElseThrow(() -> new EntityNotFoundException(
                        String.format("%s entity with id %s was not found", Route.class, scheduleDTO.getRouteId())));

        Schedule schedule = Schedule.builder()
                .departureDateTime(scheduleDTO.getDepartureDateTime())
                .arrivalDateTime(scheduleDTO.getArrivalDateTime())
                .route(route)
                .build();
        return scheduleRepository.save(schedule);
    }

    @Override
    public Optional<Schedule> findById(Long id) {
        return scheduleRepository.findById(id);
    }

    @Override
    public List<Schedule> findAll() {
        return scheduleRepository.findAll();
    }

    @Override
    public List<Schedule> findByArrivalDateTimeAfterAndAndArrivalDateTimeBeforeAndRoute(LocalDateTime from, Route route) {
        from = from.with(LocalTime.MIN);
        LocalDateTime to = from.plusDays(1);
        return scheduleRepository.findByArrivalDateTimeAfterAndAndArrivalDateTimeBeforeAndRouteOrderByDepartureDateTime(from, to, route);
    }
}
