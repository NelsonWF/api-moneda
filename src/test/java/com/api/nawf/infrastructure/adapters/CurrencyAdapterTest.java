package com.api.nawf.infrastructure.adapters;

import static org.mockito.Mockito.only;
import static org.mockito.Mockito.verify;

import com.api.nawf.domain.entities.CurrencyEntity;
import com.api.nawf.domain.entities.CurrencyEntityMother;
import com.api.nawf.infrastructure.adapters.repositories.CurrencyJpaRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
class CurrencyAdapterTest {
    @Mock
    private CurrencyJpaRepository repository;

    @InjectMocks
    private CurrencyAdapter currencyAdapter = new CurrencyAdapter();

    @BeforeEach
    public void antesDe() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void debeLlamarAlMetodoSaveDelJpaRepositorio() {
        CurrencyEntity currency = CurrencyEntityMother.getCurrency();
        currencyAdapter.save(currency);
        verify(repository, only()).save(currency);
    }

    @Test
    void debeLlamarAlMetodoFindByIdDelJpaRepositorio() {
        String code = "COP";
        currencyAdapter.findOneByCode(code);
        verify(repository, only()).findById(code);
    }
}
