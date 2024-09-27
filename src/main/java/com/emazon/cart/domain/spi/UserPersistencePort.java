package com.emazon.cart.domain.spi;

public interface UserPersistencePort {
    boolean existsUserById(String id);
    String getIdFromCurrentUser();
}
