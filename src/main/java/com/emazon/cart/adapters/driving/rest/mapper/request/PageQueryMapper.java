package com.emazon.cart.adapters.driving.rest.mapper.request;

import com.emazon.cart.adapters.driving.rest.dto.request.ItemRequest;
import com.emazon.cart.adapters.driving.rest.dto.request.PageQuery;
import com.emazon.cart.domain.model.Item;
import com.emazon.cart.domain.utils.pagination.PaginationData;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PageQueryMapper {
    PaginationData toDomain(PageQuery pageQuery);
}
