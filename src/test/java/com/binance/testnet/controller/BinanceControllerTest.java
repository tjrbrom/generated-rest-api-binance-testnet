package com.binance.testnet.controller;

import com.binance.testnet.service.IBinanceService;
import com.binance.testnet.service.RabbitMQSender;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.concurrent.CompletableFuture;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
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

    @Mock
    private RabbitMQSender rabbitMQSender;

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
        // Mock the createOrder method of the BinanceService
        CompletableFuture<String> result = CompletableFuture.completedFuture("order-id");
        when(binanceService.createOrder(anyString(), anyString(), anyString(), anyString(), anyString(), anyString()))
                .thenReturn(result);

        // Send a POST request to the /order endpoint
        MvcResult mvcResult = mockMvc.perform(
                        post("/order")
                                .param("symbol", "BNBETH")
                                .param("side", "BUY")
                                .param("type", "LIMIT")
                                .param("timeInForce", "GTC")
                                .param("quantity", "10.0")
                                .param("price", "0.0005"))
                .andExpect(status().isOk())
                .andReturn();

        // Verify that the BinanceService's createOrder method was called with the correct arguments
        verify(binanceService).createOrder(
                eq("BNBETH"), eq("BUY"), eq("LIMIT"), eq("GTC"), eq("10.0"), eq("0.0005"));

        // Verify that the response body is the expected result
        assertEquals("order-id", mvcResult.getResponse().getContentAsString());
    }

}
