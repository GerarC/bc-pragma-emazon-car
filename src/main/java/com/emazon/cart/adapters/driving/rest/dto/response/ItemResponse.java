package com.emazon.cart.adapters.driving.rest.dto.response;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ItemResponse {
    private Long productId;
    private String name;
    private BigDecimal price;
    private Integer quantity;
    private Boolean hasStock;
    private LocalDateTime nextSupplyDate;
}
