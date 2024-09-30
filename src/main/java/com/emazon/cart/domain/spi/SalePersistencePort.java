package com.emazon.cart.domain.spi;

import com.emazon.cart.domain.model.Item;

import java.util.List;

public interface SalePersistencePort {
    /**
     * It tries to make the sale transaction. If it fails,
     * then it returns a false value else it returns true instead
     *
     * @param items list of items to sell
     * @return a boolean that represents the state of the transaction
     */
    boolean performSale(List<Item> items);
}
