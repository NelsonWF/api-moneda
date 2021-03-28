package com.api.nawf.infrastructure.controllers;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import com.api.nawf.application.usecases.BlackListCases;
import com.api.nawf.domain.entities.BlackListEntityMother;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Tag("api")
@RunWith(MockitoJUnitRunner.class)
class BlacklistControllerTest {
    private MockMvc mockMvc;

    @Mock
    private BlackListCases blackListCases;

    @InjectMocks
    private BlackListController blackListController;

    private final String ip = "192.168.1.33";
    private final String ipInvalida = "192.168.1.3320";
    private final String url = "/v1/blacklist/{ip}";

    @BeforeEach
    public void antesDe() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(blackListController).build();
    }

    @Test
    void debeBanearUnaIPDada() throws Exception {
        when(blackListCases.createBlackList(anyString())).thenReturn(BlackListEntityMother.getIpBanned());
        mockMvc.perform(post(url, ip).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        verify(blackListCases, times(1)).createBlackList(ip);
        verifyNoMoreInteractions(blackListCases);
    }

    @Test
    void debeLazarErrorCuandoSeIntenteBanearUnaIpIncorrecta() throws Exception {
        when(blackListCases.createBlackList(anyString())).thenReturn(BlackListEntityMother.getIpBanned());
        mockMvc.perform(
                post(url, ipInvalida).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
        verify(blackListCases, never()).createBlackList(ip);
        verifyNoMoreInteractions(blackListCases);
    }

    @Test
    void debeEliminarUnaIpDada() throws Exception {
        mockMvc.perform(delete(url, ip).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        verify(blackListCases, times(1)).deleteBlackList(ip);
        verifyNoMoreInteractions(blackListCases);
    }

    @Test
    void debeLazarErrorCuandoSeIntenteEliminarUnaIpIncorrecta() throws Exception {
        mockMvc.perform(
                delete(url, ipInvalida).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
        verify(blackListCases, never()).createBlackList(ip);
        verifyNoMoreInteractions(blackListCases);
    }
}
