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
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.rmi.ServerError;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Optional;

import static eu.mrndesign.matned.javaworkshop.service.CountryService.INTERNAL_ERROR;
import static eu.mrndesign.matned.javaworkshop.service.CountryService.INVALID_COUNTRY_CODE;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
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


    @BeforeEach
    void setUp() {
        country = new Country();
        countryLanguage = new CountryLanguage();
    }

    @Test
    void findByCountryCode() throws ServerError {
        doReturn(true).when(countryRepository).checkConnection();
        doReturn(Collections.singletonList(country)).when(countryRepository).findByCountryCode(anyString());
        doReturn(Collections.singletonList(countryLanguage)).when(countryLanguageRepository).findByCountryCode(anyString());

        assertEquals(CountryDisplayDTO.apply(country, countryLanguage), countryService.findByCountryCode("asd"));
    }

    @Test
    void ifNonExistentCodeIsCalledThenReturnErrorMessage() throws ServerError {
        doReturn(true).when(countryRepository).checkConnection();
        doReturn(new LinkedList<>()).when(countryRepository).findByCountryCode(anyString());

        ServerError error = assertThrows(ServerError.class, ()-> countryService.findByCountryCode("eee"));

        assertTrue(error.getLocalizedMessage().contains(INVALID_COUNTRY_CODE));
    }

    @Test
    void ifNoConnectionWithDatabaseThenReturnErrorMessage() throws ServerError {
        doReturn(false).when(countryRepository).checkConnection();

        ServerError error = assertThrows(ServerError.class, ()-> countryService.findByCountryCode("eee"));

        assertTrue(error.getLocalizedMessage().contains(INTERNAL_ERROR));
    }




}