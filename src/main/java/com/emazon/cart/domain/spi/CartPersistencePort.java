package com.emazon.cart.domain.spi;

import com.emazon.cart.domain.model.Cart;

public interface CartPersistencePort {
    Cart createCar(Cart cart);
    Cart getCarById(String id);
    Cart getCarByUserId(String userId);
    Cart updateCar(Cart cart);
}
