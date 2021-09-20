package com.transportmanagmentsystem.dto;

import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RouteCheckDocumentUpdateDTO {

    @NotNull(message = "Select trip")
    private Long tripIdUpdate;

    private Long soldSeatUpdate;

    private Float filledFuelUpdate;

    private String tripStatusUpdate;

    @NotNull(message = "Select driver")
    private Long driverIdUpdate;

    @NotNull(message = "Select bus")
    private Long busIdUpdate;
}
