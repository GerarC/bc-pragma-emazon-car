package com.emazon.car.adapters.driving.rest.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ItemRequest {
    @NotNull
    private Long productId;
    @NotNull
    private Integer quantity;
}
