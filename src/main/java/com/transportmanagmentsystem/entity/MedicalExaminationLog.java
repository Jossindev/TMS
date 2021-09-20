package com.transportmanagmentsystem.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "MEDICAL_EXAMINATION_LOGS")
public class MedicalExaminationLog {

    @Id
    @GeneratedValue
    private Long id;

    private LocalDateTime examinationDate;

    private MedicalExaminationStatus medicalExaminationStatus;

    @ManyToOne
    private CustomUser driver;

    public enum MedicalExaminationStatus {
        ABLE_TO_DRIVE, NOT_ABLE_TO_DRIVE;

        private static final Map<String, MedicalExaminationStatus> MEDICAL_EXAMINATION_STATUS_MAP = new LinkedHashMap<>();

        static {
            for (MedicalExaminationStatus status : MedicalExaminationStatus.values()) {
                MEDICAL_EXAMINATION_STATUS_MAP.put(status.name(), status);
            }
        }

        public static MedicalExaminationStatus forName(String name) {
            return MEDICAL_EXAMINATION_STATUS_MAP.get(name);
        }
    }
}
