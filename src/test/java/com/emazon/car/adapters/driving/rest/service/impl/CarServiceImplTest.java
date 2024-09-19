package com.emazon.car.adapters.driving.rest.service.impl;

import com.emazon.car.adapters.driving.rest.dto.request.ItemRequest;
import com.emazon.car.adapters.driving.rest.dto.response.CarResponse;
import com.emazon.car.adapters.driving.rest.dto.response.ItemResponse;
import com.emazon.car.adapters.driving.rest.mapper.request.ItemRequestMapper;
import com.emazon.car.adapters.driving.rest.mapper.response.CarResponseMapper;
import com.emazon.car.domain.api.CarServicePort;
import com.emazon.car.domain.model.Car;
import com.emazon.car.domain.model.Item;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class CarServiceImplTest {

    @Mock
    private CarServicePort carServicePort;
    @Mock
    private CarResponseMapper carResponseMapper;
    @Mock
    private ItemRequestMapper itemRequestMapper;

    @InjectMocks
    private CarServiceImpl carServiceImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void addItem() {
        String carId = "carId";
        String userId = "userId";
        Item item = new Item(5L, 5L, 10, null);
        ItemRequest itemRequest = new ItemRequest(5L, 10);
        ItemResponse itemResponse = new ItemResponse(5L, 5L, 10);
        Car car = new Car(carId, userId, LocalDateTime.now(), LocalDateTime.now(), List.of(item));
        CarResponse carResponse = new CarResponse(userId, LocalDateTime.now(), LocalDateTime.now(), List.of(itemResponse));
        when(carResponseMapper.toResponse(any())).thenReturn(carResponse);
        when(itemRequestMapper.toDomain(any())).thenReturn(item);
        when(carServicePort.addItem(any(), any())).thenReturn(car);
        CarResponse returnedResponse = carServiceImpl.addItem(userId, itemRequest);
        verify(carServicePort).addItem(any(), any());
        assertNotNull(returnedResponse);
        assertEquals(carResponse, returnedResponse);
    }
}