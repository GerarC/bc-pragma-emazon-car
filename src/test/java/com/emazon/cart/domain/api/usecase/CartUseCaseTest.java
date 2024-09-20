package com.emazon.cart.domain.api.usecase;

import com.emazon.cart.domain.model.Cart;
import com.emazon.cart.domain.model.Item;
import com.emazon.cart.domain.model.Product;
import com.emazon.cart.domain.spi.CartPersistencePort;
import com.emazon.cart.domain.spi.ItemPersistencePort;
import com.emazon.cart.domain.spi.ProductPersistencePort;
import com.emazon.cart.domain.spi.UserPersistencePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class CartUseCaseTest {
    @Mock
    private CartPersistencePort cartPersistencePort;
    @Mock
    private UserPersistencePort userPersistencePort;
    @Mock
    private ProductPersistencePort productPersistencePort;
    @Mock
    private ItemPersistencePort itemPersistencePort;

    @InjectMocks
    private CartUseCase carUserCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void addItem() {
        String carId = "carId";
        String userId = "userId";
        Item item = new Item(5L, 5L, 10, null);
        List<Item> items = new ArrayList<>(List.of(
                new Item(1L, 1L, 10, null),
                new Item(2L, 2L, 10, null),
                new Item(3L, 3L, 10, null),
                new Item(4L, 4L, 10, null)
        ));

        Product product = new Product(5L, "name", "description", "brand", 1000L, List.of("Category5", "Category6"));
        List<Product> products = List.of(
                new Product(1L, "name", "description", "brand", 1000L, List.of("Category1", "Category2")),
                new Product(1L, "name", "description", "brand", 1000L, List.of("Category2", "Category3")),
                new Product(1L, "name", "description", "brand", 1000L, List.of("Category3", "Category4")),
                new Product(1L, "name", "description", "brand", 1000L, List.of("Category4", "Category5"))
        );
        Cart cart = new Cart(carId, userId, LocalDateTime.now(), LocalDateTime.now(), items);

        when(userPersistencePort.existsUserById(any())).thenReturn(true);
        when(cartPersistencePort.getCarByUserId(userId)).thenReturn(cart);
        when(productPersistencePort.getProduct(any())).thenReturn(product);
        when(productPersistencePort.getProductsById(any())).thenReturn(products);
        when(itemPersistencePort.save(item)).thenReturn(item);
        when(cartPersistencePort.updateCar(any())).thenReturn(cart);

        Cart returnedCart = carUserCase.addItem(userId, item);
        assertNotNull(returnedCart);
        assertEquals(returnedCart.getId(), carId);
        assertEquals(returnedCart.getUserId(), userId);
        assertEquals(5, returnedCart.getItems().size());
    }

    @Test
    void removeItem() {
        Long productId = 1L;
        String carId = "carId";
        String userId = "userId";
        Item item = new Item(1L, 1L, 10, null);
        List<Item> items = new ArrayList<>(List.of(
                new Item(2L, 2L, 10, null),
                new Item(3L, 3L, 10, null),
                new Item(4L, 4L, 10, null)
        ));
        Cart cart = new Cart(carId, userId, LocalDateTime.now(), LocalDateTime.now(), items);

        when(cartPersistencePort.getCarByUserId(userId)).thenReturn(cart);
        when(itemPersistencePort.findByProductIdAndCarId(1L, carId)).thenReturn(item);
        doNothing().when(itemPersistencePort).deleteById(any());
        when(cartPersistencePort.updateCar(any())).thenReturn(cart);
        Cart returnedCart = carUserCase.removeItem(userId, productId);
        verify(itemPersistencePort).deleteById(any());
        verify(cartPersistencePort).updateCar(any());
        assertNotNull(returnedCart);
        assertEquals(returnedCart.getId(), carId);
    }
}