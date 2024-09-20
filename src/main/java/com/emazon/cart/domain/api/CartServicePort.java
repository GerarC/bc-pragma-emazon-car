package com.emazon.cart.domain.api;

import com.emazon.cart.domain.model.Cart;
import com.emazon.cart.domain.model.Item;

public interface CartServicePort {
    Cart addItem(String userId, Item item);
    Cart removeItem(String userId, Long itemId);
}