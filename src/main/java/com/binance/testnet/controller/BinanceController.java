package com.binance.testnet.controller;

import com.binance.testnet.service.IBinanceService;
import com.binance.testnet.service.RabbitMQSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

/**
 * Generated using openai.
 */
@RestController
@CacheConfig(cacheNames = "binance")
public class BinanceController {

    @Autowired
    private IBinanceService binanceService;

    @Autowired
    private RabbitMQSender rabbitMQSender;

    @Cacheable
    @GetMapping("/ping")
    public CompletableFuture<String> ping() {
        return binanceService.ping();
    }

    @Cacheable
    @GetMapping("/time")
    public CompletableFuture<String> time() {
        return binanceService.time();
    }

    @Cacheable
    @GetMapping("/exchangeInfo")
    public CompletableFuture<String> exchangeInfo() {
        return binanceService.exchangeInfo();
    }

    @CachePut
    @PostMapping("/order")
    public CompletableFuture<String> createOrder(@RequestParam("symbol") String symbol,
                                                 @RequestParam("side") String side,
                                                 @RequestParam("type") String type,
                                                 @RequestParam("timeInForce") String timeInForce,
                                                 @RequestParam("quantity") String quantity,
                                                 @RequestParam("price") String price) {
        // Create the order on Binance
        CompletableFuture<String> result = binanceService.createOrder(symbol, side, type, timeInForce, quantity, price);

        // Send a message to RabbitMQ when the order has been created
        return result.thenApply(order -> {
            rabbitMQSender.send("Order created: " + order);
            return order;
        });
    }
}
