package eu.mrndesign.matned.javaworkshop.service;

import eu.mrndesign.matned.javaworkshop.dto.CountryDisplayDTO;
import eu.mrndesign.matned.javaworkshop.model.Country;
import eu.mrndesign.matned.javaworkshop.model.CountryLanguage;
import eu.mrndesign.matned.javaworkshop.repository.CountryLanguageRepository;
import eu.mrndesign.matned.javaworkshop.repository.CountryRepository;
import org.springframework.stereotype.Service;

import java.rmi.ServerError;

@Service
public class CountryService {

    public static final String INVALID_COUNTRY_CODE = "INVALID_COUNTRY_CODE";
    public static final String COUNTRY_LANGUAGE_NOT_FOUND = "COUNTRY_LANGUAGE_NOT_FOUND";
    public static final String INTERNAL_ERROR = "INTERNAL_ERROR";

    private final CountryRepository countryRepository;
    private final CountryLanguageRepository countryLanguageRepository;

    public CountryService(CountryRepository countryRepository,
                          CountryLanguageRepository countryLanguageRepository) {
        this.countryRepository = countryRepository;
        this.countryLanguageRepository = countryLanguageRepository;
    }

    public CountryDisplayDTO findByCountryCode(String code) throws ServerError {

        if (countryRepository.checkConnection()) {
            Country country = countryRepository.findByCountryCode(code).orElseThrow(() -> new ServerError(INVALID_COUNTRY_CODE, new Error()));
            if (country != null) {
                CountryLanguage countryLanguage = countryLanguageRepository
                        .findByCountryCode(code)
                        .orElseThrow(() -> new ServerError(COUNTRY_LANGUAGE_NOT_FOUND, new Error()));
                return CountryDisplayDTO.apply(country, countryLanguage);
            }
            throw new ServerError(INVALID_COUNTRY_CODE, new Error());
        }
        throw new ServerError(INTERNAL_ERROR, new Error());
    }
}
