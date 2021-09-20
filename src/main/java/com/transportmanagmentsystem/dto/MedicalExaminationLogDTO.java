package com.transportmanagmentsystem.dto;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MedicalExaminationLogDTO {

    @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm")
    @NotNull(message = "{page.add-driver.form.error.empty}")
    private LocalDateTime examinationDate;

    @NotBlank(message = "{page.add-driver.form.error.empty}")
    private String status;

    @NotNull(message = "{page.add-driver.form.error.empty}")
    private Long driverId;
}
