package cz.kul.spring_intoduction.controllers;

import cz.kul.spring_intoduction.services.CsvService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CsvController
{

    private final CsvService csvService;

    public CsvController(final CsvService csvService)
    {
        this.csvService = csvService;
    }

    @GetMapping("/csv/to-json")
    public String csvToJson(
        @RequestBody String csv,
        @RequestParam(required = false) Character delimiter)
    {
        return csvService.csvToJson(csv, delimiter);
    }

}
