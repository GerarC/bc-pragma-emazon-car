package com.emazon.cart.adapters.driving.rest.service;

import com.emazon.cart.adapters.driving.rest.dto.request.ItemRequest;
import com.emazon.cart.adapters.driving.rest.dto.response.CarResponse;

public interface CartService {
    CarResponse addItem(String userId, ItemRequest itemRequest);
    CarResponse removeItem(String userId, Long productId);
}
