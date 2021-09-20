package com.transportmanagmentsystem.service.impl;

import com.transportmanagmentsystem.dto.MedicalExaminationLogDTO;
import com.transportmanagmentsystem.entity.CustomUser;
import com.transportmanagmentsystem.entity.MedicalExaminationLog;
import com.transportmanagmentsystem.exception.EntityNotFoundException;
import com.transportmanagmentsystem.repository.MedicalExaminationRepository;
import com.transportmanagmentsystem.service.CustomUserDetailsService;
import com.transportmanagmentsystem.service.MedicalExaminationLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DefaultMedicalExaminationLogService implements MedicalExaminationLogService {

    @Autowired
    private MedicalExaminationRepository medicalExaminationRepository;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Override
    public MedicalExaminationLog save(MedicalExaminationLogDTO medicalExaminationLogDTO) {
        CustomUser driver = customUserDetailsService.findById(medicalExaminationLogDTO.getDriverId())
                .orElseThrow(() -> new EntityNotFoundException(String.format("%s entity with id %s was not found",
                        CustomUser.class, medicalExaminationLogDTO.getDriverId())));

        return medicalExaminationRepository.save(MedicalExaminationLog.builder()
                .driver(driver)
                .examinationDate(medicalExaminationLogDTO.getExaminationDate())
                .medicalExaminationStatus(MedicalExaminationLog.MedicalExaminationStatus.forName(medicalExaminationLogDTO.getStatus()))
                .build());
    }

    @Override
    public Optional<MedicalExaminationLog> findById(Long id) {
        return medicalExaminationRepository.findById(id);
    }

    @Override
    public List<MedicalExaminationLog> findAll() {
        return medicalExaminationRepository.findAll();
    }
}
