package com.emazon.cart.adapters.driven.feigns.dto.response;

import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponse {
    private Long id;
    private String name;
    private String description;
    private String brand;
    private BigDecimal price;
    private Long quantity;
    private List<String> categories;
}
