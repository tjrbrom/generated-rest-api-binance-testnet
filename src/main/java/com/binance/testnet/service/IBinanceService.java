package com.binance.testnet.service;

import java.util.concurrent.CompletableFuture;

/**
 * Generated using openai.
 */
public interface IBinanceService {
    CompletableFuture<String> ping();

    CompletableFuture<String> time();

    CompletableFuture<String> exchangeInfo();

    CompletableFuture<String> createOrder(String symbol, String side, String type, String timeInForce, String quantity, String price);
}
