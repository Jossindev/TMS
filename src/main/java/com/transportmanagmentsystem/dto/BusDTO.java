package com.transportmanagmentsystem.dto;

import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BusDTO {

    private Long id;

    @NotEmpty(message = "{page.add-driver.form.error.empty}")
    private String name;

    @NotEmpty(message = "{page.add-driver.form.error.empty}")
    private String registrationNumber;

    @NotNull(message = "{page.add-driver.form.error.empty}")
    @Min(value = 1, message = "Seat amount should be more than zero")
    private Long seatAmount;

    @NotNull(message = "{page.add-driver.form.error.empty}")
    @Min(value = 1, message = "Seat amount should be more than zero")
    private Float fuelTankVolume;

    @NotNull(message = "{page.add-driver.form.error.empty}")
    @Min(value = 1, message = "Fuel consumption should be more than zero")
    private Float fuelConsumption;

    @NotNull(message = "{page.add-driver.form.error.empty}")
    @Min(value = 0, message = "Mileage should be positive")
    private Float mileage;

    @NotNull(message = "{page.add-driver.form.error.empty}")
    @Min(value = 0, message = "Mileage should be positive")
    private Float serviceRequiredAfterMileage;

    @NotNull(message = "{page.add-driver.form.error.empty}")
    private Long comfortClassId;

    @NotNull(message = "{page.add-driver.form.error.empty}")
    private Long fuelId;
}
