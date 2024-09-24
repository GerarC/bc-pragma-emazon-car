package com.emazon.cart.adapters.driven.feigns.mapper.response;

import com.emazon.cart.adapters.driven.feigns.dto.response.FeignPageResponse;
import com.emazon.cart.adapters.driven.feigns.dto.response.ProductResponse;
import com.emazon.cart.domain.model.Product;
import com.emazon.cart.domain.utils.pagination.CartPage;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProductResponseMapper {
    Product toDomain(ProductResponse productResponse);
    List<Product> toDomains(List<ProductResponse> products);
    CartPage<Product> toDomainPage(FeignPageResponse<ProductResponse> feignPageResponse);
}
