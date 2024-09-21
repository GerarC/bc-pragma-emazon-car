package com.emazon.cart.adapters.driven.jpa.adapter;

import com.emazon.cart.adapters.driven.jpa.entity.ItemEntity;
import com.emazon.cart.adapters.driven.jpa.mapper.ItemEntityMapper;
import com.emazon.cart.adapters.driven.jpa.persistence.ItemRepository;
import com.emazon.cart.domain.exceptions.EntityNotFoundException;
import com.emazon.cart.domain.model.Item;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ItemJpaAdapterTest {

    @Mock
    private ItemRepository itemRepository;

    @Mock
    private ItemEntityMapper itemEntityMapper;

    @InjectMocks
    private ItemJpaAdapter itemJpaAdapter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deleteById() {
        Long id = 1L;
        when(itemRepository.existsById(id)).thenReturn(true);
        doNothing().when(itemRepository).deleteById(id);
        itemJpaAdapter.deleteById(id);
        verify(itemRepository).deleteById(id);
    }

    @Test
    void deleteById_NotFound() {
        Long id = 1L;
        when(itemRepository.existsById(id)).thenReturn(false);
        assertThrows(EntityNotFoundException.class, () -> itemJpaAdapter.deleteById(id));
    }

    @Test
    void existsByProductIdAndCarId() {
        when(itemRepository.existsByProductIdAndCartId(1L, "carId")).thenReturn(true);
        assertTrue(itemJpaAdapter.existsByProductIdAndCarId(1L, "carId"));
    }

    @Test
    void save() {
        Item item = new Item();
        ItemEntity itemEntity = new ItemEntity();
        when(itemEntityMapper.toEntity(item)).thenReturn(itemEntity);
        when(itemRepository.save(itemEntity)).thenReturn(itemEntity);
        when(itemEntityMapper.toDomain(itemEntity)).thenReturn(item);
        itemJpaAdapter.save(item);
        verify(itemRepository).save(itemEntity);
    }

    @Test
    void findByProductIdAndCarId() {
        Long id = 1L;
        Long productId = 1L;
        String carId = "carId";
        Item item = new Item(id, productId, 0, null);
        ItemEntity itemEntity = new ItemEntity(id, productId, 0, null);
        when(itemEntityMapper.toDomain(itemEntity)).thenReturn(item);
        when(itemRepository.findByProductIdAndCartId(productId, carId)).thenReturn(Optional.of(itemEntity));
        Item returnedItem = itemJpaAdapter.findByProductIdAndCarId(productId, carId);
        verify(itemRepository).findByProductIdAndCartId(productId, carId);
        verify(itemEntityMapper).toDomain(itemEntity);
        assertNotNull(returnedItem);
        assertEquals(returnedItem, item);
    }
}