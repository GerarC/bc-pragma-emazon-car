package com.emazon.cart.adapters.driven.feigns.adapter;

import com.emazon.cart.adapters.driven.feigns.client.SaleReportFeign;
import com.emazon.cart.adapters.driven.feigns.mapper.request.SaleReportRequestMapper;
import com.emazon.cart.domain.model.SaleReport;
import com.emazon.cart.domain.spi.SaleReportPersistencePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SaleReportFeignAdapter implements SaleReportPersistencePort {
    private final SaleReportFeign reportFeign;
    private final SaleReportRequestMapper reportRequestMapper;

    @Override
    public void saveReport(SaleReport saleReport) {
        reportFeign.createSaleReport(
                reportRequestMapper.toRequest(saleReport)
        );
    }
}
