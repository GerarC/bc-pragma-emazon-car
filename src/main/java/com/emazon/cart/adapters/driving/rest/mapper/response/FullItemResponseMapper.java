package com.emazon.cart.adapters.driving.rest.mapper.response;

import com.emazon.cart.adapters.driving.rest.dto.response.CarResponse;
import com.emazon.cart.adapters.driving.rest.dto.response.FullItemResponse;
import com.emazon.cart.adapters.driving.rest.dto.response.PageResponse;
import com.emazon.cart.domain.model.Cart;
import com.emazon.cart.domain.model.Item;
import com.emazon.cart.domain.utils.pagination.DomainPage;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface FullItemResponseMapper {
    FullItemResponse toResponse(Item item);
    List<FullItemResponse> toResponses(List<Item> items);
    PageResponse<FullItemResponse> toPageResponse(DomainPage<Item> domainPage);
}
