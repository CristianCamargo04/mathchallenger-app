package com.ccamargo.mathchallenger.domain.service.impl;

import com.ccamargo.mathchallenger.domain.port.PercentageProvider;
import com.ccamargo.mathchallenger.domain.model.OperationMath;
import com.ccamargo.mathchallenger.domain.service.CalculateService;

public class CalculateServiceImpl implements CalculateService {

    private final PercentageProvider percentageProvider;

    public CalculateServiceImpl(PercentageProvider percentageProvider) {
        this.percentageProvider = percentageProvider;
    }

    @Override
    public float getOperation(OperationMath operationMath) {
        float sumResult = operationMath.add();
        float percentageResult = sumResult * percentageProvider.getValue();
        return sumResult + percentageResult;
    }
}
