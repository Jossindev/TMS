package com.transportmanagmentsystem.controller;

import com.transportmanagmentsystem.dto.TripAcceptanceForm;
import com.transportmanagmentsystem.entity.CustomUser;
import com.transportmanagmentsystem.entity.RouteCheckDocument;
import com.transportmanagmentsystem.entity.TripAcceptanceLog;
import com.transportmanagmentsystem.service.CustomUserDetailsService;
import com.transportmanagmentsystem.service.RouteCheckDocumentService;
import com.transportmanagmentsystem.service.TripAcceptanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping(ControllerConstants.Mapping.BUS_PARK_EMPLOYEES_WORK_CONTROL)
public class TripAcceptancePageController {

    private static final String DRIVER_TRIP_LIST = "driverTripList";

    private final RouteCheckDocumentService routeCheckDocumentService;
    private final CustomUserDetailsService customUserDetailsService;
    private final TripAcceptanceService tripAcceptanceService;

    @GetMapping(ControllerConstants.Mapping.DRIVER_ASSIGNED_TRIP_LIST)
    public String showDriverAssignedTripRouteListPage(Model model) {
        CustomUser currentSessionDriver = customUserDetailsService.findCurrentSessionDriver();
        List<RouteCheckDocument> driverTripList = routeCheckDocumentService
                .findAllByDriverAndTripStatus(currentSessionDriver, RouteCheckDocument.TripStatus.DRIVER_ACCEPTANCE_WAITING);
        model.addAttribute(DRIVER_TRIP_LIST, driverTripList);
        return "driver-assigned-trip-list";
    }

    @PostMapping(ControllerConstants.Mapping.TRIP_INFO_PREFORMING + "/cancel")
    public String cancelTrip(@RequestBody TripAcceptanceForm tripAcceptanceForm) {
        tripAcceptanceService.save(tripAcceptanceForm, TripAcceptanceLog.TripAcceptanceStatus.DECLINED);
        return ControllerConstants.REDIRECT + ControllerConstants.Mapping.BUS_PARK_EMPLOYEES_WORK_CONTROL +
                ControllerConstants.Mapping.DRIVER_ASSIGNED_TRIP_LIST;
    }

    @GetMapping(ControllerConstants.Mapping.DRIVER_ACCEPTED_TRIP_LIST)
    public String showDriverAcceptedTripRouteListPage(Model model) {
        CustomUser currentSessionDriver = customUserDetailsService.findCurrentSessionDriver();
        List<RouteCheckDocument> driverTripList = routeCheckDocumentService
                .findAllByDriverAndTripStatus(currentSessionDriver, RouteCheckDocument.TripStatus.ACCEPTED_BY_DRIVER);
        model.addAttribute(DRIVER_TRIP_LIST, driverTripList);
        return "driver-accepted-trip-list";
    }

    @PostMapping(ControllerConstants.Mapping.TRIP_INFO_PREFORMING + "/accept")
    public String acceptTrip(@RequestBody TripAcceptanceForm tripAcceptanceForm) {
        tripAcceptanceService.save(tripAcceptanceForm, TripAcceptanceLog.TripAcceptanceStatus.ACCEPTED);
        return ControllerConstants.REDIRECT + ControllerConstants.Mapping.BUS_PARK_EMPLOYEES_WORK_CONTROL +
                ControllerConstants.Mapping.DRIVER_ASSIGNED_TRIP_LIST;
    }

}
