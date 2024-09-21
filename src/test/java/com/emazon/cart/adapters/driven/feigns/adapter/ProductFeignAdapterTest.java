package com.emazon.cart.adapters.driven.feigns.adapter;

import com.emazon.cart.adapters.driven.feigns.client.ProductFeign;
import com.emazon.cart.adapters.driven.feigns.dto.response.ProductResponse;
import com.emazon.cart.adapters.driven.feigns.mapper.response.ProductResponseMapper;
import com.emazon.cart.domain.exceptions.EntityNotFoundException;
import com.emazon.cart.domain.model.Product;
import feign.FeignException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class ProductFeignAdapterTest {

    @Mock
    private ProductFeign productFeign;

    @Mock
    private ProductResponseMapper productResponseMapper;

    @InjectMocks
    private ProductFeignAdapter productFeignAdapter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getProduct() {
        Long id = 1L;
        Product mockProduct = new Product(id, "name", "description", "brand", 1L, List.of("category"));
        ProductResponse mockResponse = new ProductResponse(id, "name", "description", "brand", 1L, List.of("category"));
        when(productResponseMapper.toDomain(mockResponse)).thenReturn(mockProduct);
        when(productFeign.getProductById(id)).thenReturn(mockResponse);
        Product result = productFeignAdapter.getProduct(id);
        verify(productFeign).getProductById(id);
        assertEquals(mockProduct, result);
    }

    @Test
    void getProduct_NotFound() {
        Long id = 1L;
        when(productFeign.getProductById(id)).thenThrow(new FeignException.NotFound(any(), any(), any(), any()));
        assertThrows(EntityNotFoundException.class, () -> productFeignAdapter.getProduct(id));
    }

    @Test
    void getProductsById() {
        Long id = 1L;
        List<Long> ids = List.of(id);
        List<ProductResponse> productResponses = List.of(
                new ProductResponse(id, "name", "description", "brand", 1L, List.of("category"))
        );
        List<Product> products = List.of(
                new Product(id, "name", "description", "brand", 1L, List.of("category"))
        );
        when(productResponseMapper.toDomains(productResponses)).thenReturn(products);
        when(productFeign.getProductsByIds(ids)).thenReturn(productResponses);
        List<Product> returnedProducts = productFeignAdapter.getProductsById(ids);
        verify(productFeign).getProductsByIds(ids);
        assertEquals(products, returnedProducts);
    }
}