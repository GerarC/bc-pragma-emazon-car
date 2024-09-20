package com.emazon.cart.adapters.driven.feigns.adapter;

import com.emazon.cart.adapters.driven.feigns.client.UserFeign;
import com.emazon.cart.domain.spi.UserPersistencePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserFeignAdapter implements UserPersistencePort {
    private final UserFeign userFeign;

    @Override
    public boolean existsUserById(String id) {
        return userFeign.existUser(id).isExists();
    }
}
