package se.stromqvist.david.skolarbeten.iv1350.seminarie3pos.model;

import se.stromqvist.david.skolarbeten.iv1350.seminarie3pos.DTOs.SaleInfoDTO;

/**
 * An observer interface to get information about a finished sale.
 * A class intrested in this information implements this interface.
 * @author David
 */
public interface FinishedSaleObserver
{
    /**
     * Invoked when a sale is finished.
     * @param saleInfo the finished sales information.
     */
    void saleIsFinished(SaleInfoDTO saleInfo);
}
