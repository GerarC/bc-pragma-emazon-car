package com.emazon.cart.domain.api.usecase;

import com.emazon.cart.domain.exceptions.NotEnoughProductStockException;
import com.emazon.cart.domain.model.Cart;
import com.emazon.cart.domain.model.Item;
import com.emazon.cart.domain.model.Product;
import com.emazon.cart.domain.model.SaleReport;
import com.emazon.cart.domain.spi.*;
import com.emazon.cart.domain.utils.filter.ItemFilter;
import com.emazon.cart.domain.utils.pagination.CartPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class CartUseCaseTest {
    private static final Logger log = LoggerFactory.getLogger(CartUseCaseTest.class);
    @Mock
    private CartPersistencePort cartPersistencePort;

    @Mock
    private UserPersistencePort userPersistencePort;

    @Mock
    private ProductPersistencePort productPersistencePort;

    @Mock
    private ItemPersistencePort itemPersistencePort;

    @Mock
    private SupplyPersistencePort supplyPersistencePort;

    @Mock
    private SalePersistencePort salePersistencePort;

    @Mock
    private SaleReportPersistencePort saleReportPersistencePort;

    @InjectMocks
    private CartUseCase cartUseCase;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void addItem() {
        String carId = "carId";
        String userId = "userId";
        Item item = new Item(5L, 5L, "name", BigDecimal.ONE, 10, null,
                List.of("Category5", "Category6"), "brand", false, 1000L, null);
        List<Item> items = new ArrayList<>(List.of(
                new Item(1L, 1L, "name", BigDecimal.ONE, 10, null,
                        List.of("Category1", "Category2"), "brand", true, 1000L, null),
                new Item(2L, 2L, "name", BigDecimal.ONE, 10, null,
                        List.of("Category2", "Category3"), "brand", true, 1000L, null),
                new Item(3L, 3L, "name", BigDecimal.ONE, 10, null,
                        List.of("Category3", "Category4"), "brand", true, 1000L, null),
                new Item(4L, 4L, "name", BigDecimal.ONE, 10, null,
                        List.of("Category4", "Category5"), "brand", true, 1000L, null)
        ));

        Product product = new Product(5L, "name", "description", "brand",
                BigDecimal.ONE, 1000L, List.of("Category5", "Category6"));
        List<Product> products = List.of(
                new Product(1L, "name", "description", "brand",
                        BigDecimal.ONE, 1000L, List.of("Category1", "Category2")),
                new Product(2L, "name", "description", "brand",
                        BigDecimal.ONE, 1000L, List.of("Category2", "Category3")),
                new Product(3L, "name", "description", "brand",
                        BigDecimal.ONE, 1000L, List.of("Category3", "Category4")),
                new Product(4L, "name", "description", "brand",
                        BigDecimal.ONE, 1000L, List.of("Category4", "Category5"))
        );
        Cart cart = new Cart(carId, userId, LocalDateTime.now(), LocalDateTime.now(), items);

        when(userPersistencePort.existsUserById(any())).thenReturn(true);
        when(cartPersistencePort.getCarByUserId(userId)).thenReturn(cart);
        when(productPersistencePort.getProduct(any())).thenReturn(product);
        when(productPersistencePort.getProductsById(any())).thenReturn(products);
        when(itemPersistencePort.save(item)).thenReturn(item);
        when(cartPersistencePort.updateCar(any())).thenReturn(cart);

        Cart returnedCart = cartUseCase.addItem(item);
        assertNotNull(returnedCart);
        assertEquals(returnedCart.getId(), carId);
        assertEquals(returnedCart.getUserId(), userId);
        assertEquals(4, returnedCart.getItems().size());
    }

    @Test
    void removeItem() {
        Long productId = 1L;
        String carId = "carId";
        String userId = "userId";
        Item item =
        new Item(1L, 1L, "name", BigDecimal.ONE, 10, null, List.of("Category1", "Category2"),
                "brand", true, 1000L, null);
        List<Item> items = new ArrayList<>(List.of(
                new Item(2L, 2L, "name", BigDecimal.ONE, 10, null,
                        List.of("Category2", "Category3"), "brand", true, 1000L, null),
                new Item(3L, 3L, "name", BigDecimal.ONE, 10, null,
                        List.of("Category3", "Category4"), "brand", true, 1000L, null),
                new Item(4L, 4L, "name", BigDecimal.ONE, 10, null,
                        List.of("Category4", "Category5"), "brand", true, 1000L, null)
        ));
        Cart cart = new Cart(carId, userId, LocalDateTime.now(), LocalDateTime.now(), items);

        when(cartPersistencePort.getCarByUserId(userId)).thenReturn(cart);
        when(itemPersistencePort.findByProductIdAndCarId(1L, carId)).thenReturn(item);
        doNothing().when(itemPersistencePort).deleteById(any());
        when(cartPersistencePort.updateCar(any())).thenReturn(cart);
        Cart returnedCart = cartUseCase.removeItem(productId);
        verify(itemPersistencePort).deleteById(any());
        verify(cartPersistencePort).updateCar(any());
        assertNotNull(returnedCart);
        assertEquals(returnedCart.getId(), carId);
    }

    @Test
    void getItems() {
        String carId = "carId";
        String userId = "userId";
        List<Long> ids = List.of(1L, 2L, 3L, 4L);
        List<Item> items = new ArrayList<>(List.of(
                new Item(1L, 1L, "name", BigDecimal.ONE, 10, null,
                        List.of("Category1", "Category2"), "brand", true, 1000L, null),
                new Item(2L, 2L, "name", BigDecimal.ONE, 10, null,
                        List.of("Category2", "Category3"), "brand", true, 1000L, null),
                new Item(3L, 3L, "name", BigDecimal.ONE, 10, null,
                        List.of("Category3", "Category4"), "brand", true, 1000L, null),
                new Item(4L, 4L, "name", BigDecimal.ONE, 10, null,
                        List.of("Category4", "Category5"), "brand", true, 1000L, null)
        ));
        List<Product> products = List.of(
                new Product(1L, "name", "description", "brand",
                        BigDecimal.ONE, 1000L, List.of("Category1", "Category2")),
                new Product(2L, "name", "description", "brand",
                        BigDecimal.ONE, 1000L, List.of("Category2", "Category3")),
                new Product(3L, "name", "description", "brand",
                        BigDecimal.ONE, 1000L, List.of("Category3", "Category4")),
                new Product(4L, "name", "description", "brand",
                        BigDecimal.ONE, 1000L, List.of("Category4", "Category5"))
        );
        Cart cart = new Cart(carId, userId, LocalDateTime.now(), LocalDateTime.now(), items);
        ItemFilter filter = new ItemFilter(ids, null, null, null);
        CartPage<Product> mockPage = new CartPage<>();
        mockPage.setContent(products);


        when(cartPersistencePort.getCarByUserId(userId)).thenReturn(cart);
        when(productPersistencePort.getAllProducts(filter, null)).thenReturn(mockPage);
        when(productPersistencePort.getProductsById(any())).thenReturn(products);

        CartPage<Item> returnedPage = cartUseCase.getItems(filter, null);
        log.trace(returnedPage.toString());
        assertNotNull(returnedPage);
        assertEquals(returnedPage.getContent().size(), products.size());
    }

    @Test
    void getItemsNotStock() {
        Random random = new Random();
        String carId = "carId";
        String userId = "userId";
        List<Long> ids = List.of(1L, 2L, 3L, 4L);
        List<Item> items = new ArrayList<>(List.of(
                new Item(1L, 1L, "name", BigDecimal.ONE, 10, null,
                        List.of("Category1", "Category2"), "brand", true, 1000L, null),
                new Item(2L, 2L, "name", BigDecimal.ONE, 10, null,
                        List.of("Category2", "Category3"), "brand", true, 1000L, null),
                new Item(3L, 3L, "name", BigDecimal.ONE, 10, null,
                        List.of("Category3", "Category4"), "brand", true, 1000L, null),
                new Item(4L, 4L, "name", BigDecimal.ONE, 10, null,
                        List.of("Category4", "Category5"), "brand", true, 1000L, null)
        ));
        List<Product> products = List.of(
                new Product(1L, "name", "description", "brand",
                        BigDecimal.ONE, 1L, List.of("Category1", "Category2")),
                new Product(2L, "name", "description", "brand",
                        BigDecimal.ONE, 1L, List.of("Category2", "Category3")),
                new Product(3L, "name", "description", "brand",
                        BigDecimal.ONE, 1L, List.of("Category3", "Category4")),
                new Product(4L, "name", "description", "brand",
                        BigDecimal.ONE, 1L, List.of("Category4", "Category5"))
        );
        Cart cart = new Cart(carId, userId, LocalDateTime.now(), LocalDateTime.now(), items);
        ItemFilter filter = new ItemFilter(ids, null, null, null);
        CartPage<Product> mockPage = new CartPage<>();
        mockPage.setContent(products);


        when(cartPersistencePort.getCarByUserId(userId)).thenReturn(cart);
        when(productPersistencePort.getAllProducts(filter, null)).thenReturn(mockPage);
        when(productPersistencePort.getProductsById(any())).thenReturn(products);
        when(supplyPersistencePort.nextSupplyDate(any())).thenReturn(LocalDateTime.now().plusDays(random.nextInt(10)));

        CartPage<Item> returnedPage = cartUseCase.getItems(filter, null);
        assertNotNull(returnedPage);
        assertEquals(returnedPage.getContent().size(), products.size());
        returnedPage.getContent().forEach(item -> assertFalse(item.getHasStock()));
    }

    @Test
    void testPurchase_Success() {
        String userId = "user123";
        Cart cart = new Cart("cart1", userId, LocalDateTime.now(), LocalDateTime.now(), new ArrayList<>());
        List<Item> items = List.of(
                new Item(1L, 1001L, "Product A", new BigDecimal("20.00"), 2, cart, List.of("Category1"), "BrandA", true, 100L, null),
                new Item(2L, 1002L, "Product B", new BigDecimal("30.00"), 1, cart, List.of("Category2"), "BrandB", true, 50L, null)
        );
        cart.setItems(items);

        List<Product> products = List.of(
                new Product(1001L, "Product A", "Desc A", "BrandA", new BigDecimal("20.00"), 100L, List.of("Category1")),
                new Product(1002L, "Product B", "Desc B", "BrandB", new BigDecimal("30.00"), 50L, List.of("Category2"))
        );

        when(userPersistencePort.getIdFromCurrentUser()).thenReturn(userId);
        when(cartPersistencePort.getCarByUserId(userId)).thenReturn(cart);
        when(productPersistencePort.getProductsById(any())).thenReturn(products);
        when(salePersistencePort.performSale(any())).thenReturn(true);

        cartUseCase.purchase();

        verify(salePersistencePort, times(1)).performSale(items);
        items.forEach(item -> verify(itemPersistencePort, times(1)).deleteById(item.getId()));
        verify(saleReportPersistencePort, times(1)).saveReport(any(SaleReport.class));
        verify(cartPersistencePort, times(1)).updateCar(cart);
    }

    @Test
    void testPurchase_StockInsufficient_ThrowsException() {
        String userId = "user123";
        Cart cart = new Cart("cart1", userId, LocalDateTime.now(), LocalDateTime.now(), new ArrayList<>());
        List<Item> items = List.of(
                new Item(1L, 1001L, "Product A", new BigDecimal("20.00"), 2, cart, List.of("Category1"), "BrandA", true, 100L, null),
                new Item(2L, 1002L, "Product B", new BigDecimal("30.00"), 1, cart, List.of("Category2"), "BrandB", true, 50L, null)
        );
        cart.setItems(items);

        List<Product> products = List.of(
                new Product(1001L, "Product A", "Desc A", "BrandA", new BigDecimal("20.00"), 1L, List.of("Category1")),  // Insufficient stock
                new Product(1002L, "Product B", "Desc B", "BrandB", new BigDecimal("30.00"), 50L, List.of("Category2"))
        );

        when(userPersistencePort.getIdFromCurrentUser()).thenReturn(userId);
        when(cartPersistencePort.getCarByUserId(userId)).thenReturn(cart);
        when(productPersistencePort.getProductsById(any())).thenReturn(products);

        assertThrows(NotEnoughProductStockException.class, () -> cartUseCase.purchase());

        verify(salePersistencePort, times(0)).performSale(any());
        verify(itemPersistencePort, times(0)).deleteById(any());
        verify(saleReportPersistencePort, times(0)).saveReport(any(SaleReport.class));
    }
}