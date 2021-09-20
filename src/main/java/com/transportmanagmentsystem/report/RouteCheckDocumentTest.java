//
//
//import com.itextpdf.io.font.PdfEncodings;
//import com.itextpdf.kernel.font.PdfFont;
//import com.itextpdf.kernel.font.PdfFontFactory;
//import com.itextpdf.kernel.pdf.PdfDocument;
//import com.itextpdf.kernel.pdf.PdfWriter;
//import com.itextpdf.layout.Document;
//import com.itextpdf.layout.element.Paragraph;
//import com.itextpdf.layout.element.Table;
//import com.itextpdf.layout.element.Text;
//import com.itextpdf.layout.property.TextAlignment;
//import com.itextpdf.layout.property.UnitValue;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.core.env.Environment;
//import org.springframework.stereotype.Component;
//
//import java.io.ByteArrayInputStream;
//import java.io.ByteArrayOutputStream;
//import java.io.IOException;
//import java.math.BigDecimal;
//import java.time.format.DateTimeFormatter;
//
//@Component
//public class RouteCheckDocumentTest {
//
//    @Autowired
//    private Environment environment;
//
//    private static final String TIMES_NEW_ROMAN_FONT_PATH = "./src/main/resources/fonts/times-new-roman.ttf";
//    private static final String SIGNATURE_UNDERLINE = "___________________________________________";
//    private static final String SPACE = " ";
//    private static final String LINE_SEPARATOR = System.lineSeparator();
//    private static final String DATE_FORMAT = "dd-MM-yyyy";
//    private static final String TIME_FORMAT = "HH:mm";
//
//    public ByteArrayInputStream generateRouteCheckDocument(RouteCheckDocument routeCheckDocument) throws IOException {
//        ByteArrayOutputStream out = new ByteArrayOutputStream();
//        PdfWriter pdfWriter = new PdfWriter(out);
//        PdfDocument pdfDocument = new PdfDocument(pdfWriter);
//        PdfFont pdfFont = PdfFontFactory.createFont(TIMES_NEW_ROMAN_FONT_PATH, PdfEncodings.IDENTITY_H);
//        Document document = new Document(pdfDocument);
//
//        addTitleSection(document, pdfFont, routeCheckDocument);
//        addEmptyLines(document, 10);
//        addTopSection(document, pdfFont, routeCheckDocument);
//        addEmptyLines(document, 5);
//        addRouteInfoSection(document, pdfFont, routeCheckDocument);
//        addEmptyLines(document, 5);
//        addFuelInfoSection(document, pdfFont, routeCheckDocument);
//        addEmptyLines(document, 5);
//        addSignatureSection(document, pdfFont, routeCheckDocument);
//        document.close();
//        return new ByteArrayInputStream(out.toByteArray());
//    }
//
//    private void addTitleSection(Document document, PdfFont font, RouteCheckDocument routeCheckDocument) {
//        Paragraph paragraph = new Paragraph();
//        Text text = new Text(environment.getProperty("routedocument.title") + SPACE + routeCheckDocument.getId());
//        text.setFont(font);
//        text.setFontSize(18);
//        text.setBold();
//        paragraph.add(text);
//        paragraph.setTextAlignment(TextAlignment.CENTER);
//        document.add(paragraph);
//    }
//
//    private void addTopSection(Document document, PdfFont font, RouteCheckDocument routeCheckDocument) {
//        Paragraph fullNameParagraph = new Paragraph();
//        Text fullNameLabel = new Text(environment.getProperty("routedocument.employeefullname"));
//        Text fullNameValue = new Text(routeCheckDocument.getDriver().getFullName());
//        fullNameLabel.setFont(font);
//        fullNameValue.setFont(font);
//        addSpaces(fullNameLabel, 0, 10);
//        fullNameValue.setUnderline();
//        fullNameParagraph.add(fullNameLabel);
//        fullNameParagraph.add(fullNameValue);
//
//        Paragraph employeePositionParagraph = new Paragraph();
//        Text employeePositionLabel = new Text(environment.getProperty("routedocument.employeeposition"));
//        Text employeePositionValue = new Text("водій");
//        employeePositionLabel.setFont(font);
//        employeePositionValue.setFont(font);
//        addSpaces(employeePositionLabel, 0, 10);
//        employeePositionValue.setUnderline();
//        employeePositionParagraph.add(employeePositionLabel);
//        employeePositionParagraph.add(employeePositionValue);
//
//        Paragraph busNameParagraph = new Paragraph();
//        Text busNameLabel = new Text(environment.getProperty("routedocument.busname"));
//        Text busNameValue = new Text(routeCheckDocument.getBus().getName());
//        busNameLabel.setFont(font);
//        busNameValue.setFont(font);
//        addSpaces(busNameLabel, 0, 10);
//        busNameValue.setUnderline();
//        busNameParagraph.add(busNameLabel);
//        busNameParagraph.add(busNameValue);
//
//        document.add(fullNameParagraph);
//        document.add(employeePositionParagraph);
//        document.add(busNameParagraph);
//    }
//
//    private void addRouteInfoSection(Document document, PdfFont font, RouteCheckDocument routeCheckDocument) {
//        float[] pointColumnWidths = {200F, 200F, 200F, 200F, 200F};
//        Table table = new Table(pointColumnWidths).setMinHeight(100F);
//
//        table.addCell(new Paragraph(new Text("Маршрут № " + routeCheckDocument.getSchedule().getRoute().getName()).setFont(font)));
//        table.addCell(new Paragraph(new Text("Дата та час відправлення").setFont(font)));
//        table.addCell(new Paragraph(new Text("Дата та час прибуття").setFont(font)));
//        table.addCell(new Paragraph(new Text("Загальна кількість місць").setFont(font)));
//        table.addCell(new Paragraph(new Text("Кількість зайнятих місць").setFont(font)));
//
//        table.addCell(new Paragraph(new Text(routeCheckDocument.getSchedule().getRoute().getName()).setFont(font)));
////        table.addCell(new Paragraph(new Text(routeCheckDocument.getSchedule().getDepartureDate()
////                .format(DateTimeFormatter.ofPattern(DATE_FORMAT)) + LINE_SEPARATOR +
////                routeCheckDocument.getSchedule().getDepartureTime().format(DateTimeFormatter.ofPattern(TIME_FORMAT))).setFont(font)));
////        table.addCell(new Paragraph(new Text(routeCheckDocument.getSchedule().getArrivalDate()
////                .format(DateTimeFormatter.ofPattern(DATE_FORMAT)) + LINE_SEPARATOR +
////                routeCheckDocument.getSchedule().getArrivalTime().format(DateTimeFormatter.ofPattern(TIME_FORMAT))).setFont(font)));
//
//        table.addCell(new Paragraph(new Text("Hardcode").setFont(font)));
//        table.addCell(new Paragraph(new Text("Hardcode").setFont(font)));
//        table.addCell(new Paragraph(new Text("56").setFont(font)));
//        table.addCell(new Paragraph(new Text("46").setFont(font)));
//
//        document.add(table);
//    }
//
//    private void addFuelInfoSection(Document document, PdfFont font, RouteCheckDocument routeCheckDocument) {
//        float[] pointColumnWidths = {200F, 200F, 200F, 200F};
//        Table table = new Table(pointColumnWidths).setMinHeight(100F);
//
//        table.addCell(new Paragraph(new Text("Загальна відстань").setFont(font)));
//        table.addCell(new Paragraph(new Text("Витрати палива").setFont(font)));
//        table.addCell(new Paragraph(new Text("Кількість заправленого палива").setFont(font)));
//        table.addCell(new Paragraph(new Text("Загальні витрати на наливо").setFont(font)));
//
//        table.addCell(new Paragraph(new Text("HARDCODE").setFont(font)));//HARDCODE!!!!!!!!!!!!!!
//        table.addCell(new Paragraph(new Text(routeCheckDocument.getBus().getFuelConsumption() + " л на 100 км").setFont(font)));
////        table.addCell(new Paragraph(new Text(routeCheckDocument.getFilledFuel().toString()).setFont(font)));
//        table.addCell(new Paragraph(new Text("HARDCODE").setFont(font)));
////        table.addCell(new Paragraph(new Text(routeCheckDocument.getBus().getFuel().getPrice()
////                .multiply(BigDecimal.valueOf(routeCheckDocument.getFilledFuel())).toString()).setFont(font)));
//        table.addCell(new Paragraph(new Text("HARDCODE").setFont(font)));
//        document.add(table);
//    }
//
//    public void addSignatureSection(Document document, PdfFont font, RouteCheckDocument routeCheckDocument) {
//        Paragraph driverSignatureParagraph = new Paragraph();
//        Text driverSignatureLabel = new Text(environment.getProperty("routedocument.driversignature"));
//        Text driverSignatureValue = new Text(SIGNATURE_UNDERLINE);
//        driverSignatureLabel.setFont(font);
//        driverSignatureValue.setFont(font);
//        addSpaces(driverSignatureLabel, 0, 10);
//        driverSignatureParagraph.add(driverSignatureLabel);
//        driverSignatureParagraph.add(driverSignatureValue);
//
//        Paragraph dispatcherSignatureParagraph = new Paragraph();
//        Text dispatcherSignatureLabel = new Text(environment.getProperty("routedocument.dispatchersignature"));
//        Text dispatcherSignatureValue = new Text(SIGNATURE_UNDERLINE);
//        dispatcherSignatureLabel.setFont(font);
//        dispatcherSignatureValue.setFont(font);
//        addSpaces(dispatcherSignatureLabel, 0, 10);
//        dispatcherSignatureParagraph.add(dispatcherSignatureLabel);
//        dispatcherSignatureParagraph.add(dispatcherSignatureValue);
//
//        document.add(driverSignatureParagraph);
//        addEmptyLines(document, 2);
//        document.add(dispatcherSignatureParagraph);
//    }
//
//    private void addEmptyLines(Document document, long lineNumber) {
//        for (int i = 0; i < lineNumber; i++) {
//            document.add(new Paragraph(SPACE));
//        }
//    }
//
//    private void addSpaces(Text text, int numberOfLeftSpaces, int numberOfRightSpaces) {
//        StringBuilder leftSpaces = new StringBuilder();
//        StringBuilder rightSpaces = new StringBuilder();
//        for (int i = 0; i < numberOfLeftSpaces; i++) {
//            leftSpaces.append(" ");
//        }
//        for (int i = 0; i < numberOfRightSpaces; i++) {
//            rightSpaces.append(SPACE);
//        }
//        text.setText(leftSpaces.append(text.getText()).append(rightSpaces).toString());
//    }
//}
