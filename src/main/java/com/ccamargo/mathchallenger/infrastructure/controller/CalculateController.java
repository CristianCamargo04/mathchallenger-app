package com.ccamargo.mathchallenger.infrastructure.controller;

import com.ccamargo.mathchallenger.domain.model.OperationMath;
import com.ccamargo.mathchallenger.domain.service.CalculateService;
import com.ccamargo.mathchallenger.infrastructure.aspect.annotation.LogCallHistory;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
    @Operation(
            summary = "Realiza una operación matemática",
            description = "Este método realiza una operación matemática según los datos proporcionados"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Operación realizada exitosamente",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Float.class)
                    )
            ),
            @ApiResponse(responseCode = "429", description = "No se pudo recuperar el porcentaje después de varios intentos")
    })
    @LogCallHistory
    public ResponseEntity<Float> getOperation(@RequestBody OperationMath operationMath) {
        return ResponseEntity.ok(calculateService.getOperation(operationMath));
    }
}
