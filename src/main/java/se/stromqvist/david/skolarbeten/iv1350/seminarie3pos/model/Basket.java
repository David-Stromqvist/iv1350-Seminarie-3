package se.stromqvist.david.skolarbeten.iv1350.seminarie3pos.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import se.stromqvist.david.skolarbeten.iv1350.seminarie3pos.DTOs.*;
import se.stromqvist.david.skolarbeten.iv1350.seminarie3pos.util.*;

/**
 * Keeps track of all items added to the sale and the total price and VAT
 * for them.
 * @author David
 */
class Basket {
    
    private final ArrayList<Item> items;
    private BigDecimal totalPrice;
    private BigDecimal totalVAT;
    
    Basket()
    {
        items = new ArrayList();
        totalPrice = new BigDecimal("0.00");
        totalVAT = new BigDecimal("0.00");
    }
    
    void addItem (ItemDTO newItem, Amount amount)
    {
        for (Item item : items) {
            if(item.itemIdentifier == newItem.itemIdentifier)
            {
                item.addAmountOfItem(amount);
                increaseTotalVATandPrice(item.getPrice(), item.getVATRate(), amount);
                return;
            }
        }
        items.add(new Item(newItem, amount));
        increaseTotalVATandPrice(newItem.price, newItem.VATrate, amount);
    }

    private BigDecimal calculatePrice(BigDecimal price, Amount amount) 
    {
        BigDecimal amountToMultiply = BigDecimal.valueOf(amount.getAmount());
        return price.multiply(amountToMultiply);
    }

    private void increaseTotalVATandPrice(BigDecimal price, double vatRate, Amount amount)
    {
        BigDecimal newPriceIncrease = calculatePrice(price, amount);
        totalPrice = totalPrice.add(newPriceIncrease);
        BigDecimal newVATIncrease = calculateNewVATIncrease(newPriceIncrease, vatRate);
        totalVAT = totalVAT.add(newVATIncrease);
    }

    private BigDecimal calculateNewVATIncrease(BigDecimal newPriceIncrease, double vatRate)
    {
        BigDecimal VATRateIncrease = BigDecimal.valueOf(vatRate/100);
        return newPriceIncrease.multiply(VATRateIncrease);
    }
}
