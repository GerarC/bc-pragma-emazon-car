package com.emazon.cart.adapters.driving.rest.service.impl;

import com.emazon.cart.adapters.driving.rest.v1.dto.request.ItemRequest;
import com.emazon.cart.adapters.driving.rest.v1.dto.request.filter.ItemFilterRequest;
import com.emazon.cart.adapters.driving.rest.v1.dto.response.*;
import com.emazon.cart.adapters.driving.rest.v1.mapper.request.ItemFilterRequestMapper;
import com.emazon.cart.adapters.driving.rest.v1.mapper.request.ItemRequestMapper;
import com.emazon.cart.adapters.driving.rest.v1.mapper.request.PageQueryMapper;
import com.emazon.cart.adapters.driving.rest.v1.mapper.response.CartResponseMapper;
import com.emazon.cart.adapters.driving.rest.v1.mapper.response.FullItemResponseMapper;
import com.emazon.cart.adapters.driving.rest.v1.service.impl.CartServiceImpl;
import com.emazon.cart.domain.api.CartServicePort;
import com.emazon.cart.domain.model.Cart;
import com.emazon.cart.domain.model.Item;
import com.emazon.cart.domain.utils.DomainConstants;
import com.emazon.cart.domain.utils.filter.ItemFilter;
import com.emazon.cart.domain.utils.pagination.CartPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class CartServiceImplTest {

    @Mock
    private CartServicePort cartServicePort;
    @Mock
    private CartResponseMapper cartResponseMapper;
    @Mock
    private ItemRequestMapper itemRequestMapper;
    @Mock
    private ItemFilterRequestMapper itemFilterRequestMapper;
    @Mock
    private PageQueryMapper pageQueryMapper;
    @Mock
    private FullItemResponseMapper fullItemResponseMapper;

    @InjectMocks
    private CartServiceImpl carServiceImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void addItem() {
        String carId = "carId";
        String userId = "userId";
        Item item = new Item(5L, 5L, "name", BigDecimal.ONE, 10, null, List.of("category"), "brand",
                true, 2L, null);
        ItemRequest itemRequest = new ItemRequest(5L, 10);
        ItemResponse itemResponse = new ItemResponse(5L, "name", BigDecimal.ONE, 10, true, null);
        Cart cart = new Cart(carId, userId, LocalDateTime.now(), LocalDateTime.now(), List.of(item));
        CarResponse carResponse = new CarResponse(userId, LocalDateTime.now(), LocalDateTime.now(), List.of(itemResponse));
        when(cartResponseMapper.toResponse(any())).thenReturn(carResponse);
        when(itemRequestMapper.toDomain(any())).thenReturn(item);
        when(cartServicePort.addItem(any())).thenReturn(cart);
        CarResponse returnedResponse = carServiceImpl.addItem(itemRequest);
        verify(cartServicePort).addItem(any());
        assertNotNull(returnedResponse);
        assertEquals(carResponse, returnedResponse);
    }

    @Test
    void removeItem() {
        String carId = "carId";
        String userId = "userId";
        Item item = new Item(5L, 5L, "name", BigDecimal.ONE,
                10, null, List.of("category"), "brand",
                true, 2L, null);
        ItemResponse itemResponse = new ItemResponse(5L, "name", BigDecimal.ONE, 10, true, null);
        Cart cart = new Cart(carId, userId, LocalDateTime.now(), LocalDateTime.now(), List.of(item));
        CarResponse carResponse = new CarResponse(userId, LocalDateTime.now(), LocalDateTime.now(), List.of(itemResponse));

        when(cartResponseMapper.toResponse(any())).thenReturn(carResponse);
        when(cartServicePort.removeItem(any())).thenReturn(cart);
        CarResponse response = carServiceImpl.removeItem(item.getId());
        verify(cartServicePort).removeItem(any());
        assertNotNull(response);
        assertEquals(carResponse, response);
    }

    @Test
    void getItems() {
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

        List<FullItemResponse> responses = new ArrayList<>(List.of(
                new FullItemResponse(1L, "name", BigDecimal.ONE, 10,
                        1000L, "brand", List.of("Category1", "Category2"),
                        true, null),
                new FullItemResponse(2L, "name", BigDecimal.ONE, 10,
                        1000L, "brand", List.of("Category2", "Category3"),
                        true, null),
                new FullItemResponse(3L, "name", BigDecimal.ONE, 10,
                        1000L, "brand", List.of("Category3", "Category4"),
                        true, null),
                new FullItemResponse(4L, "name", BigDecimal.ONE, 10,
                        1000L, "brand", List.of("Category4", "Category5"),
                        true, null)
        ));
        CartPage<Item> cartPage = new CartPage<>(0, 1, 1, 4, 4L, BigDecimal.valueOf(40), items);
        PageResponse<FullItemResponse> pageResponse = new PageResponse<>(0, 1, 1, 4, 4L, BigDecimal.valueOf(40), responses);
        ItemFilterRequest filterRequest = new ItemFilterRequest(null, null, null);
        ItemFilter filter = new ItemFilter(ids, null, null, null);

        when(itemFilterRequestMapper.toDomain(filterRequest)).thenReturn(filter);
        when(pageQueryMapper.toDomain(any())).thenReturn(null);
        when(cartServicePort.getItems(any(), any())).thenReturn(cartPage);
        when(fullItemResponseMapper.toPageResponse(any())).thenReturn(pageResponse);

        PageResponse<FullItemResponse> response = carServiceImpl.getItems(filterRequest, null);

        assertNotNull(response);
        assertEquals(pageResponse, response);

    }

    @Test
    void testPurchase() {
        // Arrange
        doNothing().when(cartServicePort).purchase();

        // Act
        PurchaseProcessResponse response = carServiceImpl.purchase();

        // Assert
        assertEquals(DomainConstants.SUCCESS_SALE_MESSAGE, response.getMessage());
        verify(cartServicePort, times(1)).purchase();
    }
}