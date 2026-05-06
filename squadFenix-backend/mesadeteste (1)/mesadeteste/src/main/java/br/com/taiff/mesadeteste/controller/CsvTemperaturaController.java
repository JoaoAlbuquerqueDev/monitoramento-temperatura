package br.com.taiff.mesadeteste.controller;

import br.com.taiff.mesadeteste.services.CsvExportService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class CsvTemperaturaController {

    private final CsvExportService csvExportService;

    public CsvTemperaturaController(CsvExportService csvExportService) {
        this.csvExportService = csvExportService;
    }

    @RequestMapping(path = "/temperaturacsv")
    public void getAllEmployeesInCsv(HttpServletResponse servletResponse) throws IOException {
        servletResponse.setContentType("text/csv");
        servletResponse.addHeader("Content-Disposition","attachment; filename=\"temperaturas.csv\"");
        csvExportService.writeEmployeesToCsv(servletResponse.getWriter());
    }

}
