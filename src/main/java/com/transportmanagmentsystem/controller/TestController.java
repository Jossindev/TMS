package com.transportmanagmentsystem.controller;

import com.itextpdf.html2pdf.ConverterProperties;
import com.itextpdf.html2pdf.HtmlConverter;
import com.transportmanagmentsystem.dto.CustomUserDTO;
import com.transportmanagmentsystem.entity.*;
import com.transportmanagmentsystem.repository.ComfortClassRepository;
import com.transportmanagmentsystem.repository.FuelRepository;
import com.transportmanagmentsystem.service.CustomUserDetailsService;
import com.transportmanagmentsystem.service.RouteCheckDocumentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping(ControllerConstants.Mapping.BUS_PARK_EMPLOYEES_WORK_CONTROL)
public class TestController {

    private static final TestInnerEntity TEST_INNER_ENTITY1 = TestInnerEntity.builder().id(1L).name("Test Inner Entity1").build();
    private static final TestInnerEntity TEST_INNER_ENTITY2 = TestInnerEntity.builder().id(2L).name("Test Inner Entity2").build();
    private static final TestInnerEntity TEST_INNER_ENTITY3 = TestInnerEntity.builder().id(3L).name("Test Inner Entity3").build();

    private static final TestInnerEntity2 TEST_INNER_ENTITY21 = TestInnerEntity2.builder().id(1L).name("Test Inner Entity21").build();
    private static final TestInnerEntity2 TEST_INNER_ENTITY22 = TestInnerEntity2.builder().id(2L).name("Test Inner Entity22").build();
    private static final TestInnerEntity2 TEST_INNER_ENTITY23 = TestInnerEntity2.builder().id(3L).name("Test Inner Entity23").build();

    private static final TestEntity TEST_ENTITY1 = TestEntity.builder().id(1L).name("Test Entity1").testInnerEntity(TEST_INNER_ENTITY1).testInnerEntity2(TEST_INNER_ENTITY21).build();
    private static final TestEntity TEST_ENTITY2 = TestEntity.builder().id(2L).name("Test Entity2").testInnerEntity(TEST_INNER_ENTITY2).testInnerEntity2(TEST_INNER_ENTITY22).build();
    private static final TestEntity TEST_ENTITY3 = TestEntity.builder().id(3L).name("Test Entity3").testInnerEntity(TEST_INNER_ENTITY3).testInnerEntity2(TEST_INNER_ENTITY23).build();

    private static final List<TestEntity> TEST_ENTITIES = Arrays.asList(TEST_ENTITY1, TEST_ENTITY2, TEST_ENTITY3);
    private static final List<TestInnerEntity> TEST_INNER_ENTITIES = Arrays.asList(TEST_INNER_ENTITY1, TEST_INNER_ENTITY2, TEST_INNER_ENTITY3);
    private static final List<TestInnerEntity2> TEST_INNER_ENTITY_2_S = Arrays.asList(TEST_INNER_ENTITY21, TEST_INNER_ENTITY22, TEST_INNER_ENTITY23);


    private final RouteCheckDocumentService routeCheckDocumentService;
    private final CustomUserDetailsService customUserDetailsService;
    private final FuelRepository fuelRepository;
    private final ComfortClassRepository comfortClassRepository;
    private final ServletContext servletContext;

    @GetMapping("/pdfhtml")
    public String showPDFinHTML(Model model) {
        model.addAttribute("nameList", Arrays.asList("Name 1", "Name 2", "Name 3"));
        return "route-check-document";
    }


    @GetMapping("/pdf")
    public ResponseEntity<?> getPDF(HttpServletRequest request, HttpServletResponse response) {

        WebContext context = new WebContext(request, response, servletContext);

        ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
        templateResolver.setPrefix("templates/");
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode(TemplateMode.HTML);
        templateResolver.setCharacterEncoding("UTF-8");

        TemplateEngine templateEngine = new TemplateEngine();
        templateEngine.setTemplateResolver(templateResolver);
        String testHtml = templateEngine.process("pdf-html", context);

        ByteArrayOutputStream target = new ByteArrayOutputStream();

        ConverterProperties converterProperties = new ConverterProperties();
        converterProperties.setBaseUri("http://localhost:8080");

        HtmlConverter.convertToPdf(testHtml, target, converterProperties);

        byte[] bytes = target.toByteArray();

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_PDF)
                .body(bytes);
    }

    @GetMapping("/testentity")
    public String showTestEntityPage(Model model) {
        model.addAttribute("testEntityList", TEST_ENTITIES);
        model.addAttribute("testInnerEntityList", TEST_INNER_ENTITIES);
        model.addAttribute("testInnerEntity2List", TEST_INNER_ENTITY_2_S);
        model.addAttribute("selectedTestEntity", TEST_ENTITIES.get(0));
        return "test-entity";
    }

    @GetMapping("/testentity/{testEntityId}")
    public String showUpdatedTestInnerEntity(@PathVariable("testEntityId") Long testEntityId, Model model) {
        TestEntity selectedTestEntity = TEST_ENTITIES.stream()
                .filter(testEntity -> testEntity.getId().equals(testEntityId))
                .findFirst()
                .orElseThrow(RuntimeException::new);

        model.addAttribute("testEntityList", TEST_ENTITIES);
        model.addAttribute("testInnerEntityList", TEST_INNER_ENTITIES);
        model.addAttribute("testInnerEntity2List", TEST_INNER_ENTITY_2_S);
        model.addAttribute("selectedTestEntity", selectedTestEntity);

        return "test-entity::test_inner_entity";
    }

    @GetMapping("/create-dispatcher")
    public String createDispatcher() {

        CustomUserDTO customUser = CustomUserDTO.builder()
                .email("bogdanboyko176@gmail.com")
                .password("1111")
                .role(CustomUser.CustomUserRole.DISPATCHER)
                .build();

        customUserDetailsService.save(customUser);
        fuelRepository.save(Fuel.builder().fuelType(Fuel.FuelType.BENZINE).price(new BigDecimal("31.98")).build());
        fuelRepository.save(Fuel.builder().fuelType(Fuel.FuelType.DIESEL).price(new BigDecimal("27.98")).build());
        comfortClassRepository.save(ComfortClass.builder().comfortClassType(ComfortClass.ComfortClassType.FIRST_CLASS).price(new BigDecimal("300")).build());
        comfortClassRepository.save(ComfortClass.builder().comfortClassType(ComfortClass.ComfortClassType.SECOND_CLASS).price(new BigDecimal("120")).build());
        return ControllerConstants.Mapping.LOGIN;
    }
}
