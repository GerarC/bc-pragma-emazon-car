package com.emazon.cart.domain.utils;

public class DomainConstants {
    private DomainConstants() {
        throw new IllegalStateException("Utility class");
    }

    public static final String USER_ENTITY_NAME = "There isn't valid current user";
    public static final Integer MAX_CATEGORY_ITEMS = 3;

    public static final String ENTITY_NOT_FOUND_MESSAGE = "Entity of type '%s' with id '%s' not found";
    public static final String CATEGORY_OUT_OF_BOUNDS_MESSAGE = "Category '%s' cannot have more than %s items";
    public static final String NOT_ENOUGH_PRODUCT_STOCK_MESSAGE = "Not enough product stock of '%s', Next supply date is: '%tF'";
    public static final String ITEM_ALREADY_ADDED_MESSAGE = "Item already added to car of user '%s'";
    public static final String QUANTITY_MUST_BE_POSITIVE = "Quantity must be positive";
    public static final String PRODUCT_IN_CART_NOT_FOUND = "Product with id '%s' not found in cart with id '%s'";

    public static final String ITEM_PAGINATION_ERROR_MESSAGE = "There has been an error parsing from products to items in pagination process";

    public static final Integer DEFAULT_PAGE_SIZE = 10;
    public static final Integer DEFAULT_PAGE_NUMBER = 0;

}
