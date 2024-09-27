package com.emazon.cart.domain.api;

import com.emazon.cart.domain.model.Cart;
import com.emazon.cart.domain.model.Item;
import com.emazon.cart.domain.utils.filter.ItemFilter;
import com.emazon.cart.domain.utils.pagination.CartPage;
import com.emazon.cart.domain.utils.pagination.PaginationData;

import java.util.List;

public interface CartServicePort {
    Cart addItem(Item item);
    Cart removeItem(Long itemId);
    CartPage<Item> getItems(ItemFilter filter, PaginationData data);
    List<Item> buy();
}