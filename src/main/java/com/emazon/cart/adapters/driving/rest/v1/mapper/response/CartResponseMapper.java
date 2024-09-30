package com.emazon.cart.adapters.driving.rest.v1.mapper.response;

import com.emazon.cart.adapters.driving.rest.v1.dto.response.CarResponse;
import com.emazon.cart.domain.model.Cart;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CartResponseMapper {
    CarResponse toResponse(Cart cart);
}
