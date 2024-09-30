package com.emazon.cart.adapters.driving.rest.v1.mapper.response;

import com.emazon.cart.adapters.driving.rest.v1.dto.response.FullItemResponse;
import com.emazon.cart.adapters.driving.rest.v1.dto.response.PageResponse;
import com.emazon.cart.domain.model.Item;
import com.emazon.cart.domain.utils.pagination.CartPage;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface FullItemResponseMapper {
    FullItemResponse toResponse(Item item);
    List<FullItemResponse> toResponses(List<Item> items);
    PageResponse<FullItemResponse> toPageResponse(CartPage<Item> cartPage);
}
