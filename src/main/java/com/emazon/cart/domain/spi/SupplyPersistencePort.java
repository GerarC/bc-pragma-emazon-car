package com.emazon.cart.domain.spi;

import java.time.LocalDateTime;

public interface SupplyPersistencePort {
    LocalDateTime nextSupplyDate(Long productId);
}
