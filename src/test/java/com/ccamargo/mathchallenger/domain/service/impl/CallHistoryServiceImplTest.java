package com.ccamargo.mathchallenger.domain.service.impl;

import com.ccamargo.mathchallenger.domain.model.CallHistory;
import com.ccamargo.mathchallenger.domain.model.Paginator;
import com.ccamargo.mathchallenger.domain.port.CallHistoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CallHistoryServiceImplTest {
    @Mock
    private CallHistoryRepository callHistoryRepository;

    @InjectMocks
    CallHistoryServiceImpl callHistoryService;

    private CallHistory callHistory1;
    private CallHistory callHistory2;
    private List<CallHistory> content;
    private Paginator<CallHistory> paginator;

    @BeforeEach
    public void setUp() {
        callHistory1 = new CallHistory(1L, LocalDateTime.now(), "/url", "","");
        callHistory2 = new CallHistory();
        callHistory2.setId(2L);
        callHistory2.setTimestamp(LocalDateTime.now());
        callHistory2.setEndpoint("/url");
        callHistory2.setParameters("");
        callHistory2.setResponse("");
        content = List.of(callHistory1, callHistory2);
        paginator = new Paginator<>(content, content.size(), 1, 0, 10);
    }

    @Test
    void saveCallHistory() {
        callHistoryService.saveCallHistory(callHistory1);
        verify(callHistoryRepository, times(1)).saveCallHistory(callHistory1);
    }

    @Test
    void getPagedCallHistory() {
        when(callHistoryRepository.getPagedCallHistory(anyInt(), anyInt())).thenReturn(paginator);
        Paginator<CallHistory> response = callHistoryService.getPagedCallHistory(anyInt(), anyInt());
        assertEquals(response.getContent().getFirst().getId(), paginator.getContent().getFirst().getId());
        assertEquals(response.getContent().getFirst().getResponse(), paginator.getContent().getFirst().getResponse());
        assertEquals(response.getContent().getFirst().getEndpoint(), paginator.getContent().getFirst().getEndpoint());
        assertEquals(response.getContent().getFirst().getTimestamp(), paginator.getContent().getFirst().getTimestamp());
        assertEquals(response.getContent().getFirst().getParameters(), paginator.getContent().getFirst().getParameters());
        assertEquals(response.getTotalElements(), paginator.getTotalElements());
        assertEquals(response.getTotalPages(), paginator.getTotalPages());
        assertEquals(response.getCurrentPage(), paginator.getCurrentPage());
        assertEquals(response.getPageSize(), paginator.getPageSize());
    }
}