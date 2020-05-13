package se.stromqvist.david.skolarbeten.iv1350.seminarie3pos.view;

import java.math.BigDecimal;
import java.math.RoundingMode;
import se.stromqvist.david.skolarbeten.iv1350.seminarie3pos.DTOs.SaleInfoDTO;
import se.stromqvist.david.skolarbeten.iv1350.seminarie3pos.model.FinishedSaleObserver;

/**
 * Used to show a running total of revenue earned from finshed sales.
 * @author David
 */
public class TotalRevenueView implements FinishedSaleObserver
{
    private BigDecimal totalRevenue = new BigDecimal("0.00");

    /**
     * 
     * @param saleInfo 
     */
    @Override
    public void saleIsFinished(SaleInfoDTO saleInfo)
    {
        totalRevenue = totalRevenue.add(saleInfo.totalPrice).setScale(0, RoundingMode.HALF_UP).setScale(2);
        System.out.println("\nTotal revenue earned so far today: " + totalRevenue);
    }
    
}
