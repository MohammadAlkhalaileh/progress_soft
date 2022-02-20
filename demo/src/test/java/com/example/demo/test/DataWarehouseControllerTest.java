package com.example.demo.test;

import com.example.demo.controller.DataWarehouseController;
import com.example.demo.repository.OrderRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(DataWarehouseController.class)
public class DataWarehouseControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    OrderRepository orderRepository;

    @Test
    void shouldSaveValidOrderSuccessfully() throws Exception {

        this.mockMvc
                .perform(
                        post("/warehouse/orders")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("{ \"orderId\":1, \"fromCurrency\":\"OMR\", \"toCurrency\":\"JOD\", \"amount\":\"100\"}"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void shouldFailAndReturnErrorCode400WhenIdFieldIsMissingFromTheRequest() throws Exception {

        this.mockMvc
                .perform(
                        post("/warehouse/orders")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("{ \"fromCurrency\":\"OMR\", \"toCurrency\":\"JOD\", \"amount\":\"100\"}"))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    void shouldFailAndReturnErrorCode400WhenInvalidCurrencyCodeIsUsedInTheRequest() throws Exception {

        this.mockMvc
                .perform(
                        post("/warehouse/orders")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("{ \"orderId\":1, \"fromCurrency\":\"OMR32\", \"toCurrency\":\"JOD\", \"amount\":\"100\"}"))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    void shouldFailAndReturnErrorCode500WhenAnyFailureHappen() throws Exception {
        when(orderRepository.save(any())).thenThrow(RuntimeException.class);

        this.mockMvc
                .perform(
                        post("/warehouse/orders")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("{\"orderId\":1, \"fromCurrency\":\"OMR\", \"toCurrency\":\"JOD\", \"amount\":\"100\"}"))
                .andDo(print())
                .andExpect(status().is5xxServerError());
    }
}
