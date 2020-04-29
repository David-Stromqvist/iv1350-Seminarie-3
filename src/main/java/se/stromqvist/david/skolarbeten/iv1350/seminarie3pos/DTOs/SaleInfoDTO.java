package se.stromqvist.david.skolarbeten.iv1350.seminarie3pos.DTOs;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;

/**
 * DTO representing a completed sale
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
        this.totalPrice = totalPrice.setScale(2, RoundingMode.HALF_UP);
        this.totalVAT = totalVAT.setScale(2, RoundingMode.HALF_UP);
        this.totalPriceWithoutVAT = totalPriceWithoutVAT.setScale(2, RoundingMode.HALF_UP);
        this.timeOfSale = timeOfSale;
        this.payed = payed.setScale(2, RoundingMode.HALF_UP);
        this.change = change.setScale(2, RoundingMode.HALF_UP);
    }
}
