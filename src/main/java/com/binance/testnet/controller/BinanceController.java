package com.binance.testnet.controller;

import com.binance.testnet.service.IBinanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

/**
 * Generated using openai.
 */
@RestController
public class BinanceController {
    @Autowired
    private IBinanceService binanceService;

    @GetMapping("/ping")
    public CompletableFuture<String> ping() {
        return binanceService.ping();
    }

    @GetMapping("/time")
    public CompletableFuture<String> time() {
        return binanceService.time();
    }

    @GetMapping("/exchangeInfo")
    public CompletableFuture<String> exchangeInfo() {
        return binanceService.exchangeInfo();
    }

    @PostMapping("/order")
    public CompletableFuture<String> createOrder(@RequestParam("symbol") String symbol,
                                                 @RequestParam("side") String side,
                                                 @RequestParam("type") String type,
                                                 @RequestParam("timeInForce") String timeInForce,
                                                 @RequestParam("quantity") String quantity,
                                                 @RequestParam("price") String price) {
        return binanceService.createOrder(symbol, side, type, timeInForce, quantity, price);
    }
}
