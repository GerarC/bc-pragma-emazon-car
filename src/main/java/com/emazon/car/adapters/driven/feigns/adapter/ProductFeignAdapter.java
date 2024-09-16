package com.emazon.car.adapters.driven.feigns.adapter;

import com.emazon.car.adapters.driven.feigns.client.ProductFeign;
import com.emazon.car.adapters.driven.feigns.mapper.ProductResponseMapper;
import com.emazon.car.domain.model.Product;
import com.emazon.car.domain.spi.ProductPersistencePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ProductFeignAdapter implements ProductPersistencePort {
    private final ProductFeign productFeign;
    private final ProductResponseMapper productResponseMapper;

    @Override
    public Product getProduct(Long id) {
        return productResponseMapper.toDomain(
                productFeign.getProductById(id)
        );
    }

    @Override
    public List<Product> getProductsById(List<Long> ids) {
        return productResponseMapper.toDomains(
                productFeign.getProductsByIds(ids)
        );
    }
}
