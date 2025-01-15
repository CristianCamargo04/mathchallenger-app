package com.ccamargo.mathchallenger.infrastructure.controller;

import com.ccamargo.mathchallenger.domain.model.OperationMath;
import com.ccamargo.mathchallenger.domain.service.CalculateService;
import com.ccamargo.mathchallenger.infrastructure.aspect.annotation.LogCallHistory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/calculate")
public class CalculateController {

    private final CalculateService calculateService;

    public CalculateController(CalculateService calculateService) {
        this.calculateService = calculateService;
    }

    @PostMapping
    @LogCallHistory
    public ResponseEntity<Float> getOperation(@RequestBody OperationMath operationMath) {
        return ResponseEntity.ok(calculateService.getOperation(operationMath));
    }
}
