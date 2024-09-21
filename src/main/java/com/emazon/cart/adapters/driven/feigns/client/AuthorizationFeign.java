package com.emazon.cart.adapters.driven.feigns.client;

import com.emazon.cart.adapters.driven.feigns.dto.request.AuthorizationRequest;
import com.emazon.cart.adapters.driven.feigns.dto.response.AuthorizationResponse;
import com.emazon.cart.configuration.feign.FeignClientConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "AUTH-MICROSERVICE", url = "${emazon.user.base-url}" + "/auth", configuration = FeignClientConfiguration.class)
public interface AuthorizationFeign {
    @PostMapping(value = "/authorize", consumes = MediaType.APPLICATION_JSON_VALUE)
    AuthorizationResponse authorize(@RequestBody AuthorizationRequest authorizationRequest);
}
