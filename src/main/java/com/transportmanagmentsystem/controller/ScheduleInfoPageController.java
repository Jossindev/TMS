package com.transportmanagmentsystem.controller;

import com.transportmanagmentsystem.dto.ScheduleDTO;
import com.transportmanagmentsystem.service.RouteService;
import com.transportmanagmentsystem.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
@RequestMapping(ControllerConstants.Mapping.BUS_PARK_EMPLOYEES_WORK_CONTROL)
public class ScheduleInfoPageController {

    private static final String ROUTE_LIST = "routeList";

    private final RouteService routeService;
    private final ScheduleService scheduleService;

    @GetMapping(ControllerConstants.Mapping.SCHEDULE_INFO_PERFORMING)
    public String showScheduleInfoPerformingPage(@ModelAttribute ScheduleDTO scheduleDTO, Model model) {
        loadScheduleInfoPerformingPageModel(model);
        return ControllerConstants.Pages.SCHEDULE_INFO_PERFORMING;
    }

    @PostMapping(ControllerConstants.Mapping.SAVE_SCHEDULE_INFO)
    public String saveBusInfo(@Valid @ModelAttribute ScheduleDTO scheduleDTO, BindingResult bindingResult, Model model,
                              RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            loadScheduleInfoPerformingPageModel(model);
            return ControllerConstants.Pages.SCHEDULE_INFO_PERFORMING;
        }

        scheduleService.save(scheduleDTO);
        redirectAttributes.addFlashAttribute(ControllerConstants.Model.INFO_SAVED_ATTRIBUTE_KEY, true);
        return ControllerConstants.REDIRECT + ControllerConstants.Mapping.BUS_PARK_EMPLOYEES_WORK_CONTROL
                + ControllerConstants.Mapping.SCHEDULE_INFO_PERFORMING;
    }

    private void loadScheduleInfoPerformingPageModel(Model model) {
        model.addAttribute(ROUTE_LIST, routeService.findAll());
    }
}
