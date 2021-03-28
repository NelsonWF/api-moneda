package com.api.nawf.domain.services;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.only;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.List;

import com.api.nawf.domain.entities.CountryEntity;
import com.api.nawf.domain.entities.CountryEntityMother;
import com.api.nawf.domain.entities.RateEntity;
import com.api.nawf.domain.entities.RateEntityMother;
import com.api.nawf.domain.models.RateAPIModel;
import com.api.nawf.domain.models.RatesAPIModelMother;
import com.api.nawf.domain.ports.RatesPort;
import com.api.nawf.infrastructure.exceptions.ApiException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
class RatesServiceTest {

    @Mock
    private RatesPort ratesPort;

    @Mock
    private ApiService apiService;

    @Spy
    @InjectMocks
    private RatesService ratesService = new RatesService();

    private CountryEntity countryMock = CountryEntityMother.getCountry();

    private List<RateEntity> ratesMock = RateEntityMother.getRates();
    private List<RateAPIModel> ratesApiMock = RatesAPIModelMother.getRates();

    @BeforeEach
    public void antesDe() throws ApiException {
        MockitoAnnotations.openMocks(this);
        when(ratesPort.findCurrentRatesByCountry(any(CountryEntity.class))).thenReturn(ratesMock);
        when(apiService.findRatesByCurrencyCode(anyString())).thenReturn(ratesApiMock);
        when(ratesPort.saveAll(anyList())).thenReturn(ratesMock);
    }

    @Test
    void debeLlamarElMetodoSaveAllDeRatesPort() {
        ratesService.saveAll(ratesMock);
        verify(ratesPort, only()).saveAll(ratesMock);
    }

    @Test
    void debeRecuperarLasTarifasCotizadas() {
        try {
            assertEquals(ratesService.findCurrentRatesByCountry(countryMock), RateEntityMother.getRates());
            verify(ratesPort, only()).findCurrentRatesByCountry(countryMock);
        } catch (ApiException e) {
            fail("Debe recuperar las tarifas actuales.");
        }
    }

    @Test
    void debeBuscarYCrearTarifasCotizadas() {
        try {
            when(ratesPort.findCurrentRatesByCountry(any(CountryEntity.class))).thenReturn(Collections.emptyList());
            assertEquals(RateEntityMother.getRates(), ratesService.findCurrentRatesByCountry(countryMock));
            verify(ratesPort, times(1)).saveAll(RatesAPIModelMother.getRatesEntity(countryMock));
            verify(apiService, times(1)).findRatesByCurrencyCode(countryMock.getCurrency().getCode());
        } catch (ApiException e) {
            fail("Debe crear y recuperar las tarifas actuales.");
        }
    }
}
