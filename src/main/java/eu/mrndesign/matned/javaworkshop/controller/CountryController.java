package eu.mrndesign.matned.javaworkshop.controller;

import eu.mrndesign.matned.javaworkshop.dto.CountryDisplayDTO;
import eu.mrndesign.matned.javaworkshop.service.CountryService;
import org.springframework.web.bind.annotation.*;

import java.rmi.ServerError;
import java.util.List;

@RestController
@RequestMapping()
public class CountryController {

    private final CountryService countryService;

    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @GetMapping("/{code}")
    public List<CountryDisplayDTO> cityByCode(@RequestParam(defaultValue = "${default.country.page.start}", name = "countryPage") Integer countryPage,
                                              @RequestParam(defaultValue = "${default.country.page.size}", name = "countriesAmount") Integer countriesAmount,
                                              @RequestParam(defaultValue = "${default.language.page.start}", name = "languagePage") Integer languagePage,
                                              @RequestParam(defaultValue = "${default.language.page.size}", name = "languagesAmount") Integer languagesAmount,
                                              @RequestParam(defaultValue = "${default.language.is.only.official}", name = "isOfficial") boolean onlyOfficial,
                                              @PathVariable String code) throws ServerError {
        return countryService.findByCountryCode(code, countryPage, countriesAmount, languagePage, languagesAmount, onlyOfficial);
    }
}
