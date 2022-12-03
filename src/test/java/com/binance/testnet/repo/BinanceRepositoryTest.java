package com.binance.testnet.repo;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Generated using openai.
 */
@ExtendWith(MockitoExtension.class)
public class BinanceRepositoryTest {
    @Mock
    private RestTemplate restTemplate;

    @Mock
    private BinanceRepository binanceRepository;

    @Test
    public void testPing() {
        when(binanceRepository.callBinanceApi("v1/ping")).thenReturn("pong");
        assertEquals("pong", binanceRepository.ping());
        verify(restTemplate).getForObject("https://testnet.binance.com/api/v1/ping", String.class);
    }

    @Test
    public void testTime() {
        when(binanceRepository.callBinanceApi("v1/time")).thenReturn("1575570075983");
        assertEquals("1575570075983", binanceRepository.time());
        verify(restTemplate).getForObject("https://testnet.binance.com/api/v1/time", String.class);
    }

    @Test
    public void testExchangeInfo() {
        when(binanceRepository.callBinanceApi("v1/exchangeInfo")).thenReturn("{...}");
        assertEquals("{...}", binanceRepository.exchangeInfo());
        verify(restTemplate).getForObject("https://testnet.binance.com/api/v1/exchangeInfo", String.class);
    }

    @Test
    public void testCreateOrder() {
        when(binanceRepository.callBinanceApi("v3/order?symbol=ETHBTC&side=BUY&type=MARKET&timeInForce=GTC&quantity=0.1&price=0.001"))
                .thenReturn("{...}");
        assertEquals("{...}", binanceRepository.createOrder("ETHBTC", "BUY", "MARKET", "GTC", "0.1", "0.001"));
        verify(restTemplate).getForObject("https://testnet.binance.com/api/v3/order?symbol=ETHBTC&side=BUY&type=MARKET&timeInForce=GTC&quantity=0.1&price=0.001", String.class);
    }
}
