package com.ccamargo.mathchallenger.infrastructure.adapter;

import com.ccamargo.mathchallenger.domain.model.CallHistory;
import com.ccamargo.mathchallenger.domain.model.Paginator;
import com.ccamargo.mathchallenger.infrastructure.persistence.entity.CallHistoryEntity;
import com.ccamargo.mathchallenger.infrastructure.persistence.mapper.CallHistoryMapper;
import com.ccamargo.mathchallenger.infrastructure.persistence.repository.CallHistoryJpaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CallHistoryRepositoryAdapterTest {

    @Mock
    private CallHistoryJpaRepository callHistoryJpaRepository;

    @Mock
    private CallHistoryMapper callHistoryMapper;

    @InjectMocks
    private CallHistoryRepositoryAdapter callHistoryRepositoryAdapter;

    private CallHistory callHistory;
    private CallHistoryEntity callHistoryEntity;

    @BeforeEach
    public void setUp() {
        callHistory = new CallHistory(1L, LocalDateTime.now(), "/url", "","" );
        callHistoryEntity = new CallHistoryEntity(1L, LocalDateTime.now(), "/url", "","");
    }

    @Test
    public void testSaveCallHistory() {
        when(callHistoryMapper.toCallHistoryEntity(callHistory)).thenReturn(callHistoryEntity);
        callHistoryRepositoryAdapter.saveCallHistory(callHistory);
        verify(callHistoryJpaRepository, times(1)).save(callHistoryEntity);
    }

    @Test
    public void testGetPagedCallHistory() {
        List<CallHistoryEntity> entities = List.of(callHistoryEntity);
        Page<CallHistoryEntity> entityPage = new PageImpl<>(entities, PageRequest.of(0, 10), 1);

        when(callHistoryJpaRepository.findAll(PageRequest.of(0, 10))).thenReturn(entityPage);
        when(callHistoryMapper.toCallHistory(callHistoryEntity)).thenReturn(callHistory);

        Paginator<CallHistory> result = callHistoryRepositoryAdapter.getPagedCallHistory(0, 10);

        assertNotNull(result);
        assertEquals(1, result.getTotalElements());
        assertEquals(1, result.getTotalPages());
        assertEquals(0, result.getCurrentPage());
        assertEquals(10, result.getPageSize());
        assertEquals(1, result.getContent().size());
    }
}