package com.emazon.cart.domain.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Cart {
    private String id;
    private String userId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private List<Item> items;

    public Cart(String id, String userId, LocalDateTime createdAt, LocalDateTime updatedAt, List<Item> items) {
        this.id = id;
        this.userId = userId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.items = items == null ? new ArrayList<>() : items;
    }

    public Cart() {
        this.items = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public List<Item> getItems() {
        return this.items;
    }

    public void setItems(List<Item> items) {
        this.items = items == null ? new ArrayList<>() : items;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void addItem(Item item) {
        this.items.add(item);
    }
}