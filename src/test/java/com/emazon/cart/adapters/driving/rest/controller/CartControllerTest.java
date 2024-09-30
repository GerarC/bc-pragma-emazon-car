package com.emazon.cart.adapters.driving.rest.controller;

import com.emazon.cart.adapters.driving.rest.v1.controller.CartController;
import com.emazon.cart.adapters.driving.rest.v1.dto.request.ItemRequest;
import com.emazon.cart.adapters.driving.rest.v1.dto.response.CarResponse;
import com.emazon.cart.adapters.driving.rest.v1.dto.response.FullItemResponse;
import com.emazon.cart.adapters.driving.rest.v1.dto.response.ItemResponse;
import com.emazon.cart.adapters.driving.rest.v1.dto.response.PageResponse;
import com.emazon.cart.adapters.driving.rest.v1.service.CartService;
import com.emazon.cart.adapters.driving.rest.v1.utils.JsonParser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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
        ItemResponse itemResponse = new ItemResponse(5L, "name", BigDecimal.ONE, 10, true, null);
        CarResponse carResponse = new CarResponse(userId, LocalDateTime.now(), LocalDateTime.now(), List.of(itemResponse));
        when(cartService.addItem(any())).thenReturn(carResponse);
        this.mockMvc.perform(post("/carts/items")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonParser.toJson(itemRequest)))
                .andExpect(status().isOk());
    }

    @Test
    void deleteItem() throws Exception {
        String userId = "userId";
        ItemResponse itemResponse = new ItemResponse(5L, "name", BigDecimal.ONE, 10, true, null);
        CarResponse carResponse = new CarResponse(userId, LocalDateTime.now(), LocalDateTime.now(), List.of(itemResponse));
        when(cartService.removeItem(any())).thenReturn(carResponse);
        this.mockMvc.perform(delete("/carts/items/{id}", 5L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void getCartItems() throws Exception {
        List<FullItemResponse> responses = new ArrayList<>(List.of(
                new FullItemResponse(1L, "name", BigDecimal.ONE, 10,
                        1000L, "brand", List.of("Category1", "Category2"),
                        true, null),
                new FullItemResponse(2L, "name", BigDecimal.ONE, 10,
                        1000L, "brand", List.of("Category2", "Category3"),
                        true, null),
                new FullItemResponse(3L, "name", BigDecimal.ONE, 10,
                        1000L, "brand", List.of("Category3", "Category4"),
                        true, null),
                new FullItemResponse(4L, "name", BigDecimal.ONE, 10,
                        1000L, "brand", List.of("Category4", "Category5"),
                        true, null)
        ));
        PageResponse<FullItemResponse> pageResponse = new PageResponse<>(0, 1, 1, 4, 4L, BigDecimal.valueOf(40), responses);

        when(cartService.getItems(any(), any())).thenReturn(pageResponse);
        this.mockMvc.perform(get("/carts/items")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }
}