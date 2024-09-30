package com.emazon.cart.adapters.driven.feigns.dto.request.sale;

import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SaleItemRequest {
    Long productId;
    Integer quantity;
}
