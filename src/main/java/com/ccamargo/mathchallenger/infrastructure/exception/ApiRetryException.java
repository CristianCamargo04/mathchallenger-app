package com.ccamargo.mathchallenger.infrastructure.exception;

public class ApiRetryException extends RuntimeException {

    public ApiRetryException(String message, Throwable cause) {
        super(message, cause);
    }
}
