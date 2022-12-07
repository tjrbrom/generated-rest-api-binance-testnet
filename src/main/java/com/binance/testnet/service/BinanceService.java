package com.binance.testnet.service;

import com.binance.testnet.repo.BinanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class BinanceService implements IBinanceService {
    @Autowired
    private BinanceRepository binanceRepository;

    @Override
    public CompletableFuture<String> ping() {
        return binanceRepository.ping();
    }

    @Override
    public CompletableFuture<String> time() {
        return binanceRepository.time();
    }

    @Override
    public CompletableFuture<String> exchangeInfo() {
        return binanceRepository.exchangeInfo();
    }

    @Override
    public CompletableFuture<String> createOrder(String symbol, String side, String type, String timeInForce, String quantity, String price) {
        return binanceRepository.createOrder(symbol, side, type, timeInForce, quantity, price);
    }
}
