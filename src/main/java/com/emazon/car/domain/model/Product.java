package com.emazon.car.domain.model;

import java.util.List;

public class Product {
    private Long id;
    private String name;
    private Integer quantity;
    private List<Category> categories;

    public Product(Long id, String name, Integer quantity, List<Category> categories) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.categories = categories;
    }

    public Product() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
