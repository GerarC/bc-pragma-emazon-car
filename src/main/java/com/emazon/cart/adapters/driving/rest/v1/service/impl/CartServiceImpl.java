package com.emazon.cart.adapters.driving.rest.v1.service.impl;

import com.emazon.cart.adapters.driving.rest.v1.dto.request.ItemRequest;
import com.emazon.cart.adapters.driving.rest.v1.dto.request.PageQuery;
import com.emazon.cart.adapters.driving.rest.v1.dto.request.filter.ItemFilterRequest;
import com.emazon.cart.adapters.driving.rest.v1.dto.response.CarResponse;
import com.emazon.cart.adapters.driving.rest.v1.dto.response.FullItemResponse;
import com.emazon.cart.adapters.driving.rest.v1.dto.response.PageResponse;
import com.emazon.cart.adapters.driving.rest.v1.dto.response.PurchaseProcessResponse;
import com.emazon.cart.adapters.driving.rest.v1.mapper.request.ItemFilterRequestMapper;
import com.emazon.cart.adapters.driving.rest.v1.mapper.request.ItemRequestMapper;
import com.emazon.cart.adapters.driving.rest.v1.mapper.request.PageQueryMapper;
import com.emazon.cart.adapters.driving.rest.v1.mapper.response.CartResponseMapper;
import com.emazon.cart.adapters.driving.rest.v1.mapper.response.FullItemResponseMapper;
import com.emazon.cart.adapters.driving.rest.v1.service.CartService;
import com.emazon.cart.domain.api.CartServicePort;
import com.emazon.cart.domain.model.Item;
import com.emazon.cart.domain.utils.DomainConstants;
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
    public CarResponse addItem(ItemRequest itemRequest) {
        Item item = itemRequestMapper.toDomain(itemRequest);
        return cartResponseMapper.toResponse(
                cartServicePort.addItem(item)
        );
    }

    @Override
    public CarResponse removeItem(Long productId) {
        return cartResponseMapper.toResponse(
                cartServicePort.removeItem(productId)
        );
    }

    @Override
    public PageResponse<FullItemResponse> getItems(ItemFilterRequest filter, PageQuery query) {
        ItemFilter domainFilter = itemFilterRequestMapper.toDomain(filter);
        PaginationData domainData = pageQueryMapper.toDomain(query);

        return fullItemResponseMapper.toPageResponse(
                cartServicePort.getItems(domainFilter, domainData)
        );
    }

    @Override
    public PurchaseProcessResponse purchase() {
        cartServicePort.purchase();
        return PurchaseProcessResponse.builder()
                .message(DomainConstants.SUCCESS_SALE_MESSAGE)
                .build();
    }
}
