package com.emazon.car.adapters.driving.rest.service.impl;

import com.emazon.car.adapters.driving.rest.dto.request.ItemRequest;
import com.emazon.car.adapters.driving.rest.dto.response.CarResponse;
import com.emazon.car.adapters.driving.rest.mapper.request.ItemRequestMapper;
import com.emazon.car.adapters.driving.rest.mapper.response.CarResponseMapper;
import com.emazon.car.adapters.driving.rest.service.CarService;
import com.emazon.car.domain.api.CarServicePort;
import com.emazon.car.domain.model.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CarServiceImpl implements CarService {
    private final CarServicePort carServicePort;
    private final CarResponseMapper carResponseMapper;
    private final ItemRequestMapper itemRequestMapper;

    @Override
    public CarResponse addItem(String userId, ItemRequest itemRequest) {
        Item item = itemRequestMapper.toDomain(itemRequest);
        return carResponseMapper.toResponse(
                carServicePort.addItem(userId, item)
        );
    }
}
