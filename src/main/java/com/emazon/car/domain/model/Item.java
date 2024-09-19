package com.emazon.car.domain.model;

public class Item {
    private Long id;
    private Long productId;
    private Integer quantity;
    private Car car;

    public Item(Long id, Long productId, Integer quantity, Car car) {
        this.id = id;
        this.productId = productId;
        this.quantity = quantity;
        this.car = car;
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

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }
}
