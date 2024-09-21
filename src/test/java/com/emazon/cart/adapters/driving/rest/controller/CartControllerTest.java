package com.emazon.cart.adapters.driving.rest.controller;

import com.emazon.cart.adapters.driving.rest.dto.request.ItemRequest;
import com.emazon.cart.adapters.driving.rest.dto.response.CarResponse;
import com.emazon.cart.adapters.driving.rest.dto.response.ItemResponse;
import com.emazon.cart.adapters.driving.rest.service.CartService;
import com.emazon.cart.adapters.driving.rest.utils.JsonParser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(value = CartController.class)
@AutoConfigureMockMvc(addFilters = false)
class CartControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    CartService cartService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void addItem() throws Exception {
        String userId = "userId";
        ItemRequest itemRequest = new ItemRequest(5L, 10);
        ItemResponse itemResponse = new ItemResponse(5L, 10);
        CarResponse carResponse = new CarResponse(userId, LocalDateTime.now(), LocalDateTime.now(), List.of(itemResponse));
        when(cartService.addItem(any(), any())).thenReturn(carResponse);
        this.mockMvc.perform(post("/cars/{user-id}/items", userId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonParser.toJson(itemRequest)))
                .andExpect(status().isOk());
    }

    @Test
    void deleteItem() throws Exception {
        String userId = "userId";
        ItemResponse itemResponse = new ItemResponse(5L, 10);
        CarResponse carResponse = new CarResponse(userId, LocalDateTime.now(), LocalDateTime.now(), List.of(itemResponse));
        when(cartService.removeItem(any(), any())).thenReturn(carResponse);
        this.mockMvc.perform(delete("/cars/{user-id}/items/{id}", userId, 5L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}