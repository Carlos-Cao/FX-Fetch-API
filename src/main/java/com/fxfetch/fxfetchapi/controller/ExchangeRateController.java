package com.fxfetch.fxfetchapi.controller;

import com.fxfetch.fxfetchapi.service.ExchangeRateService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/rates")
public class ExchangeRateController {

    private final ExchangeRateService exchangeRateService;

    public ExchangeRateController(ExchangeRateService exchangeRateService) {
        this.exchangeRateService = exchangeRateService;
    }

    @GetMapping("/latest/{currency}")
    public Map<String, Object> getLatest(@PathVariable String currency) {
        return exchangeRateService.getLatestRates(currency);
    }

    @GetMapping("/convert")
    public Map<String, Object> convert(@RequestParam String from, @RequestParam String to, @RequestParam double amount) {
        return exchangeRateService.convertCurrency(from, to, amount);
    }

    @GetMapping("/supported")
    public Map<String, Object> getSupportedCurrencies() {
        return exchangeRateService.getSupportedCurrencies();
    }
}