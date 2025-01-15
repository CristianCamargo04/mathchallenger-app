package com.ccamargo.mathchallenger.domain.service.impl;

import com.ccamargo.mathchallenger.domain.model.OperationMath;
import com.ccamargo.mathchallenger.domain.model.Percentage;
import com.ccamargo.mathchallenger.domain.port.PercentageProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CalculateServiceImplTest {

    @Mock
    private PercentageProvider percentageProvider;

    @InjectMocks
    CalculateServiceImpl calculateService;

    private OperationMath operationMath1;
    private OperationMath operationMath2;
    private Percentage percentage;

    @BeforeEach
    public void setUp() {
        percentage = new Percentage();
        percentage.setValue(0.1f);
        operationMath1 = new OperationMath(10f, 10f);
        operationMath2 = new OperationMath();
        operationMath2.setNum1(20);
        operationMath2.setNum2(20);
    }

    @Test
    void getOperationMath1() {
        float sumResult = operationMath1.add();
        when(percentageProvider.getValue()).thenReturn(percentage.getValue());
        float result = calculateService.getOperation(operationMath1);
        assertEquals(result, sumResult + (sumResult * percentage.getValue()));
    }

    @Test
    void getOperationMath2() {
        float sumResult = operationMath2.getNum1() + operationMath2.getNum2();
        when(percentageProvider.getValue()).thenReturn(percentage.getValue());
        float result = calculateService.getOperation(operationMath2);
        assertEquals(result, sumResult + (sumResult * percentage.getValue()));
    }
}