package com.api.nawf.application.usecases;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import com.api.nawf.domain.entities.CountryEntity;
import com.api.nawf.domain.entities.CountryEntityMother;
import com.api.nawf.domain.entities.IpCountryEntityMother;
import com.api.nawf.domain.entities.RateEntityMother;
import com.api.nawf.domain.services.BlackListService;
import com.api.nawf.domain.services.CountryService;
import com.api.nawf.domain.services.IpCountryService;
import com.api.nawf.domain.services.RatesService;
import com.api.nawf.infrastructure.exceptions.ApiException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
class SearchCountryTest {
    @Mock
    private IpCountryService ipCountryService;

    @Mock
    private CountryService countryService;

    @Mock
    private RatesService ratesService;

    @Mock
    private BlackListService blackListService;

    @InjectMocks
    private SearchCountry searchCountry = new SearchCountry();

    private String ip = "192.168.2.1";

    private CountryEntity country = CountryEntityMother.getCountry();

    @BeforeEach
    public void antesDe() throws ApiException {
        MockitoAnnotations.openMocks(this);
        country.setRates(RateEntityMother.getRates());
        when(blackListService.isIpBanned(anyString())).thenReturn(false);
        when(ratesService.findCurrentRatesByCountry(any(CountryEntity.class))).thenReturn(RateEntityMother.getRates());
    }

    @Test
    void debeLanzarErrorCuandoLaIpEstaBaneada() {
        when(blackListService.isIpBanned(anyString())).thenReturn(true);
        assertThrows(ApiException.class, () -> {
            searchCountry.searchByIp(ip);
        });
    }

    @Test
    void debeRecuperarLaInformacionDelPaisDesdeBaseDeDatos() {
        when(ipCountryService.findOneByIp(anyString())).thenReturn(IpCountryEntityMother.getIpCountryOptional());
        try {
            searchCountry.searchByIp(ip);
            verify(ipCountryService, times(1)).findOneByIp(ip);
            verify(ratesService, times(1)).findCurrentRatesByCountry(country);
            verify(ipCountryService, never()).save(IpCountryEntityMother.getIpCountry());
            verify(countryService, never()).createByIp(ip);
        } catch (ApiException e) {
            fail("Debe recuperar la información persistida");
        }
    }

    @Test
    void debeConsultarAlasAPILaInformacionDelPais() {
        try {
            when(ipCountryService.findOneByIp(anyString())).thenReturn(Optional.empty());
            when(countryService.createByIp(anyString())).thenReturn(CountryEntityMother.getCountry());
            searchCountry.searchByIp(ip);
            verify(ipCountryService, times(1)).findOneByIp(ip);
            verify(ratesService, times(1)).findCurrentRatesByCountry(country);
            verify(countryService, times(1)).createByIp(ip);
        } catch (ApiException e) {
            fail("Debe recuperar la información persistida");
        }
    }
}
