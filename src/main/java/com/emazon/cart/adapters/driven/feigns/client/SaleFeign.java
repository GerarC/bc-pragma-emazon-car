package com.emazon.cart.adapters.driven.feigns.client;

import com.emazon.cart.adapters.driven.feigns.dto.request.sale.SaleItemsRequest;
import com.emazon.cart.configuration.feign.FeignClientConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "SALE-MICROSERVICE", url = "${emazon.transaction.base-url}" + "/v1/sales", configuration = FeignClientConfiguration.class)
public interface SaleFeign {
    @PostMapping
    void saveSales(@RequestBody SaleItemsRequest request);
}
