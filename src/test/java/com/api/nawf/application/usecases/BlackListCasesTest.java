package com.api.nawf.application.usecases;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.only;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Date;
import java.util.Optional;

import com.api.nawf.domain.entities.BlackListEntityMother;
import com.api.nawf.domain.entities.BlacklistEntity;
import com.api.nawf.domain.services.BlackListService;
import com.api.nawf.infrastructure.helpers.CalendarHelper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
class BlackListCasesTest {
    @Mock
    private BlackListService blackListService;

    @Mock
    private CalendarHelper calendarHelper;

    @InjectMocks
    private BlackListCases blackListCases = new BlackListCases();

    private String ip = "192.168.2.1";

    private Date now = new Date();

    @BeforeEach
    public void antesDe() {
        MockitoAnnotations.openMocks(this);
        when(calendarHelper.now()).thenReturn(now);
    }

    @Test
    void debeLLamarAlMetodoDeleteDelservicio() {
        blackListCases.deleteBlackList(ip);
        verify(blackListService, times(1)).delete(ip);
    }

    @Test
    void debeGuardarLaIpEnLaListaNegraCuandoEstaNoExistaEnLaTabla() {
        BlacklistEntity blacklistEntity = BlacklistEntity.builder().ip(ip).date(now).build();
        when(blackListService.findOne(anyString())).thenReturn(Optional.empty());
        blackListCases.createBlackList(ip);
        verify(blackListService, times(1)).findOne(ip);
        verify(blackListService, times(1)).save(blacklistEntity);
    }

    @Test
    void debeRetornarLaIpDeLaListaNegraCuandoEstaExistaEnLaTabla() {
        when(blackListService.findOne(anyString())).thenReturn(BlackListEntityMother.getIpBannedOptional());
        BlacklistEntity blacklistEntity = BlackListEntityMother.getIpBanned();
        assertEquals(blacklistEntity, blackListCases.createBlackList(ip));
        verify(blackListService, only()).findOne(ip);
    }
}
