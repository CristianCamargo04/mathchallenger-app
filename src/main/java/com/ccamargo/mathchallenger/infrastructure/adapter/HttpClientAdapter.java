package com.ccamargo.mathchallenger.infrastructure.adapter;

import com.ccamargo.mathchallenger.domain.port.HttpClient;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class HttpClientAdapter implements HttpClient {

    private final RestTemplate restTemplate;

    public HttpClientAdapter(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public <T> T get(String endpoint, Class<T> responseType) {
        return restTemplate.getForObject(endpoint, responseType);
    }
}
