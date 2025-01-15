package com.ccamargo.mathchallenger.domain.service;

import com.ccamargo.mathchallenger.domain.model.CallHistory;
import com.ccamargo.mathchallenger.domain.model.Paginator;

public interface CallHistoryService {

    void saveCallHistory(CallHistory callHistory);

    Paginator<CallHistory> getPagedCallHistory(int page, int size);
}
