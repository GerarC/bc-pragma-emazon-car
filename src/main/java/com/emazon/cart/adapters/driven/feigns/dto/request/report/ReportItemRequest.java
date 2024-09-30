package com.emazon.cart.adapters.driven.feigns.dto.request.report;

import java.math.BigDecimal;

import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReportItemRequest {
    private Long productId;
    private BigDecimal price;
    private Integer quantity;
}
