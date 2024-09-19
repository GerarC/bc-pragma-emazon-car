package com.emazon.car.domain.api.usecase;

import com.emazon.car.domain.model.Car;
import com.emazon.car.domain.model.Item;
import com.emazon.car.domain.model.Product;
import com.emazon.car.domain.spi.CarPersistencePort;
import com.emazon.car.domain.spi.ItemPersistencePort;
import com.emazon.car.domain.spi.ProductPersistencePort;
import com.emazon.car.domain.spi.UserPersistencePort;
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
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

class CarUserCaseTest {
    @Mock
    private CarPersistencePort carPersistencePort;
    @Mock
    private UserPersistencePort userPersistencePort;
    @Mock
    private ProductPersistencePort productPersistencePort;
    @Mock
    private ItemPersistencePort itemPersistencePort;

    @InjectMocks
    private CarUserCase carUserCase;

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
        Car car = new Car(carId, userId, LocalDateTime.now(), LocalDateTime.now(), items);

        when(userPersistencePort.existsUserById(any())).thenReturn(true);
        when(carPersistencePort.getCarByUserId(userId)).thenReturn(car);
        when(productPersistencePort.getProduct(any())).thenReturn(product);
        when(productPersistencePort.getProductsById(any())).thenReturn(products);
        when(itemPersistencePort.save(item)).thenReturn(item);
        when(carPersistencePort.updateCar(any())).thenReturn(car);

        Car returnedCar = carUserCase.addItem(userId, item);
        assertNotNull(returnedCar);
        assertEquals(returnedCar.getId(), carId);
        assertEquals(returnedCar.getUserId(), userId);
        assertEquals(5, returnedCar.getItems().size());
    }
}