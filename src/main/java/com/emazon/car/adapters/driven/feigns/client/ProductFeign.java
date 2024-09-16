package com.emazon.car.adapters.driven.feigns.client;

import com.emazon.car.adapters.driven.feigns.dto.response.ProductResponse;
import com.emazon.car.configuration.feign.FeignClientConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "STOCK-MICROSERVICE", url = "${emazon.stock.base-url}" + "/product", configuration = FeignClientConfiguration.class)
public interface ProductFeign {
    @GetMapping("/{id}")
    ProductResponse getProductById(@PathVariable Long id);

    @GetMapping ("/get-by-ids")
    List<ProductResponse> getProductsByIds(List<Long> ids);
}
