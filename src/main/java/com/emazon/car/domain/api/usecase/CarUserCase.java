package com.emazon.car.domain.api.usecase;

import com.emazon.car.domain.api.CarServicePort;
import com.emazon.car.domain.exceptions.EntityNotFoundException;
import com.emazon.car.domain.exceptions.ItemAlreadyAddedException;
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
        Product product = productPersistencePort.getProduct(item.getProductId());

        List<Item> items = car.getItems();
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

        validateItem(item, car, product);
        item.setCar(car);
        car.addItem(
                itemPersistencePort.save(item)
        );
        updateCar(car);
        return car;
    }

    private void validateItem(Item item, Car car, Product product) {
        if (product.getQuantity() < item.getQuantity()) throw new NotEnoughProductStockException(product.getName());
        if(itemPersistencePort.existsByProductIdAndCarId(item.getProductId(), car.getId()))
            throw new ItemAlreadyAddedException(car.getId());
    }

    private Car getCarByUserIdOrCreate(String userId) {
        try {
            return carPersistencePort.getCarByUserId(userId);
        } catch (EntityNotFoundException e) {
            return carPersistencePort.createCar(
                    new Car(null, userId, LocalDateTime.now(), LocalDateTime.now(), List.of())
            );
        }
    }

    void validateCanAddProduct(List<Product> carProducts, Product product) {
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

    private void updateCar(Car car){
        car.setUpdatedAt(LocalDateTime.now());
        carPersistencePort.updateCar(car);
    }
}
