package com.emazon.cart.domain.spi;

import com.emazon.cart.domain.model.Product;
import com.emazon.cart.domain.utils.filter.ItemFilter;
import com.emazon.cart.domain.utils.pagination.CartPage;
import com.emazon.cart.domain.utils.pagination.PaginationData;

import java.util.List;

public interface ProductPersistencePort {
    Product getProduct(Long id);
    List<Product> getProductsById(List<Long> ids);
    CartPage<Product> getAllProducts(ItemFilter itemFilter, PaginationData data);
}
