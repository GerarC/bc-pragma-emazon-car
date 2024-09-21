package com.emazon.cart.adapters.driven.feigns.mapper.request;

import com.emazon.cart.adapters.driven.feigns.dto.request.ProductQuery;
import com.emazon.cart.domain.utils.filter.ItemFilter;
import com.emazon.cart.domain.utils.pagination.PaginationData;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProductQueryMapper {
    default ProductQuery toRequest(ItemFilter filter, PaginationData data){
        ProductQuery.ProductQueryBuilder builder = ProductQuery.builder();
        if(filter != null) builder
                .ids(filter.getIds())
                .name(filter.getName())
                .category(filter.getCategory())
                .brand(filter.getBrand());
        if(data != null) builder
                .sortBy(data.getSortBy())
                .page(data.getPage())
                .asc(data.getAsc())
                .pageSize(data.getPageSize());
        return builder.build();
    }
}
