package com.emazon.cart.adapters.driven.feigns.dto.response;


import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class FeignPageResponse<T> {
    Integer page;
    Integer pageSize;
    Integer totalPages;
    Integer count;
    Long totalCount;
    List<T> content;
}
