package com.emazon.cart.configuration.feign;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.QueryMapEncoder;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class FeignQueryBuilder implements QueryMapEncoder {
    @Override
    public Map<String, Object> encode(Object o) {
        ObjectMapper objectMapper = new ObjectMapper();

        Map<String, Object> map = objectMapper.convertValue(o, new TypeReference<Map<String, Object>>() {
        });
        return map;
    }
}
