package com.emazon.cart.domain.model;

public class Item {
    private Long id;
    private Long productId;
    private Integer quantity;
    private Cart cart;

    public Item(Long id, Long productId, Integer quantity, Cart cart) {
        this.id = id;
        this.productId = productId;
        this.quantity = quantity;
        this.cart = cart;
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
}
