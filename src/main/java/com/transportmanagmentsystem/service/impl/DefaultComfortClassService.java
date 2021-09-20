package com.transportmanagmentsystem.service.impl;

import com.transportmanagmentsystem.dto.ComfortClassDTO;
import com.transportmanagmentsystem.entity.ComfortClass;
import com.transportmanagmentsystem.repository.ComfortClassRepository;
import com.transportmanagmentsystem.service.ComfortClassService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DefaultComfortClassService implements ComfortClassService {

    private final ComfortClassRepository comfortClassRepository;

    @Override
    public ComfortClass save(ComfortClassDTO comfortClassDTO) {
        return comfortClassRepository.save(ComfortClass.builder()
                .price(comfortClassDTO.getPrice())
                .comfortClassType(comfortClassDTO.getComfortClassType())
                .build());
    }

    @Override
    public Optional<ComfortClass> findById(Long id) {
        return comfortClassRepository.findById(id);
    }

    @Override
    public List<ComfortClass> findAll() {
        return comfortClassRepository.findAll();
    }
}
