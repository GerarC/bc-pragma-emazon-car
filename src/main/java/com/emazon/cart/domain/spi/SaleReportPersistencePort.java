package com.emazon.cart.domain.spi;

import com.emazon.cart.domain.model.SaleReport;

public interface SaleReportPersistencePort {
    /**
     * Save the report of a sale
     *
     * @param saleReport object that represents the report
     */
    void saveReport(SaleReport saleReport);
}
