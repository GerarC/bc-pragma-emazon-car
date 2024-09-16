package com.emazon.car.domain.api.usecase;

import com.emazon.car.domain.api.CarServicePort;
import com.emazon.car.domain.exceptions.EntityNotFoundException;
import com.emazon.car.domain.exceptions.MaxCategoryCountException;
import com.emazon.car.domain.exceptions.NotEnoughProductStockException;
import com.emazon.car.domain.model.Car;
import com.emazon.car.domain.model.Item;
import com.emazon.car.domain.model.Product;
import com.emazon.car.domain.spi.CarPersistencePort;
import com.emazon.car.domain.spi.ItemPersistencePort;
import com.emazon.car.domain.spi.ProductPersistencePort;
import com.emazon.car.domain.spi.UserPersistencePort;
import com.emazon.car.domain.utils.DomainConstants;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CarUserCase implements CarServicePort {
    private final CarPersistencePort carPersistencePort;
    private final UserPersistencePort userPersistencePort;
    private final ProductPersistencePort productPersistencePort;
    private final ItemPersistencePort itemPersistencePort;

    public CarUserCase(CarPersistencePort carPersistencePort, UserPersistencePort userPersistencePort, ProductPersistencePort productPersistencePort, ItemPersistencePort itemPersistencePort) {
        this.carPersistencePort = carPersistencePort;
        this.userPersistencePort = userPersistencePort;
        this.productPersistencePort = productPersistencePort;
        this.itemPersistencePort = itemPersistencePort;
    }

    @Override
    public Car addItem(String userId, Item item) {
        if (!userPersistencePort.existsUserById(userId))
            throw new EntityNotFoundException(DomainConstants.USER_ENTITY_NAME, userId);

        Car car = getCarByUserIdOrCreate(userId);
        Product product = productPersistencePort.getProduct(item.getId());
        if (product.getQuantity() < item.getQuantity()) throw new NotEnoughProductStockException(product.getName());

        if (!car.getItems().isEmpty()) {
            List<Item> items = car.getItems();
            List<Long> ids = items.stream().map(Item::getProductId).toList();
            List<Product> products = productPersistencePort.getProductsById(ids);

            for (int idx = 0; idx < products.size(); idx++) {
                if (products.get(idx).getQuantity() < items.get(idx).getQuantity()){
                    itemPersistencePort.deleteById(items.get(idx).getProductId());
                    throw new NotEnoughProductStockException(products.get(idx).getName());
                }
            }
            validateCanAddProduct(products, product);
        }

        car.addItem(item);
        return updateCar(car);
    }

    private Car getCarByUserIdOrCreate(String userId) {
        try {
            return carPersistencePort.getCarByUserId(userId);
        } catch (EntityNotFoundException e) {
            return carPersistencePort.createCar(
                    new Car(null, userId, LocalDateTime.now(), null)
            );
        }
    }

    void validateCanAddProduct(List<Product> carProducts, Product product) {
        Map<Long, Integer> categoryCount = new HashMap<>();
        carProducts.forEach(item ->
                item.getCategories().forEach(category ->
                        categoryCount.merge(category.getId(), 1, Integer::sum)
                )
        );
        product.getCategories().forEach(category -> {
            int count = categoryCount.getOrDefault(category.getId(), 0) + 1;
            if (count > DomainConstants.MAX_CATEGORY_ITEMS) throw new MaxCategoryCountException(category.getName());
        });
    }

    private Car updateCar(Car car){
        car.setModified(LocalDateTime.now());
        return carPersistencePort.updateCar(car);
    }
}
