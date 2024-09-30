package com.emazon.cart.adapters.driven.feigns.dto.request.sale;

import lombok.*;

import java.util.List;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SaleItemsRequest {
    private List<SaleItemRequest> items;
}
