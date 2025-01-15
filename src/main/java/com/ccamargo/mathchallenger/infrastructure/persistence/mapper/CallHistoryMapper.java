package com.ccamargo.mathchallenger.infrastructure.persistence.mapper;

import com.ccamargo.mathchallenger.domain.model.CallHistory;
import com.ccamargo.mathchallenger.infrastructure.persistence.entity.CallHistoryEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface CallHistoryMapper {
    @Mappings({
        @Mapping(source = "id", target = "id"),
        @Mapping(source = "timestamp", target = "timestamp"),
        @Mapping(source = "endpoint", target = "endpoint"),
        @Mapping(source = "parameters", target = "parameters"),
        @Mapping(source = "response", target = "response")
    })
    CallHistory toCallHistory(CallHistoryEntity callHistory);

    @InheritInverseConfiguration
    CallHistoryEntity toCallHistoryEntity(CallHistory callHistory);
}
