package com.emazon.cart.adapters.driven.jpa.persistence;

import com.emazon.cart.adapters.driven.jpa.entity.ItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ItemRepository extends JpaRepository<ItemEntity, Long> {
    boolean existsByProductIdAndCartId(Long productId, String carId);
    Optional<ItemEntity> findByProductIdAndCartId(Long productId, String carId);
    List<ItemEntity> findAllByCart_Id(String id);
}
