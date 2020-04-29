package se.stromqvist.david.skolarbeten.iv1350.seminarie3pos.DTOs;

import java.math.BigDecimal;
import java.math.RoundingMode;
import se.stromqvist.david.skolarbeten.iv1350.seminarie3pos.util.*;

/**
 * A DTO representing a quantity of a specific item.
 * @author David
 */
public class SoldItemDTO {
    public final ItemDTO item;
    public final Amount amount;
    public final BigDecimal priceForAllItems;
    
    public SoldItemDTO(ItemDTO item, Amount amount, BigDecimal priceForAllItems)
    {
        this.item = item;
        this.amount = amount;
        this.priceForAllItems = priceForAllItems.setScale(2, RoundingMode.HALF_UP);
    }
}

