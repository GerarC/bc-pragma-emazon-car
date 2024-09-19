package com.emazon.car.adapters.driven.jpa.adapter;

import com.emazon.car.adapters.driven.jpa.entity.CarEntity;
import com.emazon.car.adapters.driven.jpa.mapper.CarEntityMapper;
import com.emazon.car.adapters.driven.jpa.persistence.CarRepository;
import com.emazon.car.adapters.driven.jpa.persistence.ItemRepository;
import com.emazon.car.domain.exceptions.EntityNotFoundException;
import com.emazon.car.domain.model.Car;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class CarJpaAdapterTest {

    @Mock
    private CarRepository carRepository;

    @Mock
    private CarEntityMapper carEntityMapper;

    @Mock
    private ItemRepository itemRepository;

    @InjectMocks
    private CarJpaAdapter carJpaAdapter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createCar() {
        String id = "carId";
        String userId = "userId";
        CarEntity carEntity = new CarEntity(id, userId, LocalDateTime.now(), LocalDateTime.now(), List.of());
        Car car = new Car(id, userId, LocalDateTime.now(), LocalDateTime.now(), List.of());
        when(carEntityMapper.toDomain(any())).thenReturn(car);
        when(carRepository.save(any())).thenReturn(carEntity);
        when(carEntityMapper.toEntity(any())).thenReturn(carEntity);
        Car carCreated = carJpaAdapter.createCar(car);
        verify(carRepository).save(any());
        assertNotNull(carCreated);
        assertEquals(car, carCreated);
    }

    @Test
    void getCarById() {
        String id = "carId";
        String userId = "userId";
        CarEntity carEntity = new CarEntity(id, userId, LocalDateTime.now(), LocalDateTime.now(), List.of());
        Car car = new Car(id, userId, LocalDateTime.now(), LocalDateTime.now(), List.of());
        when(carEntityMapper.toDomain(any())).thenReturn(car);
        when(carRepository.findById(id)).thenReturn(Optional.of(carEntity));
        Car carCreated = carJpaAdapter.getCarById(id);
        verify(carRepository).findById(any());
        assertNotNull(carCreated);
        assertEquals(car, carCreated);

    }

    @Test
    void getCarById_NotFoundException() {
        String id = "carId";
        when(carRepository.findById(id)).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> carJpaAdapter.getCarById(id));
    }

    @Test
    void getCarByUserId() {
        String id = "carId";
        String userId = "userId";
        CarEntity carEntity = new CarEntity(id, userId, LocalDateTime.now(), LocalDateTime.now(), List.of());
        Car car = new Car(id, userId, LocalDateTime.now(), LocalDateTime.now(), List.of());
        when(carEntityMapper.toDomain(any())).thenReturn(car);
        when(carRepository.findByUserId(userId)).thenReturn(Optional.of(carEntity));
        Car carCreated = carJpaAdapter.getCarByUserId(userId);
        verify(carRepository).findByUserId(any());
        assertNotNull(carCreated);
        assertEquals(car, carCreated);
    }

    @Test
    void getCarByUserId_NotFoundException() {
        String userId = "userId";
        when(carRepository.findByUserId(userId)).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> carJpaAdapter.getCarByUserId(userId));
    }

    @Test
    void updateCar() {
        String id = "carId";
        String userId = "userId";
        CarEntity carEntity = new CarEntity(id, userId, LocalDateTime.now(), LocalDateTime.now(), List.of());
        Car car = new Car(id, userId, LocalDateTime.now(), LocalDateTime.now(), List.of());
        when(carEntityMapper.toDomain(any())).thenReturn(car);
        when(carRepository.save(any())).thenReturn(carEntity);
        when(carEntityMapper.toEntity(any())).thenReturn(carEntity);
        when(itemRepository.findAllByCar_Id(any())).thenReturn(List.of());
        Car carUpdated = carJpaAdapter.updateCar(car);
        verify(carRepository).save(any());
        assertNotNull(carUpdated);
        assertEquals(car, carUpdated);
    }
}