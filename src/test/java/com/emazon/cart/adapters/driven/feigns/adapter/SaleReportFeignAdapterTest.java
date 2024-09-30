package com.emazon.cart.adapters.driven.feigns.adapter;

import com.emazon.cart.adapters.driven.feigns.client.SaleReportFeign;
import com.emazon.cart.adapters.driven.feigns.dto.request.report.ReportItemRequest;
import com.emazon.cart.adapters.driven.feigns.dto.request.report.SaleReportRequest;
import com.emazon.cart.adapters.driven.feigns.mapper.request.SaleReportRequestMapper;
import com.emazon.cart.domain.model.Item;
import com.emazon.cart.domain.model.SaleReport;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

class SaleReportFeignAdapterTest {

    @Mock
    private SaleReportFeign reportFeign;

    @Mock
    private SaleReportRequestMapper reportRequestMapper;

    @InjectMocks
    private SaleReportFeignAdapter saleReportFeignAdapter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void saveReport_Success() {
        // Arrange
        List<Item> items = List.of(new Item(
                1L,
                1001L,
                "Product A",
                new BigDecimal("29.99"),
                2,
                null,
                List.of("Category1", "Category2"),
                "BrandA",
                true,
                100L,
                LocalDateTime.now().plusDays(7)
        ));

        SaleReport saleReport = new SaleReport(
                LocalDateTime.now(),
                new BigDecimal("59.98"),
                2,
                items
        );

        SaleReportRequest saleReportRequest = SaleReportRequest.builder()
                .saleDate(saleReport.getSaleDate())
                .totalPrice(saleReport.getTotalPrice())
                .productCount(saleReport.getProductCount())
                .items(List.of(new ReportItemRequest(1001L,new BigDecimal("29.99"), 2)))
                .build();

        when(reportRequestMapper.toRequest(saleReport)).thenReturn(saleReportRequest);
        doNothing().when(reportFeign).createSaleReport(any(SaleReportRequest.class));

        // Act
        saleReportFeignAdapter.saveReport(saleReport);

        // Assert
        verify(reportFeign, times(1)).createSaleReport(any(SaleReportRequest.class));
    }
}

