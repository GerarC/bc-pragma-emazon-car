package com.emazon.cart.domain.exceptions;

import com.emazon.cart.domain.utils.DomainConstants;

public class NotEnoughProductStockException extends RuntimeException {
    public NotEnoughProductStockException(String product) {
        super(String.format(DomainConstants.NOT_ENOUGH_PRODUCT_STOCK_MESSAGE, product));
    }
}
