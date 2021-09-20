package com.transportmanagmentsystem.dto;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ScheduleDTO {

    @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm")
    @NotNull(message = "{page.add-driver.form.error.empty}")
    private LocalDateTime departureDateTime;

    @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm")
    @NotNull(message = "{page.add-driver.form.error.empty}")
    private LocalDateTime arrivalDateTime;

    @NotNull(message = "{page.add-driver.form.error.empty}")
    private Long routeId;
}
