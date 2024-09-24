package com.emazon.cart.domain.exceptions;

import com.emazon.cart.domain.utils.DomainConstants;

import java.time.LocalDateTime;

public class NotEnoughProductStockException extends RuntimeException {
    public NotEnoughProductStockException(String product, LocalDateTime localDateTime) {
        super(String.format(DomainConstants.NOT_ENOUGH_PRODUCT_STOCK_MESSAGE, product, localDateTime));
    }
}
