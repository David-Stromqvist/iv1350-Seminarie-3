package se.stromqvist.david.skolarbeten.iv1350.seminarie3pos.DTOs;

import java.math.BigDecimal;
import se.stromqvist.david.skolarbeten.iv1350.seminarie3pos.util.*;

/**
 *
 * @author David
 */
public class ItemDTO {

    public final int itemIdentifier;
    public final String itemDescription;
    public final BigDecimal price;
    public final float VATrate;
    private final AmountENUM type;

    public ItemDTO(int itemIdentifier, String itemDescription, BigDecimal price, float VATrate, AmountENUM type)
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
