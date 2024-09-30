package com.emazon.cart.adapters.driven.feigns.mapper.request;

import com.emazon.cart.adapters.driven.feigns.dto.request.sale.SaleItemRequest;
import com.emazon.cart.domain.model.Item;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ItemSaleRequestMapper {
    SaleItemRequest toRequest(Item item);
    List<SaleItemRequest> toRequests(List<Item> items);
}
