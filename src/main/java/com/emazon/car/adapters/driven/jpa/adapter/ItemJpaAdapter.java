package com.emazon.car.adapters.driven.jpa.adapter;

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

    @Override
    public void deleteById(Long id) {
        if(itemRepository.existsById(id)) throw new EntityNotFoundException(Item.class.getSimpleName(), id.toString());
        itemRepository.deleteById(id);
    }
}
