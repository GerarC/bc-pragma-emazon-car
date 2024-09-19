package com.emazon.car.domain.model;

import java.util.List;

public class Product {
    private Long id;
    private String name;
    private String description;
    private String brand;
    private Long quantity;
    private List<String> categories;

    public Product(Long id, String name, String description, String brand, Long quantity, List<String> categories) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.brand = brand;
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

    public List<String> getCategories() {
        return categories;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }

    public Long getQuantity() {
        return quantity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }
}
