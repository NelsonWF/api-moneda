package com.api.nawf.infrastructure.adapters;

import static org.mockito.Mockito.only;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Date;
import java.util.List;

import com.api.nawf.domain.entities.CountryEntity;
import com.api.nawf.domain.entities.CountryEntityMother;
import com.api.nawf.domain.entities.RateEntity;
import com.api.nawf.domain.entities.RateEntityMother;
import com.api.nawf.infrastructure.adapters.repositories.RateJpaRepository;
import com.api.nawf.infrastructure.helpers.CalendarHelper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
class RatesAdapterTest {
    @Mock
    private RateJpaRepository repository;

    @Mock
    private CalendarHelper calendarHelper;

    @InjectMocks
    private RatesAdapter ratesAdapter = new RatesAdapter();

    private final Date now = new Date();

    @BeforeEach
    public void antesDe() {
        MockitoAnnotations.openMocks(this);
        when(calendarHelper.now()).thenReturn(now);
    }

    @Test
    void debeLlamarAlMetodoSaveAllDelJpaRepositorio() {
        List<RateEntity> rates = RateEntityMother.getRates();
        ratesAdapter.saveAll(rates);
        verify(repository, only()).saveAll(rates);
    }

    @Test
    void debeLlamarAlMetodoFindCurrentRatesByCountryDelJpaRepositorio() {
        CountryEntity country = CountryEntityMother.getCountry();
        ratesAdapter.findCurrentRatesByCountry(country);
        verify(repository, times(1)).findCurrentRatesByCountry(country, now);
    }
}
