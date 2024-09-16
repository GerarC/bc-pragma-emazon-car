package com.emazon.car.adapters.driven.feigns.adapter;

import com.emazon.car.adapters.driven.feigns.client.UserFeign;
import com.emazon.car.domain.spi.UserPersistencePort;
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
