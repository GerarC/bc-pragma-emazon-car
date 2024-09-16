package com.emazon.car.adapters.driving.rest.mapper.response;

import com.emazon.car.adapters.driving.rest.dto.response.CarResponse;
import com.emazon.car.domain.model.Car;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CarResponseMapper {
    CarResponse toResponse(Car car);
}
