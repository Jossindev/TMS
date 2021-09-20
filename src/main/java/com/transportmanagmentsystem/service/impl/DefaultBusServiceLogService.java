package com.transportmanagmentsystem.service.impl;

import com.transportmanagmentsystem.dto.BusServiceLogDTO;
import com.transportmanagmentsystem.entity.Bus;
import com.transportmanagmentsystem.entity.BusServiceLog;
import com.transportmanagmentsystem.exception.EntityNotFoundException;
import com.transportmanagmentsystem.repository.BusServiceLogRepository;
import com.transportmanagmentsystem.service.BusService;
import com.transportmanagmentsystem.service.BusServiceLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DefaultBusServiceLogService implements BusServiceLogService {

    @Autowired
    private BusServiceLogRepository busServiceLogRepository;

    @Autowired
    private BusService busService;

    @Override
    public BusServiceLog save(BusServiceLogDTO busServiceLogDTO) {
        Bus bus = busService.findById(busServiceLogDTO.getBusId())
                .orElseThrow(() -> new EntityNotFoundException(
                        String.format("%s entity with id %s was not found", Bus.class, busServiceLogDTO.getBusId())));
        bus.setMileage(busServiceLogDTO.getMileage());
        return busServiceLogRepository.save(BusServiceLog.builder()
                .bus(bus)
                .serviceDate(busServiceLogDTO.getServiceDate())
                .mileage(busServiceLogDTO.getMileage())
                .build());
    }

    @Override
    public Optional<BusServiceLog> findById(Long id) {
        return busServiceLogRepository.findById(id);
    }

    @Override
    public List<BusServiceLog> findAll() {
        return busServiceLogRepository.findAll();
    }
}
