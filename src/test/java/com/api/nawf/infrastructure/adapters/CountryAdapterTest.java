package com.api.nawf.infrastructure.adapters;

import static org.mockito.Mockito.only;
import static org.mockito.Mockito.verify;

import com.api.nawf.domain.entities.CountryEntity;
import com.api.nawf.domain.entities.CountryEntityMother;
import com.api.nawf.infrastructure.adapters.repositories.CountryJpaRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
class CountryAdapterTest {
    @Mock
    private CountryJpaRepository repository;

    @InjectMocks
    private CountryAdapter countryAdapter;

    @BeforeEach
    public void antesDe() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void debeLlamarAlMetodoSaveDelJpaRepositorio() {
        CountryEntity country = CountryEntityMother.getCountry();
        countryAdapter.save(country);
        verify(repository, only()).save(country);
    }

    @Test
    void debeLlamarAlMetodoFindByIdDelJpaRepositorio() {
        String isoCode = "CO";
        countryAdapter.findOneByIsoCode(isoCode);
        verify(repository, only()).findById(isoCode);
    }
}
