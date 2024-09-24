package com.emazon.cart.domain.api.usecase;

import com.emazon.cart.domain.api.CartServicePort;
import com.emazon.cart.domain.exceptions.*;
import com.emazon.cart.domain.model.Cart;
import com.emazon.cart.domain.model.Item;
import com.emazon.cart.domain.model.Product;
import com.emazon.cart.domain.spi.*;
import com.emazon.cart.domain.utils.DomainConstants;
import com.emazon.cart.domain.utils.StreamUtils;
import com.emazon.cart.domain.utils.filter.ItemFilter;
import com.emazon.cart.domain.utils.pagination.CartPage;
import com.emazon.cart.domain.utils.pagination.PaginationData;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CartUseCase implements CartServicePort {
    private final CartPersistencePort cartPersistencePort;
    private final UserPersistencePort userPersistencePort;
    private final ProductPersistencePort productPersistencePort;
    private final ItemPersistencePort itemPersistencePort;
    private final SupplyPersistencePort supplyPersistencePort;

    private static final Integer NOT_CONTENT_TOTAL_PAGES = 1;
    private static final Integer NOT_CONTENT_PAGE_COUNT = 0;
    private static final Long NOT_CONTENT_PAGE_TOTAL_COUNT = 0L;
    private static final BigDecimal ZERO_PRICE = BigDecimal.ZERO;

    public CartUseCase(CartPersistencePort cartPersistencePort,
                       UserPersistencePort userPersistencePort,
                       ProductPersistencePort productPersistencePort,
                       ItemPersistencePort itemPersistencePort,
                       SupplyPersistencePort supplyPersistencePort) {
        this.cartPersistencePort = cartPersistencePort;
        this.userPersistencePort = userPersistencePort;
        this.productPersistencePort = productPersistencePort;
        this.itemPersistencePort = itemPersistencePort;
        this.supplyPersistencePort = supplyPersistencePort;
    }

    @Override
    public Cart addItem(String userId, Item newItem) {
        if (!userPersistencePort.existsUserById(userId))
            throw new EntityNotFoundException(DomainConstants.USER_ENTITY_NAME, userId);
        List<Long> ids;
        List<Product> products;

        Cart cart = getCarByUserIdOrCreate(userId);

        List<Item> items = cart.getItems();
        List<Long> cartIdItems = StreamUtils.map(items, Item::getProductId);
        ids = new ArrayList<>(cartIdItems);
        ids.add(newItem.getProductId());
        products = productPersistencePort.getProductsById(ids);


        validateCanAddProduct(products);
        validateItem(newItem, cart, products.get(products.size() - 1));
        saveItem(newItem, cart);

        Cart updatedCar = updateCar(cart);
        updatedCar.setItems(
                mapItems(updatedCar.getItems(), products)
        );

        return updatedCar;
    }

    @Override
    public Cart removeItem(String userId, Long productId) {
        Cart cart = cartPersistencePort.getCarByUserId(userId);
        Item item = itemPersistencePort.findByProductIdAndCarId(productId, cart.getId());
        itemPersistencePort.deleteById(item.getId());
        return updateCar(cart);
    }

    @Override
    public CartPage<Item> getItems(String userId, ItemFilter filter, PaginationData data) {
        Cart cart = getCarByUserIdOrCreate(userId);
        List<Item> items = cart.getItems();
        if (items == null || items.isEmpty()) return new CartPage<>(
                DomainConstants.DEFAULT_PAGE_NUMBER,
                DomainConstants.DEFAULT_PAGE_SIZE,
                NOT_CONTENT_TOTAL_PAGES,
                NOT_CONTENT_PAGE_COUNT,
                NOT_CONTENT_PAGE_TOTAL_COUNT,
                ZERO_PRICE,
                List.of());
        List<Long> ids = StreamUtils.map(items, Item::getProductId);
        filter.setIds(ids);
        CartPage<Product> productPage = productPersistencePort.getAllProducts(filter, data);
        BigDecimal totalPrice = calculateTotalPrice(cart);
        return mapProductPage2ItemPage(items, productPage, totalPrice);
    }

    private CartPage<Item> mapProductPage2ItemPage(List<Item> items, CartPage<Product> productPage, BigDecimal totalPrice) {
        CartPage<Item> page = new CartPage<>(
                productPage.getPage(),
                productPage.getPageSize(),
                productPage.getTotalPages(),
                productPage.getCount(),
                productPage.getTotalCount(),
                totalPrice,
                List.of()
        );

        page.setContent(
                StreamUtils.map(productPage.getContent(), product -> {
                    Item foundItem = getItemByProduct(items, product);
                    foundItem.setCategories(product.getCategories());
                    foundItem.setBrand(product.getBrand());
                    foundItem.setName(product.getName());
                    foundItem.setStockQuantity(product.getQuantity());
                    if (product.getQuantity() >= foundItem.getQuantity()) foundItem.setHasStock(true);
                    else {
                        foundItem.setNextSupplyDate(supplyPersistencePort.nextSupplyDate(product.getId()));
                        foundItem.setHasStock(false);
                    }
                    foundItem.setPrice(product.getPrice());

                    return foundItem;
                })
        );

        return page;
    }

    /**
     * Validates if there are enough stocks of item and if it isn't already added
     *
     * @param item    Item to validate
     * @param cart    cart to validate if it has that item
     * @param product product to validate stock
     */
    private void validateItem(Item item, Cart cart, Product product) {
        if (product.getQuantity() < item.getQuantity()) {
            LocalDateTime nextSupplyDate = supplyPersistencePort.nextSupplyDate(product.getId());
            throw new NotEnoughProductStockException(product.getName(), nextSupplyDate);
        }
        if (itemPersistencePort.existsByProductIdAndCarId(item.getProductId(), cart.getId()))
            throw new ItemAlreadyAddedException(cart.getUserId());
    }

    private void validateCanAddProduct(List<Product> carProducts) {
        Map<String, Integer> categoryCount = new HashMap<>();
        carProducts.forEach(item ->
                item.getCategories().forEach(category ->
                        categoryCount.merge(category, 1, Integer::sum)
                )
        );
        categoryCount.forEach((category, count) -> {
            if (count > DomainConstants.MAX_CATEGORY_ITEMS) throw new MaxCategoryCountException(category);
        });
    }

    private void saveItem(Item newItem, Cart cart) {
        newItem.setCart(cart);
        itemPersistencePort.save(newItem);
    }

    private Cart updateCar(Cart cart) {
        cart.setUpdatedAt(LocalDateTime.now());
        return cartPersistencePort.updateCar(cart);
    }

    private Cart getCarByUserIdOrCreate(String userId) {
        try {
            return cartPersistencePort.getCarByUserId(userId);
        } catch (EntityNotFoundException e) {
            return cartPersistencePort.createCar(
                    new Cart(null, userId, LocalDateTime.now(), LocalDateTime.now(), List.of())
            );
        }
    }

    private Item getItemByProduct(List<Item> items, Product product) {
        // I think It could be a better approach, but at this moment I don't know,
        // so It'll stay isolated to easily changing if I think on something better
        return StreamUtils.findAny(items,
                item -> item.getProductId().equals(product.getId()),
                new ItemPaginationException());
    }

    private List<Item> mapItems(List<Item> items, List<Product> products) {
        List<Item> mappedItems = new ArrayList<>();
        Map<Item, Product> zipItemProducts = StreamUtils.zip(items, products);
        zipItemProducts.forEach((item, product) -> {
            if (product.getQuantity() >= item.getQuantity()) item.setHasStock(true);
            else {
                item.setNextSupplyDate(supplyPersistencePort.nextSupplyDate(product.getId()));
                item.setHasStock(false);
            }
            item.setStockQuantity(product.getQuantity());
            item.setPrice(product.getPrice());
            item.setCategories(product.getCategories());
            item.setBrand(product.getBrand());
            item.setName(product.getName());
            mappedItems.add(item);
        });
        return mappedItems;
    }

    /**
     * Get total price getting all the products of the cart and calculates its total price
     *
     * @param cart cart to calculate total price with
     * @return Total price
     */
    private BigDecimal calculateTotalPrice(Cart cart) {
        List<Item> items = cart.getItems();
        List<Long> ids = StreamUtils.map(items, Item::getProductId);
        List<Product> products = productPersistencePort.getProductsById(ids);
        List<Item> mappedItems = mapItems(cart.getItems(), products);

        return StreamUtils.mapAndReduce(mappedItems, ZERO_PRICE,
                item -> item.getPrice().multiply(BigDecimal.valueOf(item.getQuantity())),
                BigDecimal::add
        );
    }
}
