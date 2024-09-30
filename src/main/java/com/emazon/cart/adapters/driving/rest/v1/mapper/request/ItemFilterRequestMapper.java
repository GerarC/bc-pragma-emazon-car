package com.emazon.cart.adapters.driving.rest.v1.mapper.request;

import com.emazon.cart.adapters.driving.rest.v1.dto.request.filter.ItemFilterRequest;
import com.emazon.cart.domain.utils.filter.ItemFilter;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ItemFilterRequestMapper {
    ItemFilter toDomain(ItemFilterRequest itemRequest);
}
