package com.transportmanagmentsystem.dto;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WorkedHoursRouteDTO {

    private String name;

    private LocalDate dateFrom;

    private LocalDate dateTo;

    private LocalTime timeFrom;

    private LocalTime timeTo;

    private int totalHours;
}
