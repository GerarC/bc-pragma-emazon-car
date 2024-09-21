package com.emazon.cart.adapters.driving.rest.dto.response;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CarResponse {
    private String userId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private List<ItemResponse> items;
}
