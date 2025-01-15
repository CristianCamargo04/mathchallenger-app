package com.ccamargo.mathchallenger.infrastructure.adapter;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class HttpClientAdapterTest {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private HttpClientAdapter httpClientAdapter;

    private static final String ENDPOINT = "http://test-endpoint.com";
    private static final String RESPONSE = "response";

    @Test
    public void testGet() {
        when(restTemplate.getForObject(ENDPOINT, String.class)).thenReturn(RESPONSE);

        String result = httpClientAdapter.get(ENDPOINT, String.class);

        assertNotNull(result);
        assertEquals(RESPONSE, result);
        verify(restTemplate, times(1)).getForObject(ENDPOINT, String.class);
    }
}
