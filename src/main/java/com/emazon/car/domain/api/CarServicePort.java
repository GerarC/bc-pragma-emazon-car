package com.emazon.car.domain.api;

import com.emazon.car.domain.model.Car;
import com.emazon.car.domain.model.Item;

public interface CarServicePort {
    Car addItem(String userId, Item item);
}