package com.emazon.cart.adapters.driven.jpa.adapter;

import com.emazon.cart.adapters.driven.jpa.entity.CartEntity;
import com.emazon.cart.adapters.driven.jpa.mapper.CartEntityMapper;
import com.emazon.cart.adapters.driven.jpa.persistence.CartRepository;
import com.emazon.cart.adapters.driven.jpa.persistence.ItemRepository;
import com.emazon.cart.domain.exceptions.EntityNotFoundException;
import com.emazon.cart.domain.model.Cart;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class CartJpaAdapterTest {

    @Mock
    private CartRepository cartRepository;

    @Mock
    private CartEntityMapper cartEntityMapper;

    @Mock
    private ItemRepository itemRepository;

    @InjectMocks
    private CartJpaAdapter carJpaAdapter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createCar() {
        String id = "carId";
        String userId = "userId";
        CartEntity cartEntity = new CartEntity(id, userId, LocalDateTime.now(), LocalDateTime.now(), List.of());
        Cart cart = new Cart(id, userId, LocalDateTime.now(), LocalDateTime.now(), List.of());
        when(cartEntityMapper.toDomain(any())).thenReturn(cart);
        when(cartRepository.save(any())).thenReturn(cartEntity);
        when(cartEntityMapper.toEntity(any())).thenReturn(cartEntity);
        Cart cartCreated = carJpaAdapter.createCar(cart);
        verify(cartRepository).save(any());
        assertNotNull(cartCreated);
        assertEquals(cart, cartCreated);
    }

    @Test
    void getCarById() {
        String id = "carId";
        String userId = "userId";
        CartEntity cartEntity = new CartEntity(id, userId, LocalDateTime.now(), LocalDateTime.now(), List.of());
        Cart cart = new Cart(id, userId, LocalDateTime.now(), LocalDateTime.now(), List.of());
        when(cartEntityMapper.toDomain(any())).thenReturn(cart);
        when(cartRepository.findById(id)).thenReturn(Optional.of(cartEntity));
        Cart cartCreated = carJpaAdapter.getCarById(id);
        verify(cartRepository).findById(any());
        assertNotNull(cartCreated);
        assertEquals(cart, cartCreated);

    }

    @Test
    void getCarById_NotFoundException() {
        String id = "carId";
        when(cartRepository.findById(id)).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> carJpaAdapter.getCarById(id));
    }

    @Test
    void getCarByUserId() {
        String id = "carId";
        String userId = "userId";
        CartEntity cartEntity = new CartEntity(id, userId, LocalDateTime.now(), LocalDateTime.now(), List.of());
        Cart cart = new Cart(id, userId, LocalDateTime.now(), LocalDateTime.now(), List.of());
        when(cartEntityMapper.toDomain(any())).thenReturn(cart);
        when(cartRepository.findByUserId(userId)).thenReturn(Optional.of(cartEntity));
        Cart cartCreated = carJpaAdapter.getCarByUserId(userId);
        verify(cartRepository).findByUserId(any());
        assertNotNull(cartCreated);
        assertEquals(cart, cartCreated);
    }

    @Test
    void getCarByUserId_NotFoundException() {
        String userId = "userId";
        when(cartRepository.findByUserId(userId)).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> carJpaAdapter.getCarByUserId(userId));
    }

    @Test
    void updateCar() {
        String id = "carId";
        String userId = "userId";
        CartEntity cartEntity = new CartEntity(id, userId, LocalDateTime.now(), LocalDateTime.now(), List.of());
        Cart cart = new Cart(id, userId, LocalDateTime.now(), LocalDateTime.now(), List.of());
        when(cartEntityMapper.toDomain(any())).thenReturn(cart);
        when(cartRepository.save(any())).thenReturn(cartEntity);
        when(cartEntityMapper.toEntity(any())).thenReturn(cartEntity);
        when(itemRepository.findAllByCar_Id(any())).thenReturn(List.of());
        Cart cartUpdated = carJpaAdapter.updateCar(cart);
        verify(cartRepository).save(any());
        assertNotNull(cartUpdated);
        assertEquals(cart, cartUpdated);
    }
}