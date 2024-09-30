package com.emazon.cart.adapters.driving.rest.v1.mapper.request;

import com.emazon.cart.adapters.driving.rest.v1.dto.request.ItemRequest;
import com.emazon.cart.domain.model.Item;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ItemRequestMapper {
    Item toDomain(ItemRequest itemRequest);
}
