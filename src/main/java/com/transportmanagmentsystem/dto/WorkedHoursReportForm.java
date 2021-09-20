package com.transportmanagmentsystem.dto;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WorkedHoursReportForm {

    private Long driverId;

    @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm")
    private LocalDateTime dateFrom1;

    @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm")
    private LocalDateTime dateTo1;
}
