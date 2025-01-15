package com.ccamargo.mathchallenger.infrastructure.persistence.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity

@Table(name = "call_history")
public class CallHistoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "timestamp", nullable = false)
    private LocalDateTime timestamp;

    @Column(name = "endpoint", nullable = false)
    private String endpoint;

    @Column(name = "parameters", columnDefinition = "TEXT")
    private String parameters;

    @Column(name = "response", columnDefinition = "TEXT")
    private String response;

    public CallHistoryEntity() {
    }

    public CallHistoryEntity(Long id, LocalDateTime timestamp, String endpoint, String parameters, String response) {
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
