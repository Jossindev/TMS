package com.transportmanagmentsystem.dto;

import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WorkedHoursDTO {

    private String driverFullName;

    private LocalDate dateFrom;

    private LocalDate dateTo;

    List<WorkedHoursRouteDTO> workedHoursRouteDTOS;
}
