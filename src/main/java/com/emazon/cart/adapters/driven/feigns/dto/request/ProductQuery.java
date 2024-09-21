package com.emazon.cart.adapters.driven.feigns.dto.request;

import feign.Param;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductQuery {
    // Filtering
    @Param("ids")
    private List<Long> ids;
    @Param("name")
    private String name;
    @Param("category")
    private String category;
    @Param("brand")
    private String brand;

    // Pagination
    @Param("sortBy")
    private String sortBy;
    @Param("page")
    private Integer page;
    @Param("asc")
    private Boolean asc;
    @Param("pageSize")
    private Integer pageSize;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (ids != null && !ids.isEmpty()) sb.append("ids=").append(ids);
        if (name != null && !name.isEmpty()) sb.append("&name=").append(name);
        if (category != null && !category.isEmpty()) sb.append("&category=").append(category);
        if (brand != null && !brand.isEmpty()) sb.append("&brand=").append(brand);

        if (sortBy != null && !sortBy.isEmpty()) sb.append("&sortBy=").append(sortBy);
        if (page != null) sb.append("&page=").append(page);
        if (asc != null) sb.append("&asc=").append(asc);
        if (pageSize != null) sb.append("&pageSize=").append(pageSize);

        return sb.toString();
    }
}
