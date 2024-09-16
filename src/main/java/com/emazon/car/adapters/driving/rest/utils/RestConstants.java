package com.emazon.car.adapters.driving.rest.utils;

public class RestConstants {
    private RestConstants() {
        throw new IllegalStateException("Utility class");
    }

    public static final String CODE_OK = "200";
    public static final String CODE_CREATED = "201";
    public static final String CODE_ACCEPTED = "202";
    public static final String CODE_CONFLICT = "409";
    public static final String CODE_BAD_REQUEST = "400";
    public static final String CODE_NOT_FOUND = "404";


    public static final String SWAGGER_VALIDATIONS_DONT_PASS = "Some of the field doesn't pass validations";

    public static final String SWAGGER_ADD_ITEM_SUMMARY = "Add an item to the user car, if car doesn't exists then creates it";
    public static final String SWAGGER_ADD_ITEM_RESPONSE = "Car with that item";
    public static final String SWAGGER_PRODUCT_NOT_FOUND = "Product with that id doesn't exist";
    public static final String SWAGGER_USER_NOT_FOUND = "User with that id doesn't exist";
}
