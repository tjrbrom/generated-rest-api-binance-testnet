package com.binance.testnet.controller;

import com.binance.testnet.service.IBinanceService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.concurrent.CompletableFuture;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class BinanceControllerTest {
    private MockMvc mockMvc;

    @Mock
    private IBinanceService binanceService;

    @InjectMocks
    private BinanceController binanceController;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(binanceController).build();
    }

    @Test
    void testPing() throws Exception {
        when(binanceService.ping()).thenReturn(CompletableFuture.completedFuture("pong"));

        mockMvc.perform(get("/ping"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", is("pong")));
    }

    @Test
    void testTime() throws Exception {
        when(binanceService.time()).thenReturn(CompletableFuture.completedFuture("1594289800000"));

        mockMvc.perform(get("/time"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", is("1594289800000")));
    }

    @Test
    void testExchangeInfo() throws Exception {
        when(binanceService.exchangeInfo()).thenReturn(CompletableFuture.completedFuture("{...}"));

        mockMvc.perform(get("/exchangeInfo"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", is("{...}")));
    }

    @Test
    void testCreateOrder() throws Exception {
        when(binanceService.createOrder("symbol", "side", "type", "timeInForce", "quantity", "price"))
                .thenReturn(CompletableFuture.completedFuture("{...}"));

        mockMvc.perform(post("/order")
                        .param("symbol", "symbol")
                        .param("side", "side")
                        .param("type", "type")
                        .param("timeInForce", "timeInForce")
                        .param("quantity", "quantity")
                        .param("price", "price"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", is("{...}")));
    }

}
