package com.emazon.cart.adapters.driving.rest.v1.dto.request.filter;

import lombok.*;

@Data
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ItemFilterRequest {
    private String name;
    private String category;
    private String brand;
}
