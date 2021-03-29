package com.api.nawf.domain.services;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import com.api.nawf.domain.entities.BlackListEntityMother;
import com.api.nawf.domain.entities.BlacklistEntity;
import com.api.nawf.domain.ports.BlacklistPort;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
class BlackListServiceTest {
    @Mock
    private BlacklistPort blackListPort;

    @InjectMocks
    private BlackListService blackListService = new BlackListService();

    private String ip = "192.168.1.1";

    @BeforeEach
    public void antesDe() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void debeLLamarAlMetodoSaveDeBlackListPort() {
        BlacklistEntity blacklistEntity = BlackListEntityMother.getIpBanned();
        blackListService.save(blacklistEntity);
        verify(blackListPort, times(1)).save(blacklistEntity);
    }

    @Test
    void debeLLamarAlMetodoDeleteDeBlackListPort() {
        blackListService.delete(ip);
        verify(blackListPort, times(1)).delete(ip);
    }

    @Test
    void debeLLamarAlMetodoIsIpBannedDeBlackListPort() {
        blackListService.isIpBanned(ip);
        verify(blackListPort, times(1)).isIpBanned(ip);
    }

    @Test
    void debeLLamarAlMetodoFindOneDeBlackListPort() {
        blackListService.findOne(ip);
        verify(blackListPort, times(1)).findOne(ip);
    }
}
