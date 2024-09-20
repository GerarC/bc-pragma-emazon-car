package com.emazon.cart.domain.exceptions;

import com.emazon.cart.domain.utils.DomainConstants;

public class MaxCategoryCountException extends RuntimeException {
    public MaxCategoryCountException(String category) {
        super(String.format(DomainConstants.CATEGORY_OUT_OF_BOUNDS_MESSAGE, category, DomainConstants.MAX_CATEGORY_ITEMS));
    }
}
