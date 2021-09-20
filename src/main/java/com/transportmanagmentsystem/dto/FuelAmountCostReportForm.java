package com.transportmanagmentsystem.dto;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FuelAmountCostReportForm {

    private Long routeId;

    @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm")
    private LocalDateTime dateFrom;

    @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm")
    private LocalDateTime dateTo;
}
