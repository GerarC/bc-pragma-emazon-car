package com.emazon.car.adapters.driving.rest.dto.request;

import com.emazon.car.domain.utils.DomainConstants;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
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
    @Positive(message = DomainConstants.QUANTITY_MUST_BE_POSITIVE)
    private Integer quantity;
}
