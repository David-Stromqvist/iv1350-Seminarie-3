package se.stromqvist.david.skolarbeten.iv1350.seminarie3pos.integration;

/**
 * Exception that is thrown when an itemidentifier is entered that isn't
 * for an item in the database.
 * @author David
 */
public class InvalidItemIdentifierException extends Exception
{
    private final int invalidItemIdentifier;
    
    public InvalidItemIdentifierException(int itemIdentifier)
    {
        super("" + itemIdentifier + "is not valid for any item in the database");
        invalidItemIdentifier = itemIdentifier;
    }
    
    /**
     * Returns the invalid item identifern.
     * @return the int value of the invalid item Identifiern.
     */
    public int getItemIdentifier()
    {
        return invalidItemIdentifier;
    }
}
