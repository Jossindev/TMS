package com.transportmanagmentsystem.service.impl;

import com.transportmanagmentsystem.dto.BusDTO;
import com.transportmanagmentsystem.entity.Bus;
import com.transportmanagmentsystem.entity.ComfortClass;
import com.transportmanagmentsystem.entity.Fuel;
import com.transportmanagmentsystem.exception.EntityNotFoundException;
import com.transportmanagmentsystem.repository.BusRepository;
import com.transportmanagmentsystem.repository.ComfortClassRepository;
import com.transportmanagmentsystem.repository.FuelRepository;
import com.transportmanagmentsystem.service.BusService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DefaultBusService implements BusService {

    private final BusRepository busRepository;
    private final FuelRepository fuelRepository;
    private final ComfortClassRepository comfortClassRepository;

    @Override
    public Optional<Bus> findById(Long id) {
        return busRepository.findById(id);
    }

    @Override
    @Transactional
    public Bus save(BusDTO busDTO) {

        Fuel fuel = fuelRepository.findById(busDTO.getFuelId())
                .orElseThrow(() -> new EntityNotFoundException(
                        String.format("%s entity with id %s was not found", Fuel.class, busDTO.getFuelId())));

        ComfortClass comfortClass = comfortClassRepository.findById(busDTO.getComfortClassId())
                .orElseThrow(() -> new EntityNotFoundException(
                        String.format("%s entity with id %s was not found", ComfortClass.class, busDTO.getComfortClassId())));

        return busRepository.save(Bus.builder()
                .id(busDTO.getId())
                .name(busDTO.getName())
                .registrationNumber(busDTO.getRegistrationNumber())
                .seatAmount(busDTO.getSeatAmount())
                .fuelTankVolume(busDTO.getFuelTankVolume())
                .fuelConsumption(busDTO.getFuelConsumption())
                .mileage(busDTO.getMileage())
                .serviceRequiredAfterMileage(busDTO.getServiceRequiredAfterMileage())
                .fuel(fuel)
                .comfortClass(comfortClass)
                .build());
    }

    @Override
    public List<Bus> findAll() {
        return busRepository.findAll();
    }
}
