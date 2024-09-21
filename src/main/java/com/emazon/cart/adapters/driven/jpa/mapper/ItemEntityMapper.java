package com.emazon.cart.adapters.driven.jpa.mapper;

import com.emazon.cart.adapters.driven.jpa.entity.CartEntity;
import com.emazon.cart.adapters.driven.jpa.entity.ItemEntity;
import com.emazon.cart.domain.model.Cart;
import com.emazon.cart.domain.model.Item;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ItemEntityMapper {
    @Mapping(target = "cart", ignore = true)
    Item toDomain(ItemEntity itemEntity);
    List<Item> toDomains(List<ItemEntity> itemEntities);

    @Mapping(target = "items", ignore = true)
    CartEntity toEntity(Cart cart);

    ItemEntity toEntity(Item item);
    List<ItemEntity> toEntities(List<Item> items);
}
