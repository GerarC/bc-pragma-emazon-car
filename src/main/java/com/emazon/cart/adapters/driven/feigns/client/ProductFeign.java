package com.emazon.cart.adapters.driven.feigns.client;

import com.emazon.cart.adapters.driven.feigns.dto.request.ProductQuery;
import com.emazon.cart.adapters.driven.feigns.dto.response.FeignPageResponse;
import com.emazon.cart.adapters.driven.feigns.dto.response.ProductResponse;
import com.emazon.cart.configuration.feign.FeignClientConfiguration;
import feign.QueryMap;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "STOCK-MICROSERVICE", url = "${emazon.stock.base-url}" + "/products", configuration = FeignClientConfiguration.class)
public interface ProductFeign {
    @GetMapping("/{id}")
    ProductResponse getProductById(@PathVariable Long id);

    @GetMapping ("/get-by-ids")
    List<ProductResponse> getProductsByIds(@RequestParam List<Long> ids);

    @GetMapping
    FeignPageResponse<ProductResponse> getProducts(@SpringQueryMap @ModelAttribute ProductQuery query);
}
