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
public interface CartEntityMapper {
    @Mapping(target = "cart", ignore = true)
    Item toDomainItem(ItemEntity entity);
    Cart toDomain(CartEntity cartEntity);
    List<Cart> toDomains(List<CartEntity> cartEntityList);

    @Mapping(target = "items", ignore = true)
    CartEntity toEntity(Cart cart);
    List<CartEntity> toEntities(List<Cart> carts);
}
