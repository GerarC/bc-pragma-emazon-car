package com.emazon.cart.adapters.driving.rest.service;

import com.emazon.cart.adapters.driving.rest.dto.request.ItemRequest;
import com.emazon.cart.adapters.driving.rest.dto.request.PageQuery;
import com.emazon.cart.adapters.driving.rest.dto.request.filter.ItemFilterRequest;
import com.emazon.cart.adapters.driving.rest.dto.response.CarResponse;
import com.emazon.cart.adapters.driving.rest.dto.response.FullItemResponse;
import com.emazon.cart.adapters.driving.rest.dto.response.PageResponse;

public interface CartService {
    CarResponse addItem(ItemRequest itemRequest);
    CarResponse removeItem(Long productId);
    PageResponse<FullItemResponse> getItems(ItemFilterRequest filter, PageQuery query);
}
