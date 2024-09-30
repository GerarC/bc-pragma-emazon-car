package com.emazon.cart.adapters.driven.feigns.dto.request.report;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SaleReportRequest {
    private LocalDateTime saleDate;
    private BigDecimal totalPrice;
    private Integer productCount;
    private List<ReportItemRequest> items;
}
