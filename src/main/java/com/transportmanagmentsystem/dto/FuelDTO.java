package com.transportmanagmentsystem.dto;

import com.transportmanagmentsystem.entity.Fuel;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FuelDTO {

    private BigDecimal price;

    private Fuel.FuelType fuelType;
}
