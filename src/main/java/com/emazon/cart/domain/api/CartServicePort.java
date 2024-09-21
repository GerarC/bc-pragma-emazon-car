package com.emazon.cart.domain.api;

import com.emazon.cart.domain.model.Cart;
import com.emazon.cart.domain.model.Item;
import com.emazon.cart.domain.utils.filter.ItemFilter;
import com.emazon.cart.domain.utils.pagination.DomainPage;
import com.emazon.cart.domain.utils.pagination.PaginationData;

import java.util.List;

public interface CartServicePort {
    Cart addItem(String userId, Item item);
    Cart removeItem(String userId, Long itemId);
    DomainPage<Item> getItems(String userId, ItemFilter filter, PaginationData data);
}