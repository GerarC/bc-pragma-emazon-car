package com.emazon.car.adapters.driven.jpa.mapper;

import com.emazon.car.adapters.driven.jpa.entity.CarEntity;
import com.emazon.car.adapters.driven.jpa.entity.ItemEntity;
import com.emazon.car.domain.model.Car;
import com.emazon.car.domain.model.Item;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CarEntityMapper {
    @Mapping(target = "car", ignore = true)
    Item toDomainItem(ItemEntity entity);
    Car toDomain(CarEntity carEntity);
    List<Car> toDomains(List<CarEntity> carEntityList);

    @Mapping(target = "items", ignore = true)
    CarEntity toEntity(Car car);
    List<CarEntity> toEntities(List<Car> cars);
}
