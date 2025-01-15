package com.ccamargo.mathchallenger.infrastructure.configuration;

import com.ccamargo.mathchallenger.infrastructure.exception.ApiRetryException;
import com.ccamargo.mathchallenger.infrastructure.listener.ApiRetryListener;
import com.ccamargo.mathchallenger.infrastructure.utils.ApplicationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.retry.support.RetryTemplate;

@Configuration
public class RetryConfig {

    private final ApplicationProperties applicationProperties;

    public RetryConfig(ApplicationProperties applicationProperties) {
        this.applicationProperties = applicationProperties;
    }

    @Bean
    public RetryTemplate retryTemplate() {

        return RetryTemplate.builder()
                .maxAttempts(applicationProperties.getSpringRetryMaxAttempts())
                .fixedBackoff(applicationProperties.getSpringRetryTime())
                .retryOn(ApiRetryException.class)
                .withListener(new ApiRetryListener())
                .build();
    }
}
