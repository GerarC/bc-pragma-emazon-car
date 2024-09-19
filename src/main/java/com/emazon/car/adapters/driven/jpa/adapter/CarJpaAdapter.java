package com.emazon.car.adapters.driven.jpa.adapter;

import com.emazon.car.adapters.driven.jpa.entity.CarEntity;
import com.emazon.car.adapters.driven.jpa.entity.ItemEntity;
import com.emazon.car.adapters.driven.jpa.mapper.CarEntityMapper;
import com.emazon.car.adapters.driven.jpa.mapper.ItemEntityMapper;
import com.emazon.car.adapters.driven.jpa.persistence.CarRepository;
import com.emazon.car.adapters.driven.jpa.persistence.ItemRepository;
import com.emazon.car.domain.exceptions.EntityNotFoundException;
import com.emazon.car.domain.model.Car;
import com.emazon.car.domain.spi.CarPersistencePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CarJpaAdapter implements CarPersistencePort {

    private final CarRepository carRepository;
    private final CarEntityMapper carEntityMapper;
    private final ItemRepository itemRepository;

    @Override
    public Car createCar(Car car) {
        CarEntity carEntity = carEntityMapper.toEntity(car);
        return carEntityMapper.toDomain(
                carRepository.save(carEntity)
        );
    }

    @Override
    public Car getCarById(String id) {
        return carEntityMapper.toDomain(
                carRepository.findById(id)
                        .orElseThrow(() -> new EntityNotFoundException(Car.class.getSimpleName(), id))
        );
    }

    @Override
    public Car getCarByUserId(String userId) {
        return carEntityMapper.toDomain(
                carRepository.findByUserId(userId)
                        .orElseThrow(() -> new EntityNotFoundException(Car.class.getSimpleName(), userId))
        );
    }

    @Override
    public Car updateCar(Car car) {
        CarEntity entity = carEntityMapper.toEntity(car);
        List<ItemEntity> itemEntities = itemRepository.findAllByCar_Id(car.getId());
        entity.setItems(itemEntities);
        return carEntityMapper.toDomain(
                carRepository.save(entity)
        );
    }
}
