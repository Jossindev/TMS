package com.transportmanagmentsystem.dto;

import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RouteCheckDocumentDTO {

    private Long soldSeat;

    private Float filledFuel;

    @NotNull(message = "{page.add-driver.form.error.empty}")
    private Long driverId;

    @NotNull(message = "{page.add-driver.form.error.empty}")
    private Long busId;

    @NotNull(message = "{page.add-driver.form.error.empty}")
    private Long scheduleId;

}
