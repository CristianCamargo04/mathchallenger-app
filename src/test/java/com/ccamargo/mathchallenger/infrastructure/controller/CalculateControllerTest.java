package com.ccamargo.mathchallenger.infrastructure.controller;

import com.ccamargo.mathchallenger.domain.model.OperationMath;
import com.ccamargo.mathchallenger.domain.service.CalculateService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CalculateControllerTest {

    @Mock
    private CalculateService calculateService;

    @InjectMocks
    private CalculateController calculateController;

    @Test
    public void testGetOperation() {
        OperationMath operationMath = new OperationMath(3, 2);
        when(calculateService.getOperation(operationMath)).thenReturn(5.0f);

        ResponseEntity<Float> response = calculateController.getOperation(operationMath);

        assertEquals(5.0f, response.getBody());
        assertEquals(200, response.getStatusCodeValue());
    }
}
