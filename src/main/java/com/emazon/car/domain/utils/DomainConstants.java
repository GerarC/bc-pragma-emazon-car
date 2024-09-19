package com.emazon.car.domain.utils;

public class DomainConstants {
    private DomainConstants() {
        throw new IllegalStateException("Utility class");
    }

    public static final String USER_ENTITY_NAME = "User";
    public static final Integer MAX_CATEGORY_ITEMS = 3;

    public static final String ENTITY_NOT_FOUND_MESSAGE = "Entity of type '%s' with id '%s' not found";
    public static final String CATEGORY_OUT_OF_BOUNDS_MESSAGE = "Category '%s' cannot have more than %s items";
    public static final String NOT_ENOUGH_PRODUCT_STOCK_MESSAGE = "Not enough product stock of '%s'";
    public static final String ITEM_ALREADY_ADDED_MESSAGE = "Item already added to the car '%s'";
    public static final String QUANTITY_MUST_BE_POSITIVE = "Quantity must be positive";
}
