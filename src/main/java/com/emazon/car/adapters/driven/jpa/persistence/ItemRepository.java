package com.emazon.car.adapters.driven.jpa.persistence;

import com.emazon.car.adapters.driven.jpa.entity.ItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<ItemEntity, Long> {
    boolean existsByProductIdAndCarId(Long productId, String carId);
    List<ItemEntity> findAllByCar_Id(String id);
}
