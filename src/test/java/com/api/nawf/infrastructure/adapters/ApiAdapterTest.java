package com.api.nawf.infrastructure.adapters;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.HashMap;
import java.util.Optional;

import com.api.nawf.domain.models.CountryAPIModel;
import com.api.nawf.domain.models.CountryAPIModelMother;
import com.api.nawf.domain.models.CurrenciesAPIModel;
import com.api.nawf.domain.models.CurrenciesAPIModelMother;
import com.api.nawf.domain.models.RatesAPIModelMother;
import com.api.nawf.infrastructure.exceptions.ApiException;
import com.api.nawf.infrastructure.helpers.CalendarHelper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@RunWith(MockitoJUnitRunner.class)
class ApiAdapterTest {
    @Mock
    private RestTemplate restTemplate;

    @Mock
    private CalendarHelper calendarHelper;

    @InjectMocks
    private ApiAdapter apiAdapter = new ApiAdapter();

    @BeforeEach
    public void antesDe() {
        MockitoAnnotations.openMocks(this);
        when(calendarHelper.toDate(anyLong())).thenReturn(RatesAPIModelMother.now);
    }

    @Test
    void debeConsultarLaInformacionDeUnPaisPorLaIp() {
        try {
            CountryAPIModel country = CountryAPIModelMother.getCountryApi();
            String ip = "192.168.1.2";
            when(restTemplate.getForEntity(anyString(), any()))
                    .thenReturn(new ResponseEntity<>(country, HttpStatus.OK));
            assertEquals(CountryAPIModelMother.getCountryApiOptional(), apiAdapter.findCountryByIp(ip));
            verify(restTemplate, times(1)).getForEntity("https://api.ip2country.info/ip?" + ip, CountryAPIModel.class);
        } catch (RestClientException | ApiException e) {
            fail("Debe consultar la información del país por su ip");
        }
    }

    @Test
    void debeRetornarEmptyCuandoNoSeEncuentreElPaisPorLaIp() {
        try {
            String ip = "192.168.1.2";
            when(restTemplate.getForEntity(anyString(), any())).thenReturn(new ResponseEntity<>(HttpStatus.NO_CONTENT));
            assertEquals(Optional.empty(), apiAdapter.findCountryByIp(ip));
        } catch (RestClientException | ApiException e) {
            fail("Debe retornar vacio cuando la respuesta es 201.");
        }
    }

    @Test
    void debeLanzarErrorCuandoNoSePuedaConsultarElEndpointDeIp2country() {
        String ip = "192.168.1.2";
        when(restTemplate.getForEntity(anyString(), any())).thenThrow(new RestClientException("No se puede consultar"));
        assertThrows(ApiException.class, () -> {
            apiAdapter.findCountryByIp(ip);
        });
    }

    @Test
    void debeConsultarLaInformacionDeUnaMonedaPorElCodigoIsoDelPais() {
        try {
            CurrenciesAPIModel currencies = CurrenciesAPIModelMother.getCurrenciesApi();
            String isoCode = "CO";
            when(restTemplate.getForEntity(anyString(), any()))
                    .thenReturn(new ResponseEntity<>(currencies, HttpStatus.OK));
            assertEquals(CurrenciesAPIModelMother.getCurrencyApiOptional(), apiAdapter.findCurrencyByIsoCode(isoCode));
            verify(restTemplate, times(1)).getForEntity("https://restcountries.eu/rest/v2/alpha/CO?fields=currencies",
                    CurrenciesAPIModel.class);
        } catch (RestClientException | ApiException e) {
            fail("Debe consultar la información de la moneda correspondiente al país por su código iso");
        }
    }

    @Test
    void debeRetornarEmptyCuandoNoSeEncuentreInformacionDeMoneda() {
        try {
            String isoCode = "CO";
            when(restTemplate.getForEntity(anyString(), any())).thenReturn(new ResponseEntity<>(HttpStatus.NO_CONTENT));
            assertEquals(Optional.empty(), apiAdapter.findCurrencyByIsoCode(isoCode));
        } catch (RestClientException | ApiException e) {
            fail("Debe retornar vacio cuando la respuesta es 201.");
        }
    }

    @Test
    void debeLanzarErrorCuandoNoSePuedaConsultarElEndpointDeRestcountries() {
        String isoCode = "CO";
        when(restTemplate.getForEntity(anyString(), any())).thenThrow(new RestClientException("No se puede consultar"));
        assertThrows(ApiException.class, () -> {
            apiAdapter.findCurrencyByIsoCode(isoCode);
        });
    }

    @Test
    void debeConsultarLasTarifasActualesDeUnaMoneda() {
        try {
            String urlExpect = "http://data.fixer.io/api/latest?access_key=null&base=COP&symbols=EUR,USD";
            String currencyCode = "COP";
            when(restTemplate.getForObject(anyString(), any())).thenReturn(RatesAPIModelMother.getSuccessResponse());
            assertEquals(RatesAPIModelMother.getRates(), apiAdapter.findRatesByCurrencyCode(currencyCode));
            verify(restTemplate, times(1)).getForObject(urlExpect, HashMap.class);
        } catch (RestClientException | ApiException e) {
            fail("Debe consultar la cotización actual de la moneda");
        }
    }

    @Test
    void debeLanzarErrorCuandoElApiFixerRetorneError() {
        String currencyCode = "COP";
        when(restTemplate.getForObject(anyString(), any())).thenReturn(RatesAPIModelMother.getErrorResponse());
        assertThrows(ApiException.class, () -> {
            apiAdapter.findRatesByCurrencyCode(currencyCode);
        });
    }

    @Test
    void debeLanzarErrorCuandoNoSeObtengaBody() {
        String currencyCode = "COP";
        when(restTemplate.getForObject(anyString(), any())).thenReturn(null);
        assertThrows(ApiException.class, () -> {
            apiAdapter.findRatesByCurrencyCode(currencyCode);
        });
    }

    @Test
    void debeLanzarErrorCuandoNoSeObtengaRespuesta() {
        String currencyCode = "COP";
        when(restTemplate.getForObject(anyString(), any())).thenThrow(new RestClientException("No se puede consultar"));
        assertThrows(ApiException.class, () -> {
            apiAdapter.findRatesByCurrencyCode(currencyCode);
        });
    }
}
