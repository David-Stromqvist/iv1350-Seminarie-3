package se.stromqvist.david.skolarbeten.iv1350.seminarie3pos.controller;

import se.stromqvist.david.skolarbeten.iv1350.seminarie3pos.util.AmountENUM;

/**
 * Thrown when the type specified in an <code>Amount</code> object is not the same as the items type
 * @author David
 */
public class InvalidItemTypeException extends Exception 
{
    private final int itemIdentifier;
    private final AmountENUM type;

    public InvalidItemTypeException(int itemIdentifier, AmountENUM type)
    {
        super("The item specified by " + itemIdentifier + " is not of the same type as the type specified in the Amount object.");
        this.itemIdentifier = itemIdentifier;
        this.type = type;
    }
    
    /**
     * Returns the invalid item identifern.
     * @return the int value of the invalid item Identifiern.
     */
    public int getItemIdentifier()
    {
        return itemIdentifier;
    }
    
    /**
     * Returns the invalid <code>AmountENUM</code> type.
     * @return the type of the invalid item.
     */
    public AmountENUM getItemType()
    {
        return type;
    }
    
    
}
