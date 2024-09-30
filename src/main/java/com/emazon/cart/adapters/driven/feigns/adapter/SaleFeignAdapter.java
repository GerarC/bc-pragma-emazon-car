package com.emazon.cart.adapters.driven.feigns.adapter;

import com.emazon.cart.adapters.driven.feigns.client.SaleFeign;
import com.emazon.cart.adapters.driven.feigns.dto.request.sale.SaleItemRequest;
import com.emazon.cart.adapters.driven.feigns.dto.request.sale.SaleItemsRequest;
import com.emazon.cart.adapters.driven.feigns.mapper.request.ItemSaleRequestMapper;
import com.emazon.cart.domain.model.Item;
import com.emazon.cart.domain.spi.SalePersistencePort;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class SaleFeignAdapter implements SalePersistencePort {
    private final SaleFeign saleFeign;
    private final ItemSaleRequestMapper itemSaleRequestMapper;

    @Override
    public boolean performSale(List<Item> items) {
        try {
            List<SaleItemRequest> saleItemRequests = itemSaleRequestMapper.toRequests(items);
            SaleItemsRequest request = SaleItemsRequest.builder().items(saleItemRequests).build();
            saleFeign.saveSales(request);
            return true;
        } catch (FeignException e) {
            log.error(e.getMessage());
            return false;
        }
    }
}
