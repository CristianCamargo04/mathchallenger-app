package com.ccamargo.mathchallenger.infrastructure.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ApplicationProperties {
    @Value("${mock.server.url}")
    private String mockServerUrl;
    @Value("${cache.time.minutes}")
    private int cacheTimeMinutes;
    @Value("${spring.retry.maxAttempts}")
    private int springRetryMaxAttempts;
    @Value("${spring.retry.time}")
    private long springRetryTime;

    @Value("${spring.redis.host}")
    private String springRedisHost;
    @Value("${spring.redis.port}")
    private int springRedisPort;

    public String getMockServerUrl() {
        return mockServerUrl;
    }

    public int getCacheTimeMinutes() {
        return cacheTimeMinutes;
    }

    public int getSpringRetryMaxAttempts() {
        return springRetryMaxAttempts;
    }

    public long getSpringRetryTime() {
        return springRetryTime;
    }

    public String getSpringRedisHost() {
        return springRedisHost;
    }

    public int getSpringRedisPort() {
        return springRedisPort;
    }
}
