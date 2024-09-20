package com.emazon.cart.adapters.driving.rest.dto.response;

import lombok.*;

@Data
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ItemResponse {
    private Long productId;
    private Integer quantity;
}
