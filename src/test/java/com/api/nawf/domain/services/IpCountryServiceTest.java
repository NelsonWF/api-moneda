package com.api.nawf.domain.services;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import com.api.nawf.domain.entities.IpCountryEntity;
import com.api.nawf.domain.entities.IpCountryEntityMother;
import com.api.nawf.domain.ports.IpCountryPort;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
class IpCountryServiceTest {
    @Mock
    private IpCountryPort ipCountryPort;

    @InjectMocks
    private IpCountryService ipCountryService = new IpCountryService();

    @BeforeEach
    public void antesDe() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void debeLLamarAlMetodoSaveDeIpCountryPort() {
        IpCountryEntity ipCountry = IpCountryEntityMother.getIpCountry();
        ipCountryService.save(ipCountry);
        verify(ipCountryPort, times(1)).save(ipCountry);
    }

    @Test
    void debeLLamarAlMetodofindOneByIpDeIpCountryPort() {
        String ip = "192.168.1.2";
        ipCountryService.findOneByIp(ip);
        verify(ipCountryPort, times(1)).findOneByIp(ip);
    }
}
