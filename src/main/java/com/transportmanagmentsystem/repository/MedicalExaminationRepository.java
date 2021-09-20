package com.transportmanagmentsystem.repository;

import com.transportmanagmentsystem.entity.MedicalExaminationLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicalExaminationRepository extends JpaRepository<MedicalExaminationLog, Long> {
}
