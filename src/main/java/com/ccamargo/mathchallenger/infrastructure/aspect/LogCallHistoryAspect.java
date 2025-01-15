package com.ccamargo.mathchallenger.infrastructure.aspect;

import com.ccamargo.mathchallenger.domain.model.CallHistory;
import com.ccamargo.mathchallenger.domain.service.CallHistoryService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

@Aspect
@Component
public class LogCallHistoryAspect {

    private final CallHistoryService callHistoryService;

    public LogCallHistoryAspect(CallHistoryService callHistoryService) {
        this.callHistoryService = callHistoryService;
    }

    @Pointcut("@annotation(com.ccamargo.mathchallenger.infrastructure.aspect.annotation.LogCallHistory)")
    public void logCallHistoryMethods() {}

    @AfterReturning(value = "logCallHistoryMethods()", returning = "result")
    public void logFromController(Object result) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        String responseBody = getResponseBodyAsString(result);

        CallHistory callHistory = new CallHistory();
        callHistory.setTimestamp(LocalDateTime.now());
        callHistory.setEndpoint(request.getRequestURI());
        callHistory.setParameters(getParameters(request));
        callHistory.setResponse(responseBody);

        callHistoryService.saveCallHistory(callHistory);
    }

    private String getParameters(HttpServletRequest request) {
        Map<String, String[]> parameterMap = request.getParameterMap();
        return parameterMap.entrySet()
                .stream()
                .map(entry -> entry.getKey() + "=" + Arrays.toString(entry.getValue()))
                .collect(Collectors.joining("&"));
    }

    private String getResponseBodyAsString(Object result) {
        if (result == null) {
            return "null";
        }
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(result);
        } catch (Exception e) {
            return result.toString();
        }
    }
}
