package eu.mrndesign.matned.javaworkshop.service;

import eu.mrndesign.matned.javaworkshop.dto.CountryDisplayDTO;
import eu.mrndesign.matned.javaworkshop.model.Country;
import eu.mrndesign.matned.javaworkshop.model.CountryLanguage;
import eu.mrndesign.matned.javaworkshop.repository.CountryLanguageRepository;
import eu.mrndesign.matned.javaworkshop.repository.CountryRepository;
import org.springframework.stereotype.Service;

import java.rmi.ServerError;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CountryService extends BaseService {

    public static final String INVALID_COUNTRY_CODE = "INVALID_COUNTRY_CODE";
    public static final String INTERNAL_ERROR = "INTERNAL_ERROR";

    private final CountryRepository countryRepository;
    private final CountryLanguageRepository countryLanguageRepository;

    public CountryService(CountryRepository countryRepository,
                          CountryLanguageRepository countryLanguageRepository) {
        this.countryRepository = countryRepository;
        this.countryLanguageRepository = countryLanguageRepository;
    }

    public List<CountryDisplayDTO> findByCountryCode(String code,
                                                     Integer countryPage,
                                                     Integer countriesAmount,
                                                     Integer languagePage,
                                                     Integer languagesAmount,
                                                     boolean onlyOfficial) throws ServerError {
        if (countryRepository.checkConnection()) {
            List<Country> countries = countryRepository
                    .findByCountryCode(code, getPageable(countryPage, countriesAmount))
                    .getContent();
            if (countries.size() <= 0) throw new ServerError(INVALID_COUNTRY_CODE, new Error());
            List<CountryLanguage> languages;
            if (!onlyOfficial) {
                languages = countryLanguageRepository
                        .findByCountryCode(code, getPageable(languagePage, languagesAmount))
                        .getContent();
            } else {
                languages = countryLanguageRepository
                        .findByCountryCode(code, getPageable(0, 1000))
                        .getContent().stream()
                        .filter(CountryLanguage::isIs_official)
                        .collect(Collectors.toList());

            }
            return applyCountryDisplayDTOList(countries, languages);
        }
        throw new ServerError(INTERNAL_ERROR, new Error());
    }

    private List<CountryDisplayDTO> applyCountryDisplayDTOList(List<Country> countries, List<CountryLanguage> languages) {
        return countries.stream()
                .map(x -> CountryDisplayDTO.apply(x, languages))
                .collect(Collectors.toList());
    }
}
