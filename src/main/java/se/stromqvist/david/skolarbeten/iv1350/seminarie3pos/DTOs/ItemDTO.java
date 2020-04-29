package se.stromqvist.david.skolarbeten.iv1350.seminarie3pos.DTOs;

import java.math.BigDecimal;
import se.stromqvist.david.skolarbeten.iv1350.seminarie3pos.util.*;

/**
 * An DTO representing an item.
 * @author David
 */
public class ItemDTO {

    public final int itemIdentifier;
    public final String itemDescription;
    public final BigDecimal price;
    public final double VATrate;
    private final AmountENUM type;

    public ItemDTO(int itemIdentifier, String itemDescription, BigDecimal price, double VATrate, AmountENUM type)
    {
        this.itemIdentifier  = itemIdentifier;
        this.itemDescription = itemDescription;
        this.price           = price;
        this.VATrate         = VATrate;
        this.type            = type;
    }

    /**
     * @return the type that the price is based on.
     */
    public AmountENUM getType() {
            return type;
    }


}
