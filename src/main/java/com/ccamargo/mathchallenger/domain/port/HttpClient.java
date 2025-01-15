package com.ccamargo.mathchallenger.domain.port;

public interface HttpClient {

    <T> T get(String endpoint, Class<T> responseType);
}
