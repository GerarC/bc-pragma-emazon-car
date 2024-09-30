package com.emazon.cart.adapters.driven.feigns.adapter;

import com.emazon.cart.adapters.driven.feigns.client.SaleFeign;
import com.emazon.cart.adapters.driven.feigns.dto.request.sale.SaleItemRequest;
import com.emazon.cart.adapters.driven.feigns.dto.request.sale.SaleItemsRequest;
import com.emazon.cart.adapters.driven.feigns.mapper.request.ItemSaleRequestMapper;
import com.emazon.cart.domain.model.Item;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;
import feign.FeignException;
import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

class SaleFeignAdapterTest {

    @Mock
    private SaleFeign saleFeign;

    @Mock
    private ItemSaleRequestMapper itemSaleRequestMapper;

    @InjectMocks
    private SaleFeignAdapter saleFeignAdapter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void performSale_Success() {
        // Arrange
        List<Item> items = List.of(new Item(
                1L,
                1001L,
                "Product A",
                new BigDecimal("29.99"),
                10,
                null,  // Assuming Cart is null for this test
                List.of("Category1", "Category2"),
                "BrandA",
                true,
                100L,
                LocalDateTime.now().plusDays(7)
        ));
        List<SaleItemRequest> saleItemRequests = List.of(new SaleItemRequest(1001L, 10));

        when(itemSaleRequestMapper.toRequests(items)).thenReturn(saleItemRequests);
        doNothing().when(saleFeign).saveSales(any(SaleItemsRequest.class));

        // Act
        boolean result = saleFeignAdapter.performSale(items);

        // Assert
        assertTrue(result);
        verify(saleFeign, times(1)).saveSales(any(SaleItemsRequest.class));
    }

    @Test
    void performSale_Failure() {
        // Arrange
        List<Item> items = List.of(new Item(
                1L,
                1001L,
                "Product A",
                new BigDecimal("29.99"),
                10,
                null,  // Assuming Cart is null for this test
                List.of("Category1", "Category2"),
                "BrandA",
                true,
                100L,
                LocalDateTime.now().plusDays(7)
        ));
        List<SaleItemRequest> saleItemRequests = List.of(new SaleItemRequest(1001L, 10));

        when(itemSaleRequestMapper.toRequests(items)).thenReturn(saleItemRequests);
        doThrow(FeignException.class).when(saleFeign).saveSales(any(SaleItemsRequest.class));

        // Act
        boolean result = saleFeignAdapter.performSale(items);

        // Assert
        assertFalse(result);
        verify(saleFeign, times(1)).saveSales(any(SaleItemsRequest.class));
    }
}