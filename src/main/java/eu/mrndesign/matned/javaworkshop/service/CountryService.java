package eu.mrndesign.matned.javaworkshop.service;

import eu.mrndesign.matned.javaworkshop.dto.CountryDisplayDTO;
import eu.mrndesign.matned.javaworkshop.model.Country;
import eu.mrndesign.matned.javaworkshop.model.CountryLanguage;
import eu.mrndesign.matned.javaworkshop.repository.CountryLanguageRepository;
import eu.mrndesign.matned.javaworkshop.repository.CountryRepository;
import org.springframework.stereotype.Service;

import java.rmi.ServerError;
import java.util.List;

@Service
public class CountryService {

    public static final String INVALID_COUNTRY_CODE = "INVALID_COUNTRY_CODE";
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
            List<Country> countries = countryRepository.findByCountryCode(code);
            Country country = countries.size() > 0? countries.get(0) : null;
            if (country == null) throw new ServerError(INVALID_COUNTRY_CODE, new Error());
            List<CountryLanguage> languages = countryLanguageRepository
                    .findByCountryCode(code);
            CountryLanguage countryLanguage = languages.size()>0? languages.get(0) : null;
            return CountryDisplayDTO.apply(country, countryLanguage);
        }
        throw new ServerError(INTERNAL_ERROR, new Error());
    }
}
