package com.emazon.cart.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class Item {
    private Long id;
    private Long productId;
    private String name;
    private BigDecimal price;
    private Integer quantity;
    private Cart cart;
    private List<String> categories;
    private String brand;
    private Boolean hasStock;
    private Long stockQuantity;
    private LocalDateTime nextSupplyDate;

    public Item(Long id, Long productId, String name, BigDecimal price, Integer quantity, Cart cart, List<String> categories, String brand, Boolean hasStock, Long stockQuantity, LocalDateTime nextSupplyDate) {
        this.id = id;
        this.productId = productId;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.cart = cart;
        this.categories = categories;
        this.brand = brand;
        this.hasStock = hasStock;
        this.stockQuantity = stockQuantity;
        this.nextSupplyDate = nextSupplyDate;
    }

    public Item() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public List<String> getCategories() {
        return categories;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public Boolean getHasStock() {
        return hasStock;
    }

    public void setHasStock(Boolean hasStock) {
        this.hasStock = hasStock;
    }

    public Long getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(Long stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    public LocalDateTime getNextSupplyDate() {
        return nextSupplyDate;
    }

    public void setNextSupplyDate(LocalDateTime nextSupplyDate) {
        this.nextSupplyDate = nextSupplyDate;
    }
}
