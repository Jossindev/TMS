package com.transportmanagmentsystem.controller;

import com.transportmanagmentsystem.dto.BusDTO;
import com.transportmanagmentsystem.dto.BusServiceLogDTO;
import com.transportmanagmentsystem.service.BusService;
import com.transportmanagmentsystem.service.BusServiceLogService;
import com.transportmanagmentsystem.service.ComfortClassService;
import com.transportmanagmentsystem.service.FuelService;
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
public class BusInfoPageController {

    private static final String INFO_SAVED_ATTRIBUTE_KEY = "infoSaved";

    private static final String FUEL_LIST_MODEL_PARAM = "fuelList";
    private static final String COMFORT_CLASS_LIST_MODEL_PARAM = "comfortClassList";
    private static final String BUS_LIST = "busList";


    private final BusService busService;
    private final FuelService fuelService;
    private final ComfortClassService comfortClassService;
    private final BusServiceLogService busServiceLogService;

    @GetMapping(value = ControllerConstants.Mapping.BUS_INFO_PERFORMING)
    public String showBusInfoPerformingPage(@ModelAttribute BusDTO busDTO, Model model) {
        loadBusInfoPerformingPageModel(model);
        return ControllerConstants.Pages.BUS_INFO_PERFORMING;
    }

    @PostMapping(ControllerConstants.Mapping.SAVE_BUS_INFO)
    public String saveBusInfo(@Valid @ModelAttribute BusDTO busDTO, BindingResult bindingResult, Model model,
                              RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            loadBusInfoPerformingPageModel(model);
            return ControllerConstants.Pages.BUS_INFO_PERFORMING;
        }

        busService.save(busDTO);
        redirectAttributes.addFlashAttribute(ControllerConstants.Model.INFO_SAVED_ATTRIBUTE_KEY, true);
        return ControllerConstants.REDIRECT + ControllerConstants.Mapping.BUS_PARK_EMPLOYEES_WORK_CONTROL
                + ControllerConstants.Mapping.BUS_INFO_PERFORMING;
    }

    @GetMapping(ControllerConstants.Mapping.BUS_SERVICE_LOG_PERFORMING)
    public String showBusInfoPerformingPage(@ModelAttribute BusServiceLogDTO busServiceLogDTO, Model model) {
        loadBusServiceLogPageModel(model);
        return ControllerConstants.Pages.BUS_SERVICE_LOG_PERFORMING;
    }

    @PostMapping(ControllerConstants.Mapping.SAVE_BUS_SERVICE_LOG_NOTE)
    public String saveBusInfo(@Valid @ModelAttribute BusServiceLogDTO busServiceLogDTO, BindingResult bindingResult,
                              Model model, RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            loadBusServiceLogPageModel(model);
            return ControllerConstants.Pages.BUS_SERVICE_LOG_PERFORMING;
        }

        busServiceLogService.save(busServiceLogDTO);
        redirectAttributes.addFlashAttribute(ControllerConstants.Model.INFO_SAVED_ATTRIBUTE_KEY, true);
        return ControllerConstants.REDIRECT + ControllerConstants.Mapping.BUS_PARK_EMPLOYEES_WORK_CONTROL
                + ControllerConstants.Mapping.BUS_SERVICE_LOG_PERFORMING;
    }

    private void loadBusInfoPerformingPageModel(Model model) {
        model.addAttribute(FUEL_LIST_MODEL_PARAM, fuelService.findAll());
        model.addAttribute(COMFORT_CLASS_LIST_MODEL_PARAM, comfortClassService.findAll());
    }

    private void loadBusServiceLogPageModel(Model model) {
        model.addAttribute(BUS_LIST, busService.findAll());
    }
}
