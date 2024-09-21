package com.emazon.cart.adapters.driving.rest.dto.response;


import lombok.*;

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
    List<T> content;
}
