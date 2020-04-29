package se.stromqvist.david.skolarbeten.iv1350.seminarie3pos.DTOs;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 *
 * @author David
 */
public class SaleInfoDTO {
    public final SoldItemDTO[] items;
    public final BigDecimal totalPrice;
    public final BigDecimal totalVAT;
    public final BigDecimal totalPriceWithoutVAT;
    public final LocalDateTime timeOfSale;
    public final BigDecimal payed;
    public final BigDecimal change;
    
    
    public SaleInfoDTO(SoldItemDTO[] items, 
            BigDecimal totalPrice, BigDecimal totalVAT, 
            BigDecimal totalPriceWithoutVAT, LocalDateTime timeOfSale,
            BigDecimal payed, BigDecimal change)
    {
        this.items = items;
        this.totalPrice = totalPrice;
        this.totalVAT = totalVAT;
        this.totalPriceWithoutVAT = totalPriceWithoutVAT;
        this.timeOfSale = timeOfSale;
        this.payed = payed;
        this.change = change;
    }
}
