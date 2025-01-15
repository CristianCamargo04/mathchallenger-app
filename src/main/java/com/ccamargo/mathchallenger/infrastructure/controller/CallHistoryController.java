package com.ccamargo.mathchallenger.infrastructure.controller;

import com.ccamargo.mathchallenger.domain.model.CallHistory;
import com.ccamargo.mathchallenger.domain.model.Paginator;
import com.ccamargo.mathchallenger.domain.service.CallHistoryService;
import com.ccamargo.mathchallenger.infrastructure.aspect.annotation.LogCallHistory;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/call-history")
public class CallHistoryController {

    private final CallHistoryService callHistoryService;

    public CallHistoryController(CallHistoryService callHistoryService) {
        this.callHistoryService = callHistoryService;
    }

    @GetMapping
    @Operation(
            summary = "Obtener historial de llamadas paginado",
            description = "Este método obtiene un historial de llamadas de manera paginada, con parámetros para definir la página y la cantidad de elementos por página"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Historial de llamadas obtenido correctamente",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Paginator.class)
                    )
            ),
    })
    @LogCallHistory
    public ResponseEntity<Paginator<CallHistory>> getPagedCallHistory(
            @Schema(description = "Número de página", example = "0") @RequestParam int page,
            @Schema(description = "Cantidad de elementos por página", example = "10") @RequestParam int size) {
        return ResponseEntity.ok(callHistoryService.getPagedCallHistory(page, size));
    }
}
