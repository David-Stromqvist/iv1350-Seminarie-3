package se.stromqvist.david.skolarbeten.iv1350.seminarie3pos.model;

import se.stromqvist.david.skolarbeten.iv1350.seminarie3pos.DTOs.*;
import se.stromqvist.david.skolarbeten.iv1350.seminarie3pos.util.*;

/**
 * Representerar ett antal av ett visst föremål
 * @author David
 */
class Item {
    private final ItemDTO item;
    private Amount amount;
    
    Item (ItemDTO item, Amount amount)
    {
        this.item = item;
        this.amount = amount;
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
}
