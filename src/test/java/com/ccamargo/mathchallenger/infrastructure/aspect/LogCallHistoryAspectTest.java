package com.ccamargo.mathchallenger.infrastructure.aspect;

import com.ccamargo.mathchallenger.domain.model.CallHistory;
import com.ccamargo.mathchallenger.domain.service.CallHistoryService;
import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LogCallHistoryAspectTest {

    @Mock
    private CallHistoryService callHistoryService;

    @Mock
    private HttpServletRequest request;

    @Mock
    private ServletRequestAttributes attributes;

    @InjectMocks
    private LogCallHistoryAspect logCallHistoryAspect;

    @BeforeEach
    void setUp() {
        RequestContextHolder.setRequestAttributes(attributes);
    }

    @Test
    void logFromController() throws Exception {
        String endpoint = "/testEndpoint";
        String responseBody = "{\"key\":\"value\"}";
        when(attributes.getRequest()).thenReturn(request);
        when(request.getRequestURI()).thenReturn(endpoint);
        when(request.getParameterMap()).thenReturn(Collections.singletonMap("param", new String[]{"value"}));

        assertDoesNotThrow(() -> logCallHistoryAspect.logFromController(new Object()));

        verify(callHistoryService, times(1)).saveCallHistory(any(CallHistory.class));
    }
}
