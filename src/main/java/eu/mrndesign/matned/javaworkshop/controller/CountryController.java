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
    public List<CountryDisplayDTO> cityByCode(@RequestParam(defaultValue = "${default.sort.by}", name = "sort") String[] sort,
                                              @RequestParam(defaultValue = "${default.page.start}", name = "page") Integer page,
                                              @RequestParam(defaultValue = "${default.page.size}", name = "amount") Integer amount,
                                              @PathVariable String code)
            throws ServerError {
        return countryService.findByCountryCode(code, page, amount, sort);
    }
}
