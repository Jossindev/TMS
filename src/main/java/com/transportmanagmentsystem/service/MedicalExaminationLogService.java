package com.transportmanagmentsystem.service;

import com.transportmanagmentsystem.dto.MedicalExaminationLogDTO;
import com.transportmanagmentsystem.entity.MedicalExaminationLog;

public interface MedicalExaminationLogService extends Service<MedicalExaminationLogDTO, MedicalExaminationLog>{
    MedicalExaminationLog save(MedicalExaminationLogDTO medicalExaminationLogDTO);
}
