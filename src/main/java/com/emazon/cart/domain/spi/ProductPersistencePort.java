package com.emazon.cart.domain.spi;

import com.emazon.cart.domain.model.Product;

import java.util.List;

public interface ProductPersistencePort {
    Product getProduct(Long id);
    List<Product> getProductsById(List<Long> ids);
}
