package com.emazon.cart.adapters.driven.feigns.client;

import com.emazon.cart.adapters.driven.feigns.dto.request.report.SaleReportRequest;
import com.emazon.cart.adapters.driven.feigns.dto.request.sale.SaleItemsRequest;
import com.emazon.cart.configuration.feign.FeignClientConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "SALE-REPORT", url = "${emazon.report.base-url}" + "/v1/sale-reports", configuration = FeignClientConfiguration.class)
public interface SaleReportFeign {
    @PostMapping
    void createSaleReport(@RequestBody SaleReportRequest saleReportRequest);
}
