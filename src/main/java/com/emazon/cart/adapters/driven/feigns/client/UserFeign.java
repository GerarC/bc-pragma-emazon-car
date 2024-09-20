package com.emazon.cart.adapters.driven.feigns.client;

import com.emazon.cart.adapters.driven.feigns.dto.response.ExistsUserResponse;
import com.emazon.cart.configuration.feign.FeignClientConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "USER-MICROSERVICE", url = "${emazon.user.base-url}" + "/user", configuration = FeignClientConfiguration.class)
public interface UserFeign {
    @GetMapping("/{id}/exist")
    ExistsUserResponse existUser(@PathVariable String id);
}
