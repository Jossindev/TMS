package com.transportmanagmentsystem.dto;

import lombok.*;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FindTripForm {

    @NotEmpty(message = "{page.add-driver.form.error.empty}")
    private String departurePoint;

    @NotEmpty(message = "{page.add-driver.form.error.empty}")
    private String arrivalPoint;

    @NotEmpty(message = "{page.add-driver.form.error.empty}")
    private String departureDateTime;

    private String tripsNotFound;
}
