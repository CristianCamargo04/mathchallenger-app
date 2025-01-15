package com.ccamargo.mathchallenger.domain.port;

import com.ccamargo.mathchallenger.domain.model.CallHistory;
import com.ccamargo.mathchallenger.domain.model.Paginator;

public interface CallHistoryRepository {

    void saveCallHistory(CallHistory callHistory);

    Paginator<CallHistory> getPagedCallHistory(int page, int size);
}
