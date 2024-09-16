package com.emazon.car.adapters.driving.rest.mapper.request;

import com.emazon.car.adapters.driving.rest.dto.request.ItemRequest;
import com.emazon.car.domain.model.Item;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ItemRequestMapper {
    Item toDomain(ItemRequest itemRequest);
}
