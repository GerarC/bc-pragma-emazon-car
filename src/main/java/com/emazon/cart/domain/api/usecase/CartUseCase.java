package com.emazon.cart.domain.api.usecase;

import com.emazon.cart.domain.api.CartServicePort;
import com.emazon.cart.domain.exceptions.EntityNotFoundException;
import com.emazon.cart.domain.exceptions.ItemAlreadyAddedException;
import com.emazon.cart.domain.exceptions.MaxCategoryCountException;
import com.emazon.cart.domain.exceptions.NotEnoughProductStockException;
import com.emazon.cart.domain.model.Cart;
import com.emazon.cart.domain.model.Item;
import com.emazon.cart.domain.model.Product;
import com.emazon.cart.domain.spi.CartPersistencePort;
import com.emazon.cart.domain.spi.ItemPersistencePort;
import com.emazon.cart.domain.spi.ProductPersistencePort;
import com.emazon.cart.domain.spi.UserPersistencePort;
import com.emazon.cart.domain.utils.DomainConstants;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CartUseCase implements CartServicePort {
    private final CartPersistencePort cartPersistencePort;
    private final UserPersistencePort userPersistencePort;
    private final ProductPersistencePort productPersistencePort;
    private final ItemPersistencePort itemPersistencePort;

    public CartUseCase(CartPersistencePort cartPersistencePort, UserPersistencePort userPersistencePort, ProductPersistencePort productPersistencePort, ItemPersistencePort itemPersistencePort) {
        this.cartPersistencePort = cartPersistencePort;
        this.userPersistencePort = userPersistencePort;
        this.productPersistencePort = productPersistencePort;
        this.itemPersistencePort = itemPersistencePort;
    }

    @Override
    public Cart addItem(String userId, Item item) {
        if (!userPersistencePort.existsUserById(userId))
            throw new EntityNotFoundException(DomainConstants.USER_ENTITY_NAME, userId);

        Cart cart = getCarByUserIdOrCreate(userId);
        Product product = productPersistencePort.getProduct(item.getProductId());

        List<Item> items = cart.getItems();
        if (items != null && !items.isEmpty()) {
            List<Long> ids = items.stream().map(Item::getProductId).toList();
            List<Product> products = productPersistencePort.getProductsById(ids);

            for (int index = 0; index < products.size(); index++) {
                if (products.get(index).getQuantity() < items.get(index).getQuantity()){
                    throw new NotEnoughProductStockException(products.get(index).getName());
                }
            }
            validateCanAddProduct(products, product);
        }

        validateItem(item, cart, product);
        item.setCart(cart);
        cart.addItem(
                itemPersistencePort.save(item)
        );
        updateCar(cart);
        return cart;
    }

    @Override
    public Cart removeItem(String userId, Long productId) {
        Cart cart = cartPersistencePort.getCarByUserId(userId);
        Item item = itemPersistencePort.findByProductIdAndCarId(productId, cart.getId());
        itemPersistencePort.deleteById(item.getId());
        return updateCar(cart);
    }

    private void validateItem(Item item, Cart cart, Product product) {
        if (product.getQuantity() < item.getQuantity()) throw new NotEnoughProductStockException(product.getName());
        if(itemPersistencePort.existsByProductIdAndCarId(item.getProductId(), cart.getId()))
            throw new ItemAlreadyAddedException(cart.getId());
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

    private void validateCanAddProduct(List<Product> carProducts, Product product) {
        Map<String, Integer> categoryCount = new HashMap<>();
        carProducts.forEach(item ->
                item.getCategories().forEach(category ->
                        categoryCount.merge(category, 1, Integer::sum)
                )
        );
        product.getCategories().forEach(category -> {
            int count = categoryCount.getOrDefault(category, 0) + 1;
            if (count > DomainConstants.MAX_CATEGORY_ITEMS) throw new MaxCategoryCountException(category);
        });
    }

    private Cart updateCar(Cart cart){
        cart.setUpdatedAt(LocalDateTime.now());
        return cartPersistencePort.updateCar(cart);
    }
}
