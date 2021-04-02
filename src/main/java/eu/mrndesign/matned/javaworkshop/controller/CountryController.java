package eu.mrndesign.matned.javaworkshop.controller;

import eu.mrndesign.matned.javaworkshop.dto.CountryDisplayDTO;
import eu.mrndesign.matned.javaworkshop.service.CountryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.rmi.ServerError;

@RestController
@RequestMapping()
public class CountryController {

    private final CountryService countryService;

    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @GetMapping("/{code}")
    public CountryDisplayDTO cityByCode(@PathVariable String code) throws ServerError {
        return countryService.findByCountryCode(code);
    }
}
