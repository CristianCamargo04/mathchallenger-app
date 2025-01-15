package com.ccamargo.mathchallenger.infrastructure.adapter;

import com.ccamargo.mathchallenger.domain.model.CallHistory;
import com.ccamargo.mathchallenger.domain.model.Paginator;
import com.ccamargo.mathchallenger.domain.port.CallHistoryRepository;
import com.ccamargo.mathchallenger.infrastructure.persistence.entity.CallHistoryEntity;
import com.ccamargo.mathchallenger.infrastructure.persistence.mapper.CallHistoryMapper;
import com.ccamargo.mathchallenger.infrastructure.persistence.repository.CallHistoryJpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CallHistoryRepositoryAdapter implements CallHistoryRepository {

    private final CallHistoryJpaRepository callHistoryJpaRepository;
    private final CallHistoryMapper callHistoryMapper;

    public CallHistoryRepositoryAdapter(CallHistoryJpaRepository callHistoryJpaRepository, CallHistoryMapper callHistoryMapper) {
        this.callHistoryJpaRepository = callHistoryJpaRepository;
        this.callHistoryMapper = callHistoryMapper;
    }

    @Override
    public void saveCallHistory(CallHistory callHistory) {
        CallHistoryEntity callHistoryEntity = callHistoryMapper.toCallHistoryEntity(callHistory);
        callHistoryJpaRepository.save(callHistoryEntity);
    }

    @Override
    public Paginator<CallHistory> getPagedCallHistory(int page, int size) {
        var pageRequest = PageRequest.of(page, size);
        Page<CallHistoryEntity> entityPage = callHistoryJpaRepository.findAll(pageRequest);

        List<CallHistory> callHistories = entityPage.getContent().stream()
                .map(callHistoryMapper::toCallHistory)
                .collect(Collectors.toList());

        return new Paginator<>(callHistories, (int) entityPage.getTotalElements(),
                entityPage.getTotalPages(), page, size);
    }
}
