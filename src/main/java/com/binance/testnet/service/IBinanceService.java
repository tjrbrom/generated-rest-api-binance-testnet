package com.binance.testnet.service;

/**
 * Generated using openai.
 */
public interface IBinanceService {
    String ping();

    String time();

    String exchangeInfo();

    String createOrder(String symbol, String side, String type, String timeInForce, String quantity, String price);
}
