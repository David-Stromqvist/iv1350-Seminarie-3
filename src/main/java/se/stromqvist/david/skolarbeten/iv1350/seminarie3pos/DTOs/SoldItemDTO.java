package se.stromqvist.david.skolarbeten.iv1350.seminarie3pos.DTOs;

import se.stromqvist.david.skolarbeten.iv1350.seminarie3pos.util.*;

/**
 * A DTO representing a quantity of a specific item.
 * @author David
 */
public class SoldItemDTO {
    public final ItemDTO item;
    public final Amount amount;
    
    public SoldItemDTO(ItemDTO item, Amount amount)
    {
        this.item = item;
        this.amount = amount;
    }
}

