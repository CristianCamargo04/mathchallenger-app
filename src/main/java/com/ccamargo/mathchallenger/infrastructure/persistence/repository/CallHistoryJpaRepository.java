package com.ccamargo.mathchallenger.infrastructure.persistence.repository;

import com.ccamargo.mathchallenger.infrastructure.persistence.entity.CallHistoryEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CallHistoryJpaRepository extends JpaRepository<CallHistoryEntity, Long> {

    Page<CallHistoryEntity> findAll(Pageable pageable);
}
