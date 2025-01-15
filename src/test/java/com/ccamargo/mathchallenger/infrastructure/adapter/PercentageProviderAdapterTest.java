package com.ccamargo.mathchallenger.infrastructure.adapter;

import com.ccamargo.mathchallenger.domain.model.CallHistory;
import com.ccamargo.mathchallenger.domain.model.Percentage;
import com.ccamargo.mathchallenger.domain.port.HttpClient;
import com.ccamargo.mathchallenger.domain.service.CallHistoryService;
import com.ccamargo.mathchallenger.infrastructure.exception.ApiRetryException;
import com.ccamargo.mathchallenger.infrastructure.exception.ServiceUnavailableException;
import com.ccamargo.mathchallenger.infrastructure.utils.ApplicationProperties;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.retry.RetryCallback;
import org.springframework.retry.support.RetryTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PercentageProviderAdapterTest {
    @Mock
    private HttpClient httpClient;

    @Mock
    private RetryTemplate retryTemplate;

    @Mock
    private CallHistoryService callHistoryService;

    @Mock
    private ApplicationProperties applicationProperties;

    @InjectMocks
    private PercentageProviderAdapter percentageProviderAdapter;

    private Percentage percentage;

    private String mockServerUrl = "http://mockserver.com";

    private String MESSAGE_RETRY_EXCEPTION = "Ocurrió un error inesperado al recuperar el porcentaje";

    @BeforeEach
    public void setUp() {
        percentage = new Percentage(0.1f);
    }

    @Test
    void testGetValueSuccess() {
        when(applicationProperties.getMockServerUrl()).thenReturn(mockServerUrl);
        when(retryTemplate.execute(any(RetryCallback.class)))
                .thenAnswer(invocation -> { RetryCallback<?, ?> callback = invocation.getArgument(0);
                    return callback.doWithRetry(null); });
        when(httpClient.get(mockServerUrl, Percentage.class)).thenReturn(percentage);
        float value = percentageProviderAdapter.getValue();
        assertEquals(percentage.getValue(), value, 0.0f);
    }

    @Test
    public void testGetValueWithRetryException() {
        when(retryTemplate.execute(Mockito.any(RetryCallback.class)))
                .thenThrow(new ApiRetryException(MESSAGE_RETRY_EXCEPTION, any(Throwable.class)));
        ServiceUnavailableException exception = assertThrows(ServiceUnavailableException.class,
                () -> percentageProviderAdapter.getValue());
        assertEquals("No se pudo recuperar el porcentaje después de varios intentos", exception.getMessage());
    }

    @Test
    void testGetValueWithHttpClientException() {
        when(applicationProperties.getMockServerUrl()).thenReturn(mockServerUrl);
        when(retryTemplate.execute(any(RetryCallback.class)))
                .thenAnswer(invocation -> {
                    RetryCallback<?, ?> callback = invocation.getArgument(0);
                    return callback.doWithRetry(null);
                });

        when(httpClient.get(mockServerUrl, Percentage.class))
                .thenThrow(new RuntimeException("Error en el cliente HTTP"));

        Mockito.doNothing().when(callHistoryService).saveCallHistory(any(CallHistory.class));

        ServiceUnavailableException exception = assertThrows(ServiceUnavailableException.class,
                () -> percentageProviderAdapter.getValue());

        assertEquals("No se pudo recuperar el porcentaje después de varios intentos", exception.getMessage());

        Mockito.verify(callHistoryService, Mockito.times(1)).saveCallHistory(any(CallHistory.class));
    }

}