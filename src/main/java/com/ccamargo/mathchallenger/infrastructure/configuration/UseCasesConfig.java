package com.ccamargo.mathchallenger.infrastructure.configuration;

import com.ccamargo.mathchallenger.domain.port.CallHistoryRepository;
import com.ccamargo.mathchallenger.domain.port.PercentageProvider;
import com.ccamargo.mathchallenger.domain.service.CalculateService;
import com.ccamargo.mathchallenger.domain.service.CallHistoryService;
import com.ccamargo.mathchallenger.domain.service.impl.CallHistoryServiceImpl;
import org.springframework.context.annotation.Configuration;
import com.ccamargo.mathchallenger.domain.service.impl.CalculateServiceImpl;
import org.springframework.context.annotation.Bean;

@Configuration
public class UseCasesConfig {

    @Bean
    public CalculateService calculateBeanService(PercentageProvider percentageProvider) {
        return new CalculateServiceImpl(percentageProvider);
    }

    @Bean
    public CallHistoryService callHistoryBeanService(CallHistoryRepository callHistoryRepository) {
        return new CallHistoryServiceImpl(callHistoryRepository);
    }
}

