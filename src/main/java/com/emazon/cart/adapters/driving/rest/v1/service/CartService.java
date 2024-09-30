package com.emazon.cart.adapters.driving.rest.v1.service;

import com.emazon.cart.adapters.driving.rest.v1.dto.request.ItemRequest;
import com.emazon.cart.adapters.driving.rest.v1.dto.request.PageQuery;
import com.emazon.cart.adapters.driving.rest.v1.dto.request.filter.ItemFilterRequest;
import com.emazon.cart.adapters.driving.rest.v1.dto.response.CarResponse;
import com.emazon.cart.adapters.driving.rest.v1.dto.response.FullItemResponse;
import com.emazon.cart.adapters.driving.rest.v1.dto.response.PageResponse;
import com.emazon.cart.adapters.driving.rest.v1.dto.response.PurchaseProcessResponse;

public interface CartService {
    CarResponse addItem(ItemRequest itemRequest);
    CarResponse removeItem(Long productId);
    PageResponse<FullItemResponse> getItems(ItemFilterRequest filter, PageQuery query);
    PurchaseProcessResponse purchase();

}
