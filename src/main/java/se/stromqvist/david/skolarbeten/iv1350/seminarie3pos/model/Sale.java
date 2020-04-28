package se.stromqvist.david.skolarbeten.iv1350.seminarie3pos.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import se.stromqvist.david.skolarbeten.iv1350.seminarie3pos.DTOs.ItemDTO;
import se.stromqvist.david.skolarbeten.iv1350.seminarie3pos.util.Amount;

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
     * @return the current total price of the sale.
     */
    public BigDecimal getTotalPrice() 
    {
        return basket.getTotalPrice();
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
}
