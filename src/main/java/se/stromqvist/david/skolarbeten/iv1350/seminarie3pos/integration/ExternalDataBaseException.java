package se.stromqvist.david.skolarbeten.iv1350.seminarie3pos.integration;

/**
 * Thrown when something is wrong with the database,
 * or with the connection to the database.
 * @author David
 */
public class ExternalDataBaseException extends RuntimeException
{
    
    public ExternalDataBaseException(String msg)
    {
        super (msg);
    }
}
