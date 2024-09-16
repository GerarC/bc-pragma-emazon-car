package com.emazon.car.domain.utils;

public class TokenContainer {
    private TokenContainer() {
        throw new UnsupportedOperationException("TokenContainer is a utility class and cannot be instantiated");
    }

    private static String token;

    public static String getToken() {
        return token;
    }

    public static void setToken(String token) {
        TokenContainer.token = token;
    }
}
