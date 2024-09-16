package com.emazon.car.adapters.driven.feigns.mapper;

import com.emazon.car.adapters.driven.feigns.dto.response.ProductResponse;
import com.emazon.car.domain.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProductResponseMapper {
    Product toDomain(ProductResponse productResponse);
    List<Product> toDomains(List<ProductResponse> products);
}
