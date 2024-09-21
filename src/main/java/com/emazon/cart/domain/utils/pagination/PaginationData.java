package com.emazon.cart.domain.utils.pagination;

public class PaginationData {
    private String sortBy;
    private Integer page;
    private Boolean asc;
    private Integer pageSize;

    public PaginationData(String sortBy, Integer page, Boolean asc, Integer pageSize) {
        this.sortBy = sortBy;
        this.page = page;
        this.asc = asc;
        this.pageSize = pageSize;
    }

    public PaginationData() {
    }

    public String getSortBy() {
        return sortBy;
    }

    public void setSortBy(String sortBy) {
        this.sortBy = sortBy;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Boolean getAsc() {
        return asc;
    }

    public void setAsc(Boolean asc) {
        this.asc = asc;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
