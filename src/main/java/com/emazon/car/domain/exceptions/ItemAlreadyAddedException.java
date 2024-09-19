package com.emazon.car.domain.exceptions;

import com.emazon.car.domain.utils.DomainConstants;

public class ItemAlreadyAddedException extends RuntimeException {
    public ItemAlreadyAddedException(String carId) {
        super(String.format(DomainConstants.ITEM_ALREADY_ADDED_MESSAGE, carId));
    }
}
