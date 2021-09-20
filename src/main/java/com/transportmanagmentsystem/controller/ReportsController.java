package com.transportmanagmentsystem.controller;

import com.itextpdf.html2pdf.ConverterProperties;
import com.itextpdf.html2pdf.HtmlConverter;
import com.transportmanagmentsystem.dto.FuelAmountCostByRouteDTO;
import com.transportmanagmentsystem.dto.FuelAmountCostReportForm;
import com.transportmanagmentsystem.dto.WorkedHoursReportForm;
import com.transportmanagmentsystem.dto.WorkedHoursRouteDTO;
import com.transportmanagmentsystem.entity.CustomUser;
import com.transportmanagmentsystem.entity.Route;
import com.transportmanagmentsystem.service.CustomUserDetailsService;
import com.transportmanagmentsystem.service.RouteService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.math.BigDecimal;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping(ControllerConstants.Mapping.BUS_PARK_EMPLOYEES_WORK_CONTROL)
public class ReportsController {

    private final ServletContext servletContext;
    private final RouteService routeService;
    private final CustomUserDetailsService customUserDetailsService;

    @GetMapping("/reports")
    public String showReportsPage(@ModelAttribute FuelAmountCostReportForm fuelAmountCostReportForm,
                                  @ModelAttribute WorkedHoursReportForm workedHoursReportForm,
                                  Model model) {
        List<Route> routes = routeService.findAll();
        List<CustomUser> drivers = customUserDetailsService.findAllByRole(CustomUser.CustomUserRole.DRIVER);
        model.addAttribute("routeList", routes);
        model.addAttribute("driverList", drivers);
        return "reports";
    }

    @GetMapping("/fuel-amount-cost-by-route")
    public ResponseEntity<?> getFuelAmountCostByRoutePDF(HttpServletRequest request, HttpServletResponse response,
                                    @ModelAttribute FuelAmountCostReportForm fuelAmountCostReportForm) {

        Route route = routeService.findById(fuelAmountCostReportForm.getRouteId())
                .orElseThrow(RuntimeException::new);

        FuelAmountCostByRouteDTO fuelAmountCostByRouteDTO = new FuelAmountCostByRouteDTO();
        fuelAmountCostByRouteDTO.setDateFrom(fuelAmountCostReportForm.getDateFrom().toLocalDate());
        fuelAmountCostByRouteDTO.setDateTo(fuelAmountCostReportForm.getDateTo().toLocalDate());
        fuelAmountCostByRouteDTO.setRouteName(route.getName());
        fuelAmountCostByRouteDTO.setRouteNumber(route.getNumber());
        fuelAmountCostByRouteDTO.setDistance(route.getDistance());
        fuelAmountCostByRouteDTO.setBenzineUsedAmount(2337f);
        fuelAmountCostByRouteDTO.setBenzineUsedCost(new BigDecimal(70110));
        fuelAmountCostByRouteDTO.setDieselUsedAmount(3669f);
        fuelAmountCostByRouteDTO.setDieselUsedCost(new BigDecimal("103906.08"));
        fuelAmountCostByRouteDTO.setFuelUsedAmountTotal(6006f);
        fuelAmountCostByRouteDTO.setFuelUsedCostAmountTotal(new BigDecimal("174016.08"));


        WebContext context = new WebContext(request, response, servletContext);
        context.setVariable("fuelAmountCostByRouteDTO", fuelAmountCostByRouteDTO);

        ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
        templateResolver.setPrefix("templates/");
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode(TemplateMode.HTML);
        templateResolver.setCharacterEncoding("UTF-8");

        TemplateEngine templateEngine = new TemplateEngine();
        templateEngine.setTemplateResolver(templateResolver);
        String testHtml = templateEngine.process("fuel-cost-amount-report", context);

        ByteArrayOutputStream target = new ByteArrayOutputStream();

        ConverterProperties converterProperties = new ConverterProperties();

        HtmlConverter.convertToPdf(testHtml, target, converterProperties);

        byte[] bytes = target.toByteArray();

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_PDF)
                .body(bytes);
    }

    @GetMapping("/worked-hours")
    public ResponseEntity<?> getWorkedHoursByDriverPDF(HttpServletRequest request, HttpServletResponse response,
                                    @ModelAttribute WorkedHoursReportForm workedHoursReportForm) {

        CustomUser customUser = customUserDetailsService.findById(workedHoursReportForm.getDriverId())
                .orElseThrow(RuntimeException::new);

        WorkedHoursRouteDTO workedHoursRouteDTO = new WorkedHoursRouteDTO();
        workedHoursRouteDTO.setDateFrom(workedHoursReportForm.getDateFrom1().toLocalDate());
        workedHoursRouteDTO.setTimeFrom(workedHoursReportForm.getDateFrom1().toLocalTime());
        workedHoursRouteDTO.setDateTo(workedHoursReportForm.getDateTo1().toLocalDate());
        workedHoursRouteDTO.setTimeTo(workedHoursReportForm.getDateTo1().toLocalTime());
        workedHoursRouteDTO.setName(customUser.getFullName());

        WebContext context = new WebContext(request, response, servletContext);
        context.setVariable("workedHoursRouteDTO", workedHoursRouteDTO);

        ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
        templateResolver.setPrefix("templates/");
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode(TemplateMode.HTML);
        templateResolver.setCharacterEncoding("UTF-8");

        TemplateEngine templateEngine = new TemplateEngine();
        templateEngine.setTemplateResolver(templateResolver);
        String testHtml = templateEngine.process("worked-hours-report", context);

        ByteArrayOutputStream target = new ByteArrayOutputStream();

        ConverterProperties converterProperties = new ConverterProperties();

        HtmlConverter.convertToPdf(testHtml, target, converterProperties);

        byte[] bytes = target.toByteArray();

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_PDF)
                .body(bytes);
    }
}
