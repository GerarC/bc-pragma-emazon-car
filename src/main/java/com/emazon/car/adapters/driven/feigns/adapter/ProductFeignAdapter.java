package com.emazon.car.adapters.driven.feigns.adapter;

import com.emazon.car.adapters.driven.feigns.client.ProductFeign;
import com.emazon.car.adapters.driven.feigns.mapper.ProductResponseMapper;
import com.emazon.car.domain.exceptions.EntityNotFoundException;
import com.emazon.car.domain.model.Product;
import com.emazon.car.domain.spi.ProductPersistencePort;
import feign.FeignException;
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
        try{
            return productResponseMapper.toDomain(
                    productFeign.getProductById(id)
            );
        }catch(FeignException.NotFound e) {
            throw new EntityNotFoundException(Product.class.getSimpleName(), id.toString());
        }
    }

    @Override
    public List<Product> getProductsById(List<Long> ids) {
        try {
            return productResponseMapper.toDomains(
                    productFeign.getProductsByIds(ids)
            );
        }catch(FeignException.NotFound e) {
            throw new EntityNotFoundException(Product.class.getSimpleName(), ids.toString());
        }
    }
}
