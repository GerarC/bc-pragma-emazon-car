package com.emazon.cart.adapters.driving.rest.dto.response;

import lombok.*;

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
    private Integer quantity;
    private List<String> categories;
    private String brand;
}
