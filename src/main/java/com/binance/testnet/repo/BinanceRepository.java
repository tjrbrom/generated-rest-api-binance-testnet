package com.binance.testnet.repo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

/**
 * Generated using openai.
 */
@Repository
public class BinanceRepository {
    @Value("${binance.api.base-url}")
    private String binanceApiBaseUrl;

    public String ping() {
        return callBinanceApi("v1/ping");
    }

    public String time() {
        return callBinanceApi("v1/time");
    }

    public String exchangeInfo() {
        return callBinanceApi("v1/exchangeInfo");
    }

    public String createOrder(String symbol, String side, String type, String timeInForce, String quantity, String price) {
        StringBuilder params = new StringBuilder();
        params.append("symbol=").append(symbol);
        params.append("&side=").append(side);
        params.append("&type=").append(type);
        params.append("&timeInForce=").append(timeInForce);
        params.append("&quantity=").append(quantity);
        params.append("&price=").append(price);
        return callBinanceApi("v3/order?" + params);
    }

    String callBinanceApi(String endpoint) {
        String url = binanceApiBaseUrl + endpoint;
        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.getForObject(url, String.class);
        return response;
    }
}