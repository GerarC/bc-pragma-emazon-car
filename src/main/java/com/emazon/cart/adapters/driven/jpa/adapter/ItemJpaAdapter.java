package com.emazon.cart.adapters.driven.jpa.adapter;

import com.emazon.cart.adapters.driven.jpa.entity.ItemEntity;
import com.emazon.cart.adapters.driven.jpa.mapper.ItemEntityMapper;
import com.emazon.cart.adapters.driven.jpa.persistence.ItemRepository;
import com.emazon.cart.domain.exceptions.EntityNotFoundException;
import com.emazon.cart.domain.model.Item;
import com.emazon.cart.domain.spi.ItemPersistencePort;
import com.emazon.cart.domain.utils.DomainConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ItemJpaAdapter implements ItemPersistencePort {
    private final ItemRepository itemRepository;
    private final ItemEntityMapper itemEntityMapper;


    @Override
    public void deleteById(Long id) {
        if (!itemRepository.existsById(id)) throw new EntityNotFoundException(Item.class.getSimpleName(), id.toString());
        itemRepository.deleteById(id);
    }

    @Override
    public boolean existsByProductIdAndCarId(Long productId, String carId) {
        return itemRepository.existsByProductIdAndCartId(productId, carId);
    }

    @Override
    public Item findByProductIdAndCarId(Long productId, String carId) {
        return itemEntityMapper.toDomain(
                itemRepository
                        .findByProductIdAndCartId(productId, carId)
                        .orElseThrow(() -> new EntityNotFoundException(
                                String.format(DomainConstants.PRODUCT_IN_CART_NOT_FOUND, productId, carId)))
        );
    }

    @Override
    public Item save(Item item) {
        ItemEntity entity = itemRepository.save(
                itemEntityMapper.toEntity(item)
        );
        return itemEntityMapper.toDomain(entity);
    }
}
