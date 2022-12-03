package com.binance.testnet.controller;

import com.binance.testnet.service.IBinanceService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

/**
 * Generated using openai.
 */
@ExtendWith(MockitoExtension.class)
public class BinanceControllerTest {
    @Mock
    private IBinanceService binanceService;

    @InjectMocks
    private BinanceController binanceController;

    @Test
    public void testPing() {
        when(binanceService.ping()).thenReturn("pong");
        assertEquals("pong", binanceController.ping());
    }

    @Test
    public void testTime() {
        when(binanceService.time()).thenReturn("1575462549495");
        assertEquals("1575462549495", binanceController.time());
    }

    @Test
    public void testExchangeInfo() {
        when(binanceService.exchangeInfo()).thenReturn("{...}");
        assertEquals("{...}", binanceController.exchangeInfo());
    }

    @Test
    public void testCreateOrder() {
        when(binanceService.createOrder("ETHBTC", "BUY", "MARKET",
                "GTC", "1.0", "0.0"))
                .thenReturn("{...}");
        assertEquals("{...}", binanceController.createOrder("ETHBTC",
                "BUY", "MARKET", "GTC", "1.0", "0.0"));
    }
}
