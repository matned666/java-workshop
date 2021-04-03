package eu.mrndesign.matned.javaworkshop.service;

import eu.mrndesign.matned.javaworkshop.JavaWorkshopApplication;
import eu.mrndesign.matned.javaworkshop.config.TestsConfig;
import eu.mrndesign.matned.javaworkshop.dto.CountryDisplayDTO;
import eu.mrndesign.matned.javaworkshop.model.Country;
import eu.mrndesign.matned.javaworkshop.model.CountryLanguage;
import eu.mrndesign.matned.javaworkshop.repository.CountryLanguageRepository;
import eu.mrndesign.matned.javaworkshop.repository.CountryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.rmi.ServerError;
import java.util.Collections;

import static eu.mrndesign.matned.javaworkshop.service.CountryService.INTERNAL_ERROR;
import static eu.mrndesign.matned.javaworkshop.service.CountryService.INVALID_COUNTRY_CODE;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.doReturn;


@ExtendWith({SpringExtension.class})
@SpringBootTest(classes = {
        JavaWorkshopApplication.class,
        TestsConfig.class
})
@ActiveProfiles("Test")
class CountryServiceTest {

    @Autowired
    private CountryService countryService;

    @MockBean
    private CountryRepository countryRepository;
    @MockBean
    private CountryLanguageRepository countryLanguageRepository;

    private Country country;
    private CountryLanguage countryLanguage;
    private Pageable pageable;

    @BeforeEach
    void setUp() {
        country = new Country();
        countryLanguage = new CountryLanguage();
        pageable = countryService.getPageable(1, 1);
    }

    @Test
    void findByCountryCode() throws ServerError {
        doReturn(true).when(countryRepository).checkConnection();
        doReturn(new PageImpl<>(Collections.singletonList(country), pageable, 1)).when(countryRepository).findByCountryCode(any(), any(Pageable.class));
        doReturn(new PageImpl<>(Collections.singletonList(countryLanguage), pageable, 1)).when(countryLanguageRepository).findByCountryCode(any(), any(Pageable.class));

        assertEquals(Collections.singletonList(CountryDisplayDTO.apply(country, Collections.singletonList(countryLanguage))), countryService.findByCountryCode("asd", 1, 1, 1, 1, false));
    }

    @Test
    void ifNonExistentCodeIsCalledThenReturnErrorMessage() {
        doReturn(true).when(countryRepository).checkConnection();
        doReturn(new PageImpl<>(Collections.emptyList(), pageable, 0)).when(countryRepository).findByCountryCode(any(), any(Pageable.class));

        ServerError error = assertThrows(ServerError.class, ()-> countryService.findByCountryCode("eee", 1,1, 1, 1, false));

        assertTrue(error.getLocalizedMessage().contains(INVALID_COUNTRY_CODE));
    }

    @Test
    void ifNoConnectionWithDatabaseThenReturnErrorMessage()  {
        doReturn(false).when(countryRepository).checkConnection();

        ServerError error = assertThrows(ServerError.class, ()-> countryService.findByCountryCode("eee", 1,1, 1, 1, false));

        assertTrue(error.getLocalizedMessage().contains(INTERNAL_ERROR));
    }




}