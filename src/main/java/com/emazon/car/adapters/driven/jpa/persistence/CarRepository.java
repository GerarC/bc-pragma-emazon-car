package com.emazon.car.adapters.driven.jpa.persistence;

import com.emazon.car.adapters.driven.jpa.entity.CarEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CarRepository extends JpaRepository<CarEntity, String> {
    Optional<CarEntity> findByUserId(String userId);
}
