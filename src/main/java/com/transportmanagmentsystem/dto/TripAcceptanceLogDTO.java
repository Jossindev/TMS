package com.transportmanagmentsystem.dto;

import com.transportmanagmentsystem.entity.TripAcceptanceLog;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TripAcceptanceLogDTO {

    private TripAcceptanceLog.TripAcceptanceStatus status;

    private String description;

    private Long driverId;

    private Long routCheckDocumentId;

}
