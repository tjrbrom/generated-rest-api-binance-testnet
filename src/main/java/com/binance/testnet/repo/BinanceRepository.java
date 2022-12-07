package com.binance.testnet.repo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Map;
import java.util.concurrent.CompletableFuture;

/**
 * Generated using openai.
 */
@Repository
public class BinanceRepository {
    private final WebClient webClient;
    @Value("${binance.api.base-url}")
    private String binanceApiBaseUrl;

    public BinanceRepository() {
        this.webClient = WebClient.create(binanceApiBaseUrl);
    }

    public CompletableFuture<String> ping() {
        return callBinanceApi("v1/ping", Map.of());
    }

    public CompletableFuture<String> time() {
        return callBinanceApi("v1/time", Map.of());
    }

    public CompletableFuture<String> exchangeInfo() {
        return callBinanceApi("v1/exchangeInfo", Map.of());
    }

    public CompletableFuture<String> createOrder(String symbol, String side, String type, String timeInForce, String quantity, String price) {
        return callBinanceApi("v3/order", Map.of(
                "symbol", symbol,
                "side", side,
                "type", type,
                "timeInForce", timeInForce,
                "quantity", quantity,
                "price", price
        ));
    }

    private CompletableFuture<String> callBinanceApi(String endpoint, Map<String, String> params) {
        MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();
        params.forEach(queryParams::add);

        return webClient.get()
                .uri(UriComponentsBuilder.fromPath(endpoint).queryParams(queryParams).build().toUri())
                .retrieve()
                .bodyToMono(String.class)
                .toFuture();
    }

}
