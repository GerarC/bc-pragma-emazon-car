package com.emazon.cart.adapters.driving.rest.service.impl;

import com.emazon.cart.adapters.driving.rest.dto.request.ItemRequest;
import com.emazon.cart.adapters.driving.rest.dto.response.CarResponse;
import com.emazon.cart.adapters.driving.rest.mapper.request.ItemRequestMapper;
import com.emazon.cart.adapters.driving.rest.mapper.response.CartResponseMapper;
import com.emazon.cart.adapters.driving.rest.service.CartService;
import com.emazon.cart.domain.api.CartServicePort;
import com.emazon.cart.domain.model.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {
    private final CartServicePort cartServicePort;
    private final CartResponseMapper cartResponseMapper;
    private final ItemRequestMapper itemRequestMapper;

    @Override
    public CarResponse addItem(String userId, ItemRequest itemRequest) {
        Item item = itemRequestMapper.toDomain(itemRequest);
        return cartResponseMapper.toResponse(
                cartServicePort.addItem(userId, item)
        );
    }

    @Override
    public CarResponse removeItem(String userId, Long productId) {
        return cartResponseMapper.toResponse(
                cartServicePort.removeItem(userId, productId)
        );
    }
}
