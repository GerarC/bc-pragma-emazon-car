package com.emazon.car.adapters.driving.rest.service;

import com.emazon.car.adapters.driving.rest.dto.request.ItemRequest;
import com.emazon.car.adapters.driving.rest.dto.response.CarResponse;

public interface CarService {
    CarResponse addItem(String userId, ItemRequest itemRequest);
}
