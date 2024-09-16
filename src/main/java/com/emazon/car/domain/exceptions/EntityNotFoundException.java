package com.emazon.car.domain.exceptions;

import com.emazon.car.domain.utils.DomainConstants;

public class EntityNotFoundException extends RuntimeException {
    public EntityNotFoundException(String message) {
        super(message);
    }

    public EntityNotFoundException(String className, String id) {
        super(String.format(DomainConstants.ENTITY_NOT_FOUND_MESSAGE, className, id));
    }
}
