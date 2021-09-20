package com.transportmanagmentsystem.controller;

import com.transportmanagmentsystem.dto.RouteDTO;
import com.transportmanagmentsystem.entity.Route;
import com.transportmanagmentsystem.entity.Schedule;
import com.transportmanagmentsystem.service.RouteService;
import com.transportmanagmentsystem.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping(ControllerConstants.Mapping.BUS_PARK_EMPLOYEES_WORK_CONTROL)
public class RouteInfoPageController {

    private final RouteService routeService;
    private final ScheduleService scheduleService;

    @GetMapping(ControllerConstants.Mapping.ROUTE_INFO_PERFORMING)
    public String showRouteInfoPerformingPage(@ModelAttribute RouteDTO routeDTO) {
        return ControllerConstants.Pages.ROUTE_INFO_PERFORMING;
    }

    @PostMapping(ControllerConstants.Mapping.SAVE_ROUTE_INFO)
    public String saveRouteInfo(@Valid @ModelAttribute RouteDTO routeDTO, BindingResult bindingResult,
                                RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            return ControllerConstants.Pages.ROUTE_INFO_PERFORMING;
        }

        routeService.save(routeDTO);
        redirectAttributes.addFlashAttribute(ControllerConstants.Model.INFO_SAVED_ATTRIBUTE_KEY, true);
        return ControllerConstants.REDIRECT + ControllerConstants.Mapping.BUS_PARK_EMPLOYEES_WORK_CONTROL
                + ControllerConstants.Mapping.ROUTE_INFO_PERFORMING;
    }

    @GetMapping(ControllerConstants.Mapping.ROUTE_INFO_PERFORMING + "/{scheduleId}")
    public ResponseEntity<Route> findRoute(@PathVariable("scheduleId") Long scheduleId) {
        Optional<Schedule> scheduleOptional = scheduleService.findById(scheduleId);
        return scheduleOptional.map(schedule -> new ResponseEntity<>(schedule.getRoute(), HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.BAD_REQUEST));
    }
}
