package com.emazon.cart.adapters.driving.rest.service.impl;

import com.emazon.cart.adapters.driving.rest.dto.request.ItemRequest;
import com.emazon.cart.adapters.driving.rest.dto.response.CarResponse;
import com.emazon.cart.adapters.driving.rest.dto.response.ItemResponse;
import com.emazon.cart.adapters.driving.rest.mapper.request.ItemRequestMapper;
import com.emazon.cart.adapters.driving.rest.mapper.response.CartResponseMapper;
import com.emazon.cart.domain.api.CartServicePort;
import com.emazon.cart.domain.model.Cart;
import com.emazon.cart.domain.model.Item;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class CartServiceImplTest {

    @Mock
    private CartServicePort cartServicePort;
    @Mock
    private CartResponseMapper cartResponseMapper;
    @Mock
    private ItemRequestMapper itemRequestMapper;

    @InjectMocks
    private CartServiceImpl carServiceImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void addItem() {
        String carId = "carId";
        String userId = "userId";
        Item item = new Item(5L, 5L, 10, null);
        ItemRequest itemRequest = new ItemRequest(5L, 10);
        ItemResponse itemResponse = new ItemResponse(5L, 10);
        Cart cart = new Cart(carId, userId, LocalDateTime.now(), LocalDateTime.now(), List.of(item));
        CarResponse carResponse = new CarResponse(userId, LocalDateTime.now(), LocalDateTime.now(), List.of(itemResponse));
        when(cartResponseMapper.toResponse(any())).thenReturn(carResponse);
        when(itemRequestMapper.toDomain(any())).thenReturn(item);
        when(cartServicePort.addItem(any(), any())).thenReturn(cart);
        CarResponse returnedResponse = carServiceImpl.addItem(userId, itemRequest);
        verify(cartServicePort).addItem(any(), any());
        assertNotNull(returnedResponse);
        assertEquals(carResponse, returnedResponse);
    }

    @Test
    void removeItem() {
        String carId = "carId";
        String userId = "userId";
        Item item = new Item(5L, 5L, 10, null);
        ItemResponse itemResponse = new ItemResponse(5L, 10);
        Cart cart = new Cart(carId, userId, LocalDateTime.now(), LocalDateTime.now(), List.of(item));
        CarResponse carResponse = new CarResponse(userId, LocalDateTime.now(), LocalDateTime.now(), List.of(itemResponse));

        when(cartResponseMapper.toResponse(any())).thenReturn(carResponse);
        when(cartServicePort.removeItem(any(), any())).thenReturn(cart);
        CarResponse response = carServiceImpl.removeItem(userId, item.getId());
        verify(cartServicePort).removeItem(any(), any());
        assertNotNull(response);
        assertEquals(carResponse, response);
    }
}