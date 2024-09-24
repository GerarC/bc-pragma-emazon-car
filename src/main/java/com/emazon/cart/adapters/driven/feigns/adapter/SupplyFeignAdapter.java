package com.emazon.cart.adapters.driven.feigns.adapter;

import com.emazon.cart.domain.spi.SupplyPersistencePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Random;

@Component
@RequiredArgsConstructor
public class SupplyFeignAdapter implements SupplyPersistencePort {

    private final Random random = new Random();

    @Override
    public LocalDateTime nextSupplyDate(Long productId) {
        return LocalDateTime.now().plusDays(random.nextInt(30));
    }
}
