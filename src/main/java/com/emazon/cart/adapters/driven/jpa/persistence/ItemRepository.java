package com.emazon.cart.adapters.driven.jpa.persistence;

import com.emazon.cart.adapters.driven.jpa.entity.ItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ItemRepository extends JpaRepository<ItemEntity, Long> {
    boolean existsByProductIdAndCarId(Long productId, String carId);
    Optional<ItemEntity> findByProductIdAndCarId(Long productId, String carId);
    List<ItemEntity> findAllByCar_Id(String id);
}
