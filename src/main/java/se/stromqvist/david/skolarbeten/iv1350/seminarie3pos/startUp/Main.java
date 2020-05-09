package se.stromqvist.david.skolarbeten.iv1350.seminarie3pos.startUp;

import java.io.IOException;
import se.stromqvist.david.skolarbeten.iv1350.seminarie3pos.controller.Controller;
import se.stromqvist.david.skolarbeten.iv1350.seminarie3pos.integration.DatabaseHandler;
import se.stromqvist.david.skolarbeten.iv1350.seminarie3pos.integration.Printer;
import se.stromqvist.david.skolarbeten.iv1350.seminarie3pos.view.View;

/**
 *
 * @author David
 */
public class Main {

    /**
     * @param args the command line arguments
     * this program doesn't have any.
     */
    public static void main(String[] args) throws IOException
    {
        DatabaseHandler database = new DatabaseHandler();
        Printer printer = new Printer();
        Controller cntr = new Controller(database, printer);
        View view = new View(cntr);
        
        view.runPointOfSale();
    }
    
}
