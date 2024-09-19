package com.emazon.car.domain.spi;

import com.emazon.car.domain.model.Item;

public interface ItemPersistencePort {
    Item save(Item item);
    void deleteById(Long id);
    boolean existsByProductIdAndCarId(Long productId, String carId);
}
