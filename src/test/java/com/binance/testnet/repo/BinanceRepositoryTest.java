package com.binance.testnet.repo;

import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
public class BinanceRepositoryTest {

    public static MockWebServer mockBackEnd;

    private static BinanceRepository binanceRepository;

    @BeforeAll
    static void setUp() throws IOException {
        binanceRepository = new BinanceRepository();
        mockBackEnd = new MockWebServer();
        mockBackEnd.start();
    }

    @AfterAll
    static void tearDown() throws IOException {
        mockBackEnd.shutdown();
    }

    @Test
    void ping() throws Exception {
        mockBackEnd.enqueue(new MockResponse()
                .addHeader("Content-Type", "application/json")
                .setBody("{\"serverTime\": 123456}"));

        CompletableFuture<String> response = binanceRepository.ping();

        assertTrue(response.isDone());
        assertEquals("{\"serverTime\": 123456}", response.get());
    }

    @Test
    void time() throws Exception {
        mockBackEnd.enqueue(new MockResponse()
                .addHeader("Content-Type", "application/json")
                .setBody("{\"serverTime\": 123456}"));

        CompletableFuture<String> response = binanceRepository.time();

        assertTrue(response.isDone());
        assertEquals("{\"serverTime\": 123456}", response.get());
    }

    @Test
    void exchangeInfo() throws Exception {
        mockBackEnd.enqueue(new MockResponse()
                .addHeader("Content-Type", "application/json")
                .setBody("{\"timezone\": \"UTC\", \"serverTime\": 123456}"));

        CompletableFuture<String> response = binanceRepository.exchangeInfo();

        assertTrue(response.isDone());
        assertEquals("{\"timezone\": \"UTC\", \"serverTime\": 123456}",
                response.get());
    }

    @Test
    void createOrder() throws Exception {
        mockBackEnd.enqueue(new MockResponse()
                .addHeader("Content-Type", "application/json")
                .setBody("{\"symbol\": \"BTCUSDT\", \"orderId\": 123456, \"status\": \"FILLED\"}"));

        CompletableFuture<String> response = binanceRepository.createOrder("BTCUSDT", "BUY", "LIMIT", "GTC", "1.0", "10500.0");

        assertTrue(response.isDone());
        assertEquals("{\"symbol\": \"BTCUSDT\", \"orderId\": 123456, \"status\": \"FILLED\"}", response.get());
    }

}
