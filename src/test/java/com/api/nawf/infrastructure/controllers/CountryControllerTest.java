package com.api.nawf.infrastructure.controllers;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.api.nawf.application.usecases.SearchCountry;
import com.api.nawf.domain.entities.CountryEntityMother;
import com.api.nawf.infrastructure.exceptions.ApiException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@Tag("api")
@RunWith(MockitoJUnitRunner.class)
class CountryControllerTest {
    private MockMvc mockMvc;

    @Mock
    private SearchCountry searchCountry;

    @InjectMocks
    private CountryController countryController;

    private final String ip = "192.168.1.33";
    private final String ipInvalida = "192.168.1.3320";
    private final String url = "/v1/country/{ip}";

    @BeforeEach
    public void antesDe() throws ApiException {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(countryController).build();
    }

    @Test
    void consultarInformacionDelPais() throws Exception {
        when(searchCountry.searchByIp(anyString())).thenReturn(CountryEntityMother.getCountry());
        mockMvc.perform(get(url, ip).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        verify(searchCountry, times(1)).searchByIp(ip);
        verifyNoMoreInteractions(searchCountry);
    }

    @Test
    void debeLazarErrorCuandoSeIgreseUnaIpIncorrecta() throws Exception {
        when(searchCountry.searchByIp(anyString())).thenReturn(CountryEntityMother.getCountry());
        mockMvc.perform(get(url, ipInvalida).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
        verify(searchCountry, never()).searchByIp(ip);
    }
}
