package com.emazon.cart.domain.utils.pagination;


import java.math.BigDecimal;
import java.util.List;

public class CartPage<T> {
    // Attributes
    private Integer page;
    private Integer pageSize;
    private Integer totalPages;
    private Integer count;
    private Long totalCount;
    private BigDecimal totalPrice;
    private List<T> content;

    public CartPage(Integer page, Integer pageSize, Integer totalPages, Integer count, Long totalCount, BigDecimal totalPrice, List<T> content) {
        this.page = page;
        this.pageSize = pageSize;
        this.totalPages = totalPages;
        this.count = count;
        this.totalCount = totalCount;
        this.totalPrice = totalPrice;
        this.content = content;
    }

    public CartPage() {
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Long totalCount) {
        this.totalCount = totalCount;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public List<T> getContent() {
        return content;
    }

    public void setContent(List<T> content) {
        this.content = content;
    }
}
