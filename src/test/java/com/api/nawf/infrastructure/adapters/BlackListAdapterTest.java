package com.api.nawf.infrastructure.adapters;

import static org.mockito.Mockito.only;
import static org.mockito.Mockito.verify;

import com.api.nawf.domain.entities.BlackListEntityMother;
import com.api.nawf.domain.entities.BlacklistEntity;
import com.api.nawf.infrastructure.adapters.repositories.BlackListJpaRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
class BlackListAdapterTest {
    @Mock
    private BlackListJpaRepository repository;

    @InjectMocks
    private BlackListAdapter blackListAdapter = new BlackListAdapter();

    private String ip = "192.168.1.1";

    @BeforeEach
    public void antesDe() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void debeLlamarAlMetodoSaveDelJpaRepositorio() {
        BlacklistEntity blackList = BlackListEntityMother.getIpBanned();
        blackListAdapter.save(blackList);
        verify(repository, only()).save(blackList);
    }

    @Test
    void debeLlamarAlMetododeleteByIdDelJpaRepositorio() {
        blackListAdapter.delete(ip);
        verify(repository, only()).deleteById(ip);
    }

    @Test
    void debeLlamarAlMetodoExistsByIdDelJpaRepositorio() {
        blackListAdapter.isIpBanned(ip);
        verify(repository, only()).existsById(ip);
    }

    @Test
    void debeLlamarAlMetodoFindByIdDelJpaRepositorio() {
        blackListAdapter.findOne(ip);
        verify(repository, only()).findById(ip);
    }
}
