package eu.mrndesign.matned.javaworkshop.controller;

import eu.mrndesign.matned.javaworkshop.JavaWorkshopApplication;
import eu.mrndesign.matned.javaworkshop.config.TestsConfig;
import eu.mrndesign.matned.javaworkshop.service.CountryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith({SpringExtension.class})
@SpringBootTest(classes = {
        JavaWorkshopApplication.class,
        TestsConfig.class
})
@ActiveProfiles("Test")
@AutoConfigureMockMvc
class CountryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CountryService countryService;

    @BeforeEach
    void setUp() {
    }

    @Test
    void cityByCode() throws Exception {
        Mockito.doReturn(null).when(countryService).findByCountryCode(any());
        mockMvc.perform(
                MockMvcRequestBuilders.get("/1")
                        .accept("application/json"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andReturn();
    }
}