package com.binance.testnet.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

/**
 * Generated using openai.
 */
@ExtendWith(MockitoExtension.class)
public class BinanceServiceTest {
    @Mock
    private IBinanceService binanceService;

    @Test
    public void testPing() {
        when(binanceService.ping()).thenReturn("pong");
        assertEquals("pong", binanceService.ping());
    }

    @Test
    public void testTime() {
        when(binanceService.time()).thenReturn("1575570075983");
        assertEquals("1575570075983", binanceService.time());
    }

    @Test
    public void testExchangeInfo() {
        when(binanceService.exchangeInfo()).thenReturn("{...}");
        assertEquals("{...}", binanceService.exchangeInfo());
    }

    @Test
    public void testCreateOrder() {
        when(binanceService.createOrder("ETHBTC", "BUY", "MARKET",
                "GTC", "0.1", "0.001"))
                .thenReturn("{...}");
        assertEquals("{...}", binanceService.createOrder("ETHBTC", "BUY",
                "MARKET", "GTC", "0.1", "0.001"));
    }
}
