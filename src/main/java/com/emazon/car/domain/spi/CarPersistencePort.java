package com.emazon.car.domain.spi;

import com.emazon.car.domain.model.Car;

public interface CarPersistencePort {
    Car createCar(Car car);
    Car getCarById(String id);
    Car getCarByUserId(String userId);
    Car updateCar(Car car);
}
