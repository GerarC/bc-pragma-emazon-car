package com.emazon.cart.adapters.driving.rest.service.impl;

import com.emazon.cart.adapters.driving.rest.dto.request.ItemRequest;
import com.emazon.cart.adapters.driving.rest.dto.request.PageQuery;
import com.emazon.cart.adapters.driving.rest.dto.request.filter.ItemFilterRequest;
import com.emazon.cart.adapters.driving.rest.dto.response.CarResponse;
import com.emazon.cart.adapters.driving.rest.dto.response.FullItemResponse;
import com.emazon.cart.adapters.driving.rest.dto.response.PageResponse;
import com.emazon.cart.adapters.driving.rest.mapper.request.ItemFilterRequestMapper;
import com.emazon.cart.adapters.driving.rest.mapper.request.ItemRequestMapper;
import com.emazon.cart.adapters.driving.rest.mapper.request.PageQueryMapper;
import com.emazon.cart.adapters.driving.rest.mapper.response.CartResponseMapper;
import com.emazon.cart.adapters.driving.rest.mapper.response.FullItemResponseMapper;
import com.emazon.cart.adapters.driving.rest.service.CartService;
import com.emazon.cart.domain.api.CartServicePort;
import com.emazon.cart.domain.model.Item;
import com.emazon.cart.domain.utils.filter.ItemFilter;
import com.emazon.cart.domain.utils.pagination.PaginationData;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {
    private final CartServicePort cartServicePort;
    private final CartResponseMapper cartResponseMapper;
    private final ItemRequestMapper itemRequestMapper;
    private final ItemFilterRequestMapper itemFilterRequestMapper;
    private final PageQueryMapper pageQueryMapper;
    private final FullItemResponseMapper fullItemResponseMapper;

    @Override
    public CarResponse addItem(String userId, ItemRequest itemRequest) {
        Item item = itemRequestMapper.toDomain(itemRequest);
        return cartResponseMapper.toResponse(
                cartServicePort.addItem(userId, item)
        );
    }

    @Override
    public CarResponse removeItem(String userId, Long productId) {
        return cartResponseMapper.toResponse(
                cartServicePort.removeItem(userId, productId)
        );
    }

    @Override
    public PageResponse<FullItemResponse> getItems(String userId, ItemFilterRequest filter, PageQuery query) {
        ItemFilter domainFilter = itemFilterRequestMapper.toDomain(filter);
        PaginationData domainData = pageQueryMapper.toDomain(query);

        return fullItemResponseMapper.toPageResponse(
                cartServicePort.getItems(userId, domainFilter, domainData)
        );
    }
}
