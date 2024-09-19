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
public interface ItemEntityMapper {
    @Mapping(target = "car", ignore = true)
    Item toDomain(ItemEntity itemEntity);
    List<Item> toDomains(List<ItemEntity> itemEntities);

    @Mapping(target = "items", ignore = true)
    CarEntity toEntity(Car car);

    ItemEntity toEntity(Item item);
    List<ItemEntity> toEntities(List<Item> items);
}
