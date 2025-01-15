package com.ccamargo.mathchallenger.infrastructure.controller;

import com.ccamargo.mathchallenger.domain.model.CallHistory;
import com.ccamargo.mathchallenger.domain.model.Paginator;
import com.ccamargo.mathchallenger.domain.service.CallHistoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CallHistoryControllerTest {

    @Mock
    private CallHistoryService callHistoryService;

    @InjectMocks
    private CallHistoryController callHistoryController;

    private CallHistory callHistory1;
    private CallHistory callHistory2;
    private List<CallHistory> content;
    private Paginator<CallHistory> paginator;
    private int page = 0;
    private int size = 10;

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
        paginator = new Paginator<>(content, content.size(), 1, page, size);
    }

    @Test
    public void testGetPagedCallHistory() {
        when(callHistoryService.getPagedCallHistory(page, size)).thenReturn(paginator);

        ResponseEntity<Paginator<CallHistory>> response = callHistoryController.getPagedCallHistory(page, size);

        assertEquals(paginator, response.getBody());
        assertEquals(200, response.getStatusCodeValue());
    }
}
