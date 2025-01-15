package com.ccamargo.mathchallenger.infrastructure.exception;

public class ServiceUnavailableException extends RuntimeException {

    public ServiceUnavailableException(String message, Throwable cause) {
        super(message, cause);
    }
}
