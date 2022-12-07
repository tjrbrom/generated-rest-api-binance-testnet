package com.binance.testnet.service;

import com.binance.testnet.repo.BinanceRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.concurrent.CompletableFuture;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BinanceServiceTest {
    @Mock
    private BinanceRepository binanceRepository;

    @InjectMocks
    private BinanceService binanceService;

    @Test
    public void testPing() {
        when(binanceRepository.ping()).thenReturn(CompletableFuture.completedFuture("pong"));

        CompletableFuture<String> future = binanceService.ping();

        verify(binanceRepository).ping();

        assertEquals("pong", future.getNow(null));
    }

    @Test
    public void testTime() {
        when(binanceRepository.time()).thenReturn(CompletableFuture.completedFuture("{\"serverTime\": 123456}"));

        CompletableFuture<String> future = binanceService.time();

        verify(binanceRepository).time();

        assertEquals("{\"serverTime\": 123456}", future.getNow(null));
    }

    @Test
    public void testExchangeInfo() {
        when(binanceRepository.exchangeInfo()).thenReturn(CompletableFuture.completedFuture("{\"timezone\": \"UTC\"}"));

        CompletableFuture<String> future = binanceService.exchangeInfo();

        verify(binanceRepository).exchangeInfo();

        assertEquals("{\"timezone\": \"UTC\"}", future.getNow(null));
    }

    @Test
    public void testCreateOrder() {
        when(binanceRepository.createOrder("symbol", "side", "type", "timeInForce", "quantity", "price"))
                .thenReturn(CompletableFuture.completedFuture("{\"orderId\": 123456}"));

        CompletableFuture<String> future = binanceService.createOrder("symbol", "side", "type", "timeInForce", "quantity", "price");

        verify(binanceRepository).createOrder("symbol", "side", "type", "timeInForce", "quantity", "price");

        assertEquals("{\"orderId\": 123456}", future.getNow(null));
    }

}
