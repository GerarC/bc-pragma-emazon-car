package com.emazon.car.domain.model;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

public class Car {
    private String id;
    private String userId;
    private LocalDateTime modified;
    private List<Item> items;

    public Car(String id, String userId, LocalDateTime modified, List<Item> items) {
        this.id = id;
        this.userId = userId;
        this.modified = modified;
        this.items = items;
    }

    public Car() {
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

    public LocalDateTime getModified() {
        return modified;
    }

    public void setModified(LocalDateTime modified) {
        this.modified = modified;
    }

    public List<Item> getItems() {
        return Collections.unmodifiableList(items);
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public void addItem(Item item) {
        this.items.add(item);
    }
}