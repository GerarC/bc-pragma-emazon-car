package com.emazon.cart.domain.utils.filter;

import java.util.List;

public class ItemFilter {
    private List<Long> ids;
    private String name;
    private String category;
    private String brand;

    public ItemFilter(List<Long> ids, String name, String category, String brand) {
        this.ids = ids;
        this.name = name;
        this.category = category;
        this.brand = brand;
    }

    public ItemFilter() {
    }

    public List<Long> getIds() {
        return ids;
    }

    public void setIds(List<Long> ids) {
        this.ids = ids;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }
}
