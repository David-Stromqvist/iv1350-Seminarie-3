package se.stromqvist.david.skolarbeten.iv1350.seminarie3pos.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import se.stromqvist.david.skolarbeten.iv1350.seminarie3pos.DTOs.*;
import se.stromqvist.david.skolarbeten.iv1350.seminarie3pos.util.*;

/**
 * Keeps track of items, time for start of sale, price and VAT of the ongoing sale.
 * @author David
 */
public class Sale {
    
    private final LocalDateTime timeOfSale; 
    private final Basket basket;
    
    public Sale()
    {
        timeOfSale = LocalDateTime.now();
        basket = new Basket();
    }

    /**
     * 
     * @return the current total price of the sale formated to show 2 decimal places
     */
    public BigDecimal getTotalPrice() 
    {
        return basket.getTotalPrice().setScale(2, RoundingMode.HALF_UP);
    }

    /**
     * 
     * @param itemToAdd the item to add.
     * @param amount the amount of selected item to add.
     */
    public void addItem(ItemDTO itemToAdd, Amount amount)
    {
        basket.addItem(itemToAdd, amount);
    }

    public SaleInfoDTO getFinalSaleInfo(BigDecimal payment, BigDecimal change) 
    {
        Item[] items = basket.getItemArray();
        SoldItemDTO[] soldItems = new SoldItemDTO[items.length];
        for (int i = 0; i < items.length; i++)
        {
            soldItems[i] = items[i].getSoldItemDTO();
        }
        SaleInfoDTO saleInfo;
        saleInfo = new SaleInfoDTO(soldItems, basket.getTotalPrice(), basket.getTotalVAT(),
                basket.getTotalPriceWithoutVAT(), timeOfSale, payment, change);
        return saleInfo;
    }
}
