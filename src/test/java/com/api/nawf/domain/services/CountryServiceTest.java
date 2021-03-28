package com.api.nawf.domain.services;

import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.api.nawf.domain.entities.CountryEntityMother;
import com.api.nawf.domain.entities.CurrencyEntity;
import com.api.nawf.domain.entities.CurrencyEntityMother;
import com.api.nawf.domain.models.CountryAPIModelMother;
import com.api.nawf.domain.models.CurrenciesAPIModelMother;
import com.api.nawf.domain.ports.ApiPort;
import com.api.nawf.domain.ports.CountryPort;
import com.api.nawf.infrastructure.exceptions.ApiException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
class CountryServiceTest {

    @Mock
    private CountryPort countryPort;

    @Mock
    private ApiPort apiPort;

    @Mock
    private CurrencyService currencyService;

    @InjectMocks
    private CountryService countryService = new CountryService();

    @BeforeEach
    public void antesDe() throws ApiException {
        MockitoAnnotations.openMocks(this);
        when(apiPort.findCountryByIp(anyString())).thenReturn(CountryAPIModelMother.getCountryApiOptional());
        when(apiPort.findCurrencyByIsoCode(anyString())).thenReturn(CurrenciesAPIModelMother.getCurrencyApiOptional());
        when(currencyService.findOrSave(any(CurrencyEntity.class))).thenReturn(CurrencyEntityMother.getCurrency());
    }

    @Test
    void debeLLamarAlMetodoFindOneByIsoCodeDeCountryPort() {
        String isoCode = "CO";
        countryService.findOne(isoCode);
        verify(countryPort, times(1)).findOneByIsoCode(isoCode);
    }

    @Test
    void debeBuscarYcrearLaInformacionDeUnPais() {
        String ip = "192.168.1.18";
        try {
            countryService.createByIp(ip);
            verify(apiPort, times(1)).findCountryByIp(ip);
            verify(apiPort, times(1)).findCurrencyByIsoCode("CO");
            verify(currencyService, times(1)).findOrSave(CurrencyEntityMother.getCurrency());
            verify(countryPort, times(1)).save(CountryEntityMother.getCountry());
        } catch (ApiException e) {
            fail("Debe crear el páis con su información correctamente.");
        }
    }
}
