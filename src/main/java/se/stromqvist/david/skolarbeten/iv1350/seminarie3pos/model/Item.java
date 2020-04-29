package se.stromqvist.david.skolarbeten.iv1350.seminarie3pos.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import se.stromqvist.david.skolarbeten.iv1350.seminarie3pos.DTOs.*;
import se.stromqvist.david.skolarbeten.iv1350.seminarie3pos.util.*;

/**
 * Representerar ett antal av ett visst föremål
 * @author David
 */
class Item {
    private final ItemDTO item;
    private Amount amount;
    final int itemIdentifier;
    
    Item (ItemDTO item, Amount amount)
    {
        this.item = item;
        this.amount = amount;
        itemIdentifier = item.itemIdentifier;
    }
    
    void addAmountOfItem (Amount amountToAdd)
    {
        Amount newAmount = amount.addAmount(amountToAdd);
        if (newAmount != null)
            amount = newAmount;
        else
            System.out.println("ERROR!!!!");
    }
    
    ItemDTO getItemDTO ()
    {
        return item;
    }
    
    Amount getAmount ()
    {
        return amount;
    }
    
    /**
     * 
     * @return the price as defined for a specific quantity of this item
     */
    BigDecimal getPrice()
    {
        return item.price;
    }
    
    /**
     * 
     * @return the price for all amounts of this item.
     */
    BigDecimal getTotalPrice()
    {
        return item.price.multiply(BigDecimal.valueOf(amount.getAmount()));
    }
    
    double getVATRate()
    {
        return item.VATrate;
    }
    
    SoldItemDTO getSoldItemDTO()
    {
        return new SoldItemDTO(item, amount, getTotalPrice());
    }
    

}