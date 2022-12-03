package com.binance.testnet.service;

import com.binance.testnet.repo.BinanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Generated using openai.
 */
@Service
public class BinanceService implements IBinanceService {
    @Autowired
    private BinanceRepository binanceRepository;

    @Override
    public String ping() {
        return binanceRepository.ping();
    }

    @Override
    public String time() {
        return binanceRepository.time();
    }

    @Override
    public String exchangeInfo() {
        return binanceRepository.exchangeInfo();
    }

    @Override
    public String createOrder(String symbol, String side, String type,
                              String timeInForce, String quantity, String price) {
        return binanceRepository.createOrder(symbol, side, type, timeInForce, quantity, price);
    }
}