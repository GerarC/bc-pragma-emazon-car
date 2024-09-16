package com.emazon.car.adapters.driven.jpa.mapper;

import com.emazon.car.adapters.driven.jpa.entity.CarEntity;
import com.emazon.car.domain.model.Car;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CarEntityMapper {
    Car toDomain(CarEntity carEntity);
    List<Car> toDomains(List<CarEntity> carEntityList);
    CarEntity toEntity(Car car);
    List<CarEntity> toEntities(List<Car> cars);
}
