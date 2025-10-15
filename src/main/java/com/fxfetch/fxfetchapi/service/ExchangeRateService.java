package com.fxfetch.fxfetchapi.service;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Value;
import java.util.Map;

@Service
public class ExchangeRateService {

    @Value("${exchange-rate.api.key}")
    private String apiKey;

    private static final String BASE_URL = "https://v6.exchangerate-api.com/v6/";

    public Map<String, Object> getLatestRates(String baseCurrency) {
        String url = BASE_URL + apiKey + "/latest/" + baseCurrency;

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Map<String, Object>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {
                }
        );
        return response.getBody();
    }

    public Map<String, Object> convertCurrency(String from, String to, double amount) {
        String url = BASE_URL + apiKey + "/pair/" + from + "/" + to + "/" + amount;
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Map<String, Object>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {
                }
        );
        return response.getBody();
    }
}