package se.stromqvist.david.skolarbeten.iv1350.seminarie3pos.controller;

import se.stromqvist.david.skolarbeten.iv1350.seminarie3pos.integration.DatabaseHandler;
import se.stromqvist.david.skolarbeten.iv1350.seminarie3pos.integration.Printer;
import se.stromqvist.david.skolarbeten.iv1350.seminarie3pos.integration.Register;

/**
 * Used to manage all communication between layers in the POS project.
 * @author David
 */
public class Controller {
    
    private DatabaseHandler database;
    private Printer printer;
    private Register register;
    
    public Controller (DatabaseHandler database, Printer printer)
    {
        this.database = database;
        this.printer = printer;
        
        register = new Register();
    }
    
    
}
