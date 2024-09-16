package com.emazon.car.domain.spi;

import com.emazon.car.domain.model.Product;

import java.util.List;

public interface ProductPersistencePort {
    Product getProduct(Long id);
    List<Product> getProductsById(List<Long> ids);
}
