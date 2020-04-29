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
    private Sale finishedSale;
    
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
     * Ends the currant sale so no new items can be added.
     * @return the sales total price.
     */
    public BigDecimal endSale()
    {
        finishedSale = currentSale;
        currentSale = null;
        
        return finishedSale.getTotalPrice();
    }
    
    /**
     * Closes the sale and prosseses the payment.
     * 
     * @param payment amount of money payed
     * @return the sale information in the for of saleInfoDTO.
     */
    public SaleInfoDTO closeSale(BigDecimal payment)
    {
        BigDecimal change = register.processPayment(payment, finishedSale.getTotalPrice());
        SaleInfoDTO saleInfo = finishedSale.getFinalSaleInfo(payment, change);
        
        StoreInfoDTO storeInfo = database.getStoreInfo();
        register.logSale(saleInfo);
        database.reportSale(saleInfo, storeInfo);
        printer.printRecipt(saleInfo, storeInfo);
        finishedSale = null;
        return saleInfo;
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
        if (checkItem(itemToAdd) && (itemToAdd.getType() == amount.getType())) 
            currentSale.addItem(itemToAdd, amount);
        else
            System.out.println("\nWrong type in the amount\n");
        return currentSale.getTotalPrice();
    }
    
    /**
     * Tries to add an item to the current sale. Used if no amount is specified
     * Instead the amount 1 is used. And amount gets the same type as the item.
     * 
     * @param itemIdentifier the items itemidentifier.
     * @return The current total price.
     */
    public BigDecimal addItem(int itemIdentifier)
    {
        return addItem(itemIdentifier, 1.0);
    }
    
     /**
     * Tries to add an item to the current sale.
     * Creates an amount object with the same type as the item.
     * 
     * @param itemIdentifier the items itemidentifier.
     * @param quantity the quantity of the sought after item..
     * @return The current total price.
     */
    public BigDecimal addItem(int itemIdentifier, double quantity)
    {
        ItemDTO itemToAdd = getItemFromDataBase(itemIdentifier);
        if (checkItem(itemToAdd))
        {
            Amount amount = new Amount(quantity, itemToAdd.getType());
            currentSale.addItem(itemToAdd, amount);
        }
        return currentSale.getTotalPrice();
    }
    
    private ItemDTO getItemFromDataBase(int itemIdentifier)
    {
        return database.getItem(itemIdentifier);
    }
    
    
     private boolean checkItem(ItemDTO itemToCheck)
     {
        if(itemToCheck == null)
        {
            System.out.println("\nItem doesn't exist\n");
            return false;
        }
        
        return true;
     }
}
