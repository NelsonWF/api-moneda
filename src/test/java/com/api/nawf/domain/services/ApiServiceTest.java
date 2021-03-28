package com.api.nawf.domain.services;

import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import com.api.nawf.domain.ports.ApiPort;
import com.api.nawf.infrastructure.exceptions.ApiException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
class ApiServiceTest {
    @Mock
    private ApiPort apiPort;

    @InjectMocks
    private ApiService apiService = new ApiService();

    @BeforeEach
    public void antesDe() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void debeLLamarAlMetodoFinCountryByIpDelPort() {
        String ip = "192.168.1.2";
        try {
            apiService.findCountryByIp(ip);
            verify(apiPort, times(1)).findCountryByIp(ip);
        } catch (ApiException e) {
            fail("Debe llamar al método findCountryByIp");
        }
    }

    @Test
    void debeLLamarAlMetodoFindCurrencyByIsoCodeDeApiPort() {
        String code = "CO";
        try {
            apiService.findCurrencyByIsoCode(code);
            verify(apiPort, times(1)).findCurrencyByIsoCode(code);
        } catch (ApiException e) {
            fail("Debe llamar al método findCurrencyByIsoCode");
        }
    }

    @Test
    void debeLLamarAlMetodofindRatesByCurrencyCodeDeApiPort() {
        String currency = "COP";
        try {
            apiService.findRatesByCurrencyCode(currency);
            verify(apiPort, times(1)).findRatesByCurrencyCode(currency);
        } catch (ApiException e) {
            fail("Debe llamar al método findRatesByCurrencyCode");
        }
    }

}
