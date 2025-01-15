package com.ccamargo.mathchallenger.domain.service.impl;

import com.ccamargo.mathchallenger.domain.model.CallHistory;
import com.ccamargo.mathchallenger.domain.model.Paginator;
import com.ccamargo.mathchallenger.domain.port.CallHistoryRepository;
import com.ccamargo.mathchallenger.domain.service.CallHistoryService;

public class CallHistoryServiceImpl implements CallHistoryService {

    private final CallHistoryRepository callHistoryRepository;

    public CallHistoryServiceImpl(CallHistoryRepository callHistoryRepository) {
        this.callHistoryRepository = callHistoryRepository;
    }

    @Override
    public void saveCallHistory(CallHistory callHistory) {
        callHistoryRepository.saveCallHistory(callHistory);
    }

    @Override
    public Paginator<CallHistory> getPagedCallHistory(int page, int size) {
        return callHistoryRepository.getPagedCallHistory(page, size);
    }
}
