package se.stromqvist.david.skolarbeten.iv1350.seminarie3pos.view;

/**
 * Used to show messages from exceptions.
 * @author David
 */
public class ExceptionMessageHandler
{
    
    public void showExceptionMessage(Exception exception)
    {
        System.out.println("\nError: " + exception.getMessage());
        System.out.println();
    }
}
