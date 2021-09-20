package com.transportmanagmentsystem.service.impl;

import com.transportmanagmentsystem.dto.FuelDTO;
import com.transportmanagmentsystem.entity.Fuel;
import com.transportmanagmentsystem.repository.FuelRepository;
import com.transportmanagmentsystem.service.FuelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DefaultFuelService implements FuelService {

    @Autowired
    private FuelRepository fuelRepository;

    @Override
    public Fuel save(FuelDTO fuelDTO) {
        return fuelRepository.save(Fuel.builder()
                .price(fuelDTO.getPrice())
                .fuelType(fuelDTO.getFuelType())
                .build());
    }

    @Override
    public Optional<Fuel> findById(Long id) {
        return fuelRepository.findById(id);
    }

    @Override
    public List<Fuel> findAll() {
        return fuelRepository.findAll();
    }
}
