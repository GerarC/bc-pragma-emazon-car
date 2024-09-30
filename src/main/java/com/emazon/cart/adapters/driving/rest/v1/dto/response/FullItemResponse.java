package com.emazon.cart.adapters.driving.rest.v1.dto.response;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FullItemResponse {
    private Long productId;
    private String name;
    private BigDecimal price;
    private Integer quantity;
    private Long stockQuantity;
    private String brand;
    private List<String> categories;
    private Boolean hasStock;
    private LocalDateTime nextSupplyDate;
}
