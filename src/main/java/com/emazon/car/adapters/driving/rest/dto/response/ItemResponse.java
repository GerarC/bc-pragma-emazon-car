package com.emazon.car.adapters.driving.rest.dto.response;

import lombok.*;

@Data
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ItemResponse {
    private Long id;
    private Long productId;
    private Integer quantity;
}
