package com.emazon.cart.domain.spi;

import com.emazon.cart.domain.model.Product;
import com.emazon.cart.domain.utils.filter.ItemFilter;
import com.emazon.cart.domain.utils.pagination.DomainPage;
import com.emazon.cart.domain.utils.pagination.PaginationData;

import java.util.List;

public interface ProductPersistencePort {
    Product getProduct(Long id);
    List<Product> getProductsById(List<Long> ids);
    DomainPage<Product> getAllProducts(ItemFilter itemFilter, PaginationData data);
}
