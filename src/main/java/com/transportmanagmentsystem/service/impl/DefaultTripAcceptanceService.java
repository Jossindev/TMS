package com.transportmanagmentsystem.service.impl;

import com.transportmanagmentsystem.dto.TripAcceptanceForm;
import com.transportmanagmentsystem.entity.CustomUser;
import com.transportmanagmentsystem.entity.RouteCheckDocument;
import com.transportmanagmentsystem.entity.TripAcceptanceLog;
import com.transportmanagmentsystem.exception.EntityNotFoundException;
import com.transportmanagmentsystem.repository.TripAcceptanceRepository;
import com.transportmanagmentsystem.service.CustomUserDetailsService;
import com.transportmanagmentsystem.service.RouteCheckDocumentService;
import com.transportmanagmentsystem.service.TripAcceptanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DefaultTripAcceptanceService implements TripAcceptanceService {

    private final TripAcceptanceRepository tripAcceptanceRepository;
    private final RouteCheckDocumentService routeCheckDocumentService;
    private final CustomUserDetailsService customUserDetailsService;

    @Override
    public TripAcceptanceLog save(TripAcceptanceForm tripAcceptanceForm, TripAcceptanceLog.TripAcceptanceStatus tripAcceptanceStatus) {
        RouteCheckDocument routeCheckDocument = routeCheckDocumentService.findById(tripAcceptanceForm.getDriverTripId())
                .orElseThrow(() -> new EntityNotFoundException(
                        String.format("%s entity with id %s was not found", RouteCheckDocument.class, tripAcceptanceForm.getDriverTripId())));


        if (tripAcceptanceStatus.equals(TripAcceptanceLog.TripAcceptanceStatus.ACCEPTED)) {
            routeCheckDocument.setTripStatus(RouteCheckDocument.TripStatus.ACCEPTED_BY_DRIVER);
        } else if (tripAcceptanceStatus.equals(TripAcceptanceLog.TripAcceptanceStatus.DECLINED)) {
            routeCheckDocument.setTripStatus(RouteCheckDocument.TripStatus.REJECTED_BY_DRIVER);
        }

        CustomUser driver = customUserDetailsService.findById(tripAcceptanceForm.getDriverId())
                .orElseThrow(() -> new EntityNotFoundException(
                        String.format("%s entity with id %s was not found", CustomUser.class, tripAcceptanceForm.getDriverId())));

        return tripAcceptanceRepository.save(TripAcceptanceLog.builder()
                .driver(driver)
                .routeCheckDocument(routeCheckDocument)
                .build());
    }
}
