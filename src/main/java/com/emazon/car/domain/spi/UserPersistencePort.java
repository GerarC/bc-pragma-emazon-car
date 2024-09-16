package com.emazon.car.domain.spi;

public interface UserPersistencePort {
    boolean existsUserById(String id);
}
