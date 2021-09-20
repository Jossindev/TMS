package com.transportmanagmentsystem.controller;

import com.itextpdf.html2pdf.ConverterProperties;
import com.itextpdf.html2pdf.HtmlConverter;
import com.transportmanagmentsystem.dto.RouteCheckDocumentDTO;
import com.transportmanagmentsystem.dto.RouteCheckDocumentUpdateDTO;
import com.transportmanagmentsystem.entity.CustomUser;
import com.transportmanagmentsystem.entity.RouteCheckDocument;
import com.transportmanagmentsystem.exception.EntityNotFoundException;
import com.transportmanagmentsystem.service.BusService;
import com.transportmanagmentsystem.service.CustomUserDetailsService;
import com.transportmanagmentsystem.service.RouteCheckDocumentService;
import com.transportmanagmentsystem.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.ByteArrayOutputStream;
import java.util.Arrays;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping(ControllerConstants.Mapping.BUS_PARK_EMPLOYEES_WORK_CONTROL)
public class TripInfoPerformingController {

    private static final String DRIVER_LIST = "driverList";
    private static final String BUS_LIST = "busList";
    private static final String SCHEDULE_LIST = "scheduleList";
    private static final String TRIP_LIST = "tripList";
    private static final String SELECTED_TRIP = "selectedTrip";
    private static final String TRIP_STATUS_LIST = "tripStatusList";

    private final CustomUserDetailsService customUserDetailsService;
    private final BusService busService;
    private final ScheduleService scheduleService;
    private final RouteCheckDocumentService routeCheckDocumentService;
    private final ServletContext servletContext;

    @GetMapping(ControllerConstants.Mapping.TRIP_INFO_PREFORMING)
    public String showTripInfoPerformingPage(@ModelAttribute("routeCheckDocumentDTO")
                                                     RouteCheckDocumentDTO routeCheckDocumentDTO, Model model) {
        loadTripInfoPerformingPageModel(model);
        return ControllerConstants.Pages.TRIP_INFO_PERFORMING;
    }

    @GetMapping(ControllerConstants.Mapping.TRIP_INFO_PREFORMING + "/update")
    public String showTripInfoPerformingUpdatePage(@ModelAttribute("routeCheckDocumentUpdateDTO")
                                                     RouteCheckDocumentUpdateDTO routeCheckDocumentUpdateDTO, Model model) {
        loadTripInfoUpdatePageModel(model);
        return "trip-info-update";
    }

    @GetMapping(ControllerConstants.Mapping.TRIP_INFO_PREFORMING + "/{tripId}")
    public String showSelectedTripFragment(@PathVariable("tripId") Long tripId, Model model,
                                           @ModelAttribute("routeCheckDocumentUpdateDTO")
                                           RouteCheckDocumentUpdateDTO routeCheckDocumentUpdateDTO) {
        loadTripInfoUpdateFragmentModel(model, tripId);
        return "trip-info-update::tripUpdateFragment";
    }

    @PostMapping(ControllerConstants.Mapping.SAVE_TRIP_INFO)
    public String saveBusInfo(@Valid @ModelAttribute RouteCheckDocumentDTO routeCheckDocumentDTO,
                              BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            loadTripInfoPerformingPageModel(model);
            return ControllerConstants.Pages.TRIP_INFO_PERFORMING;
        }

        routeCheckDocumentService.save(routeCheckDocumentDTO);
        redirectAttributes.addFlashAttribute(ControllerConstants.Model.INFO_SAVED_ATTRIBUTE_KEY, true);
        return ControllerConstants.REDIRECT + ControllerConstants.Mapping.BUS_PARK_EMPLOYEES_WORK_CONTROL
                + ControllerConstants.Mapping.TRIP_INFO_PREFORMING;
    }

    @PostMapping(ControllerConstants.Mapping.UPDATE_TRIP_INFO)
    public String updateTripInfo(@Valid @ModelAttribute("routeCheckDocumentUpdateDTO") RouteCheckDocumentUpdateDTO routeCheckDocumentUpdateDTO,
                                 BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            loadTripInfoUpdatePageModel(model);
            return "trip-info-update";
        }

        routeCheckDocumentService.update(routeCheckDocumentUpdateDTO);
        redirectAttributes.addFlashAttribute(ControllerConstants.Model.INFO_UPDATED_ATTRIBUTE_KEY, true);
        return ControllerConstants.REDIRECT + ControllerConstants.Mapping.BUS_PARK_EMPLOYEES_WORK_CONTROL
                + "/tripinfopreforming/update";
    }

    @GetMapping(ControllerConstants.Mapping.TRIP_INFO_PREFORMING + "/route-list-download/{tripId}")
    public ResponseEntity<?> getPDF(HttpServletRequest request, HttpServletResponse response,
                                    @PathVariable("tripId") Long tripId) {

        RouteCheckDocument routeCheckDocument = routeCheckDocumentService.findById(tripId)
                .orElseThrow(RuntimeException::new);

        WebContext context = new WebContext(request, response, servletContext);
        context.setVariable("routeCheckDocument", routeCheckDocument);

        ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
        templateResolver.setPrefix("templates/");
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode(TemplateMode.HTML);
        templateResolver.setCharacterEncoding("UTF-8");

        TemplateEngine templateEngine = new TemplateEngine();
        templateEngine.setTemplateResolver(templateResolver);
        String testHtml = templateEngine.process("route-check-document", context);

        ByteArrayOutputStream target = new ByteArrayOutputStream();

        ConverterProperties converterProperties = new ConverterProperties();

        HtmlConverter.convertToPdf(testHtml, target, converterProperties);

        byte[] bytes = target.toByteArray();

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_PDF)
                .body(bytes);
    }

    private void loadTripInfoUpdateFragmentModel(Model model, Long tripId) {
        RouteCheckDocument selectedTrip = routeCheckDocumentService.findById(tripId)
                .orElseThrow(EntityNotFoundException::new);
        model.addAttribute(DRIVER_LIST, customUserDetailsService.findAllByRole(CustomUser.CustomUserRole.DRIVER));
        model.addAttribute(BUS_LIST, busService.findAll());
        model.addAttribute(TRIP_STATUS_LIST, Arrays.asList(RouteCheckDocument.TripStatus.values()));
        model.addAttribute(SELECTED_TRIP, selectedTrip);
        model.addAttribute(TRIP_LIST, routeCheckDocumentService.findAll());
        RouteCheckDocumentUpdateDTO routeCheckDocumentUpdateDTO = (RouteCheckDocumentUpdateDTO) model.getAttribute("routeCheckDocumentUpdateDTO");
        routeCheckDocumentUpdateDTO.setFilledFuelUpdate(selectedTrip.getFilledFuel());
        routeCheckDocumentUpdateDTO.setSoldSeatUpdate(selectedTrip.getSoldSeat());
    }

    private void loadTripInfoPerformingPageModel(Model model) {
        model.addAttribute(DRIVER_LIST, customUserDetailsService.findAllByRole(CustomUser.CustomUserRole.DRIVER));
        model.addAttribute(BUS_LIST, busService.findAll());
        model.addAttribute(SCHEDULE_LIST, scheduleService.findAll());
        List<RouteCheckDocument> trips = routeCheckDocumentService.findAll();
        model.addAttribute(TRIP_LIST, trips);
    }

    private void loadTripInfoUpdatePageModel(Model model) {
        model.addAttribute(DRIVER_LIST, customUserDetailsService.findAllByRole(CustomUser.CustomUserRole.DRIVER));
        model.addAttribute(BUS_LIST, busService.findAll());
        model.addAttribute(SCHEDULE_LIST, scheduleService.findAll());
        List<RouteCheckDocument> trips = routeCheckDocumentService.findAll();
        model.addAttribute(TRIP_LIST, trips);
        model.addAttribute(SELECTED_TRIP, null);
        if (!trips.isEmpty()) {
            model.addAttribute(SELECTED_TRIP, trips.get(0));
            RouteCheckDocumentUpdateDTO routeCheckDocumentUpdateDTO = (RouteCheckDocumentUpdateDTO) model.getAttribute("routeCheckDocumentUpdateDTO");
            routeCheckDocumentUpdateDTO.setFilledFuelUpdate(trips.get(0).getFilledFuel());
            routeCheckDocumentUpdateDTO.setSoldSeatUpdate(trips.get(0).getSoldSeat());
        }
        model.addAttribute(TRIP_STATUS_LIST, Arrays.asList(RouteCheckDocument.TripStatus.values()));
    }
}
