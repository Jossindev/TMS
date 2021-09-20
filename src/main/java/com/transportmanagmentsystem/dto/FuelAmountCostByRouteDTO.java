package com.transportmanagmentsystem.dto;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FuelAmountCostByRouteDTO {

    private LocalDate dateFrom;

    private LocalDate dateTo;

    private String routeName;

    private String routeNumber;

    private Float distance;

    private Float benzineUsedAmount;

    private BigDecimal benzineUsedCost;

    private Float dieselUsedAmount;

    private BigDecimal dieselUsedCost;

    private Float fuelUsedAmountTotal;

    private BigDecimal fuelUsedCostAmountTotal;
}
