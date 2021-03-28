package com.api.nawf.domain.services;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import com.api.nawf.domain.entities.CurrencyEntity;
import com.api.nawf.domain.entities.CurrencyEntityMother;
import com.api.nawf.domain.ports.CurrencyPort;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
class CurrencyServiceTest {
    @Mock
    private CurrencyPort currencyPort;

    @InjectMocks
    private CurrencyService currencyService = new CurrencyService();

    private CurrencyEntity currency = CurrencyEntityMother.getCurrency();

    @BeforeEach
    public void antesDe() {
        MockitoAnnotations.openMocks(this);
        when(currencyPort.save(any(CurrencyEntity.class))).thenReturn(currency);
    }

    @Test
    void debeLLamarAlMetodoSaveDeCurrencyPort() {
        currencyService.save(currency);
        verify(currencyPort, times(1)).save(currency);
    }

    @Test
    void debeLLamarAlMetodofindOneByCodeDeCurrencyPort() {
        String code = "COP";
        currencyService.findOneByCode(code);
        verify(currencyPort, times(1)).findOneByCode(code);
    }

    @Test
    void debeRetornarLaMonedaEncontradaEnBD() {
        when(currencyPort.findOneByCode(anyString())).thenReturn(CurrencyEntityMother.getCurrencyOptional());
        assertEquals(currency, currencyService.findOrSave(currency));
        verify(currencyPort, times(1)).findOneByCode(currency.getCode());
    }

    @Test
    void debeGuardarLaMonedaYretornarla() {
        when(currencyPort.findOneByCode(anyString())).thenReturn(Optional.empty());
        assertEquals(currency, currencyService.findOrSave(currency));
        verify(currencyPort, times(1)).findOneByCode(currency.getCode());
        verify(currencyPort, times(1)).save(currency);
    }
}
