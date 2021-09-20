package com.transportmanagmentsystem.dto;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BusServiceLogDTO {

    @NotNull(message = "{page.add-driver.form.error.empty}")
    private Long busId;

    @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm")
    @NotNull(message = "{page.add-driver.form.error.empty}")
    private LocalDateTime serviceDate;

    @NotNull(message = "{page.add-driver.form.error.empty}")
    @Min(value = 0, message = "Mileage should be positive")
    private Float mileage;
}
