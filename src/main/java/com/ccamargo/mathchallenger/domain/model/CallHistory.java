package com.ccamargo.mathchallenger.domain.model;

import java.time.LocalDateTime;

public class CallHistory {
    private Long id;
    private LocalDateTime timestamp;
    private String endpoint;
    private String parameters;
    private String response;

    public CallHistory() {
    }

    public CallHistory(Long id, LocalDateTime timestamp, String endpoint, String parameters, String response) {
        this.id = id;
        this.timestamp = timestamp;
        this.endpoint = endpoint;
        this.parameters = parameters;
        this.response = response;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public String getParameters() {
        return parameters;
    }

    public void setParameters(String parameters) {
        this.parameters = parameters;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }
}
