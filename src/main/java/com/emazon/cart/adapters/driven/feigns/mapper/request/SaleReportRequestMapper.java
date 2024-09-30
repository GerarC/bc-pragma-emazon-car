package com.emazon.cart.adapters.driven.feigns.mapper.request;

import com.emazon.cart.adapters.driven.feigns.dto.request.report.SaleReportRequest;
import com.emazon.cart.domain.model.SaleReport;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SaleReportRequestMapper {
    SaleReportRequest toRequest(SaleReport saleReport);
}
