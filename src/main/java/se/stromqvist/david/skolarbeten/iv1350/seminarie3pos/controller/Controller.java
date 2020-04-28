package se.stromqvist.david.skolarbeten.iv1350.seminarie3pos.controller;

import java.math.BigDecimal;
import se.stromqvist.david.skolarbeten.iv1350.seminarie3pos.DTOs.*;
import se.stromqvist.david.skolarbeten.iv1350.seminarie3pos.integration.*;
import se.stromqvist.david.skolarbeten.iv1350.seminarie3pos.model.*;
import se.stromqvist.david.skolarbeten.iv1350.seminarie3pos.util.*;

/**
 * Used to manage all communication between layers in the POS project.
 * @author David
 */
public class Controller {
    
    private final DatabaseHandler database;
    private final Printer printer;
    private final Register register;
    private Sale currentSale;
    
    /**
     * 
     * @param database the database used for items and external reporting.
     * @param printer the printer used for printing the recipt.
     */
    public Controller (DatabaseHandler database, Printer printer)
    {
        this.database = database;
        this.printer = printer;
        
        register = new Register();
    }
    
    /**
     * Run to start a new sale.
     */
    public void startNewSale()
    {
        currentSale = new Sale();
    }
    
    /**
     * Tries to add an item to the current sale.
     * 
     * @param itemIdentifier the items itemidentifier.
     * @param amount the quantity and type of amount to add of the item.
     * @return The current total price.
     */
    public BigDecimal addItem(int itemIdentifier, Amount amount)
    {
        ItemDTO itemToAdd = database.getItem(itemIdentifier);
        if(itemToAdd != null)
            System.out.println("Item doesn't exist");
        else if (itemToAdd.getType() != amount.getType())
            System.out.println("Wrong type in the amount");
        else
            currentSale.addItem(itemToAdd, amount);
        return currentSale.getTotalPrice();
    }
    
    /**
     * Tries to add an item to the current sale. Used if no amount is specified
     * Instead the amount 1 and type AmountENUM.NUMBER is used
     * 
     * @param itemIdentifier the items itemidentifier.
     * @return The current total price.
     */
    public BigDecimal addItem(int itemIdentifier)
    {
        addItem(itemIdentifier, (new Amount(1, AmountENUM.NUMBER)) );
        return currentSale.getTotalPrice();
    }
}
