package com.emazon.car.adapters.driven.jpa.adapter;

import com.emazon.car.adapters.driven.jpa.entity.ItemEntity;
import com.emazon.car.adapters.driven.jpa.mapper.ItemEntityMapper;
import com.emazon.car.adapters.driven.jpa.persistence.ItemRepository;
import com.emazon.car.domain.exceptions.EntityNotFoundException;
import com.emazon.car.domain.model.Item;
import com.emazon.car.domain.spi.ItemPersistencePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ItemJpaAdapter implements ItemPersistencePort {
    private final ItemRepository itemRepository;
    private final ItemEntityMapper itemEntityMapper;


    @Override
    public void deleteById(Long id) {
        if (itemRepository.existsById(id)) throw new EntityNotFoundException(Item.class.getSimpleName(), id.toString());
        itemRepository.deleteById(id);
    }

    @Override
    public boolean existsByProductIdAndCarId(Long productId, String carId) {
        return itemRepository.existsByProductIdAndCarId(productId, carId);
    }

    @Override
    public Item save(Item item) {
        ItemEntity entity = itemRepository.save(
                itemEntityMapper.toEntity(item)
        );
        return itemEntityMapper.toDomain(entity);
    }
}
