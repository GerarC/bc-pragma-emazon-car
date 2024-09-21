package com.emazon.cart.domain.spi;

import com.emazon.cart.domain.model.Item;

public interface ItemPersistencePort {
    Item save(Item item);
    void deleteById(Long id);
    boolean existsByProductIdAndCarId(Long productId, String carId);
    Item findByProductIdAndCarId(Long productId, String carId);
}
