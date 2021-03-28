package com.api.nawf.infrastructure.adapters;

import static org.mockito.Mockito.only;
import static org.mockito.Mockito.verify;

import com.api.nawf.domain.entities.IpCountryEntity;
import com.api.nawf.domain.entities.IpCountryEntityMother;
import com.api.nawf.infrastructure.adapters.repositories.IpCountryJpaRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
class IpCountryAdapterTest {
    @Mock
    private IpCountryJpaRepository repository;

    @InjectMocks
    private IpCountryAdapter ipCountryAdapter = new IpCountryAdapter();

    @BeforeEach
    public void antesDe() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void debeLlamarAlMetodoSaveDelJpaRepositorio() {
        IpCountryEntity ipCountry = IpCountryEntityMother.getIpCountry();
        ipCountryAdapter.save(ipCountry);
        verify(repository, only()).save(ipCountry);
    }

    @Test
    void debeLlamarAlMetodoFindByIdDelJpaRepositorio() {
        String ip = "192.168.1.1";
        ipCountryAdapter.findOneByIp(ip);
        verify(repository, only()).findById(ip);
    }
}
