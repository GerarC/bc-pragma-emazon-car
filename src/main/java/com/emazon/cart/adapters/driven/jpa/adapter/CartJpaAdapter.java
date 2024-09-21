package com.emazon.cart.adapters.driven.jpa.adapter;

import com.emazon.cart.adapters.driven.jpa.entity.CartEntity;
import com.emazon.cart.adapters.driven.jpa.entity.ItemEntity;
import com.emazon.cart.adapters.driven.jpa.mapper.CartEntityMapper;
import com.emazon.cart.adapters.driven.jpa.persistence.CartRepository;
import com.emazon.cart.adapters.driven.jpa.persistence.ItemRepository;
import com.emazon.cart.domain.exceptions.EntityNotFoundException;
import com.emazon.cart.domain.model.Cart;
import com.emazon.cart.domain.spi.CartPersistencePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CartJpaAdapter implements CartPersistencePort {

    private final CartRepository cartRepository;
    private final CartEntityMapper cartEntityMapper;
    private final ItemRepository itemRepository;

    @Override
    public Cart createCar(Cart cart) {
        CartEntity cartEntity = cartEntityMapper.toEntity(cart);
        return cartEntityMapper.toDomain(
                cartRepository.save(cartEntity)
        );
    }

    @Override
    public Cart getCarById(String id) {
        return cartEntityMapper.toDomain(
                cartRepository.findById(id)
                        .orElseThrow(() -> new EntityNotFoundException(Cart.class.getSimpleName(), id))
        );
    }

    @Override
    public Cart getCarByUserId(String userId) {
        return cartEntityMapper.toDomain(
                cartRepository.findByUserId(userId)
                        .orElseThrow(() -> new EntityNotFoundException(Cart.class.getSimpleName(), userId))
        );
    }

    @Override
    public Cart updateCar(Cart cart) {
        CartEntity entity = cartEntityMapper.toEntity(cart);
        List<ItemEntity> itemEntities = itemRepository.findAllByCart_Id(cart.getId());
        entity.setItems(itemEntities);
        return cartEntityMapper.toDomain(
                cartRepository.save(entity)
        );
    }
}
