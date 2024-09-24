package com.emazon.cart.adapters.driving.rest.dto.response;


import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PageResponse<T> {
    Integer page;
    Integer pageSize;
    Integer totalPages;
    Integer count;
    Long totalCount;
    private BigDecimal totalPrice;
    List<T> content;
}
