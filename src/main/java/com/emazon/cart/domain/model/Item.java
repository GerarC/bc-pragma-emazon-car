package com.emazon.cart.domain.model;

import java.util.List;

public class Item {
    private Long id;
    private Long productId;
    private String name;
    private Integer quantity;
    private Cart cart;
    private List<String> categories;
    private String brand;

    public Item(Long id, Long productId, String name, Integer quantity, Cart cart, List<String> categories, String brand) {
        this.id = id;
        this.productId = productId;
        this.name = name;
        this.quantity = quantity;
        this.cart = cart;
        this.categories = categories;
        this.brand = brand;
    }

    public Item() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }
}
