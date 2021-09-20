package com.transportmanagmentsystem.dto;

import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RouteDTO {

    @NotBlank(message = "{page.add-driver.form.error.empty}")
    private String name;

    @NotBlank(message = "{page.add-driver.form.error.empty}")
    private String number;

    @NotNull(message = "{page.add-driver.form.error.empty}")
    @Min(value = 0, message = "Distance should be positive")
    private Float distance;

    @NotBlank(message = "{page.add-driver.form.error.empty}")
    private String departureAddress;

    @NotBlank(message = "{page.add-driver.form.error.empty}")
    private String arrivalAddress;
}
