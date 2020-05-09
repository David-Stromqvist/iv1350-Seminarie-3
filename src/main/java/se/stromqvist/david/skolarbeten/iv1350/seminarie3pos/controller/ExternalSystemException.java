package se.stromqvist.david.skolarbeten.iv1350.seminarie3pos.controller;

/**
 * Thrown when communication to external systems does not work.
 * @author David
 */
public class ExternalSystemException extends Exception {

    public ExternalSystemException(String msg)
    {
        super(msg);
    }
    
}
