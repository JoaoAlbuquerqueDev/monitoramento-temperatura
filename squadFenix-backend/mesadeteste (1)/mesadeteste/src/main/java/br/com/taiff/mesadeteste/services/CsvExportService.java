package br.com.taiff.mesadeteste.services;


import br.com.taiff.mesadeteste.model.Temperatura;
import br.com.taiff.mesadeteste.repository.TemperaturaRepository;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.jboss.logging.Logger;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.Writer;
import java.util.List;

import static org.jboss.logging.Logger.getLogger;

@Service
public class CsvExportService {

    private static final Logger log = getLogger(CsvExportService.class);

    private final TemperaturaRepository employeeRepository;

    public CsvExportService(TemperaturaRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public void writeEmployeesToCsv(Writer writer) {

        List<Temperatura> employees = employeeRepository.findAll();
        try (CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT)) {
           csvPrinter.printRecord("ID", "TermoPar1","TermoPar2","TermoPar3", "TermoParAmbiente","Data Teste");
            for (Temperatura employee : employees) {
                csvPrinter.printRecord(employee.getId(), employee.getT1(), employee.getT2(), employee.getT3(), employee.getTAmbiente(), employee.getDataTeste());
            }
        } catch (IOException e) {
            log.error("Error While writing CSV ", e);
        }
    }

    }

