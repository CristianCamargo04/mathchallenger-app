package com.ccamargo.mathchallenger.infrastructure.adapter;

import com.ccamargo.mathchallenger.domain.model.CallHistory;
import com.ccamargo.mathchallenger.domain.port.PercentageProvider;
import com.ccamargo.mathchallenger.domain.port.HttpClient;
import com.ccamargo.mathchallenger.domain.model.Percentage;
import com.ccamargo.mathchallenger.domain.service.CallHistoryService;
import com.ccamargo.mathchallenger.infrastructure.exception.ApiRetryException;
import com.ccamargo.mathchallenger.infrastructure.exception.ServiceUnavailableException;
import com.ccamargo.mathchallenger.infrastructure.utils.ApplicationProperties;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class PercentageProviderAdapter implements PercentageProvider {

    private final HttpClient httpClient;
    private final RetryTemplate retryTemplate;
    private final CallHistoryService callHistoryService;
    private final ApplicationProperties applicationProperties;
    private final static String MESSAGE_RETRY_EXCEPTION = "Ocurrió un error inesperado al recuperar el porcentaje";

    public PercentageProviderAdapter(HttpClient httpClient, RetryTemplate retryTemplate,
        CallHistoryService callHistoryService, ApplicationProperties applicationProperties) {
        this.httpClient = httpClient;
        this.retryTemplate = retryTemplate;
        this.callHistoryService = callHistoryService;
        this.applicationProperties = applicationProperties;
    }

    @Override
    @Cacheable(value = "percentageCache")
    public float getValue() {
        try {
            return retryTemplate.execute(context -> {
                try {
                    return httpClient.get(applicationProperties.getMockServerUrl(), Percentage.class).getValue();
                } catch (Exception ex) {
                    createCallHistoryObj(applicationProperties.getMockServerUrl());
                    throw new ApiRetryException(MESSAGE_RETRY_EXCEPTION, ex);
                }
            });
        } catch (ApiRetryException e) {
            throw new ServiceUnavailableException("No se pudo recuperar el porcentaje después de varios intentos", e);
        }
    }

    private void createCallHistoryObj(String endpoint) {
        String responseBody = String.format("{\"headers\":{},\"body\":\"%s\",\"statusCode\":\"%s\",\"statusCodeValue\":%d}",
                MESSAGE_RETRY_EXCEPTION, "SERVICE_UNAVAILABLE", HttpStatus.SERVICE_UNAVAILABLE.value());

        CallHistory callHistory = new CallHistory();
        callHistory.setTimestamp(LocalDateTime.now());
        callHistory.setEndpoint(endpoint);
        callHistory.setResponse(responseBody);
        callHistoryService.saveCallHistory(callHistory);
    }
}
