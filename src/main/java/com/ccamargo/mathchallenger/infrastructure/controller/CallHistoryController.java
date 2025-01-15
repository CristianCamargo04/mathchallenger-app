package com.ccamargo.mathchallenger.infrastructure.controller;

import com.ccamargo.mathchallenger.domain.model.CallHistory;
import com.ccamargo.mathchallenger.domain.model.Paginator;
import com.ccamargo.mathchallenger.domain.service.CallHistoryService;
import com.ccamargo.mathchallenger.infrastructure.aspect.annotation.LogCallHistory;
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
    @LogCallHistory
    public ResponseEntity<Paginator<CallHistory>> getPagedCallHistory( @RequestParam int page, @RequestParam int size) {
        return ResponseEntity.ok(callHistoryService.getPagedCallHistory(page, size));
    }
}
