package com.emazon.cart.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class SaleReport {
    private LocalDateTime saleDate;
    private BigDecimal totalPrice;
    private Integer productCount;
    private List<Item> items;

    public SaleReport(LocalDateTime saleDate, BigDecimal totalPrice, Integer productCount, List<Item> items) {
        this.saleDate = saleDate;
        this.totalPrice = totalPrice;
        this.productCount = productCount;
        this.items = items;
    }

    public LocalDateTime getSaleDate() {
        return saleDate;
    }

    public SaleReport saleDate(LocalDateTime saleDate) {
        this.saleDate = saleDate;
        return this;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public SaleReport totalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
        return this;
    }

    public Integer getProductCount() {
        return productCount;
    }

    public SaleReport productCount(Integer productCount) {
        this.productCount = productCount;
        return this;
    }

    public List<Item> getItems() {
        return items;
    }

    public SaleReport items(List<Item> items) {
        this.items = items;
        return this;
    }
}
