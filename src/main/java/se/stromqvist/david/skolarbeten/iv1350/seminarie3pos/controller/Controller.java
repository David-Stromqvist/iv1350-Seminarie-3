package se.stromqvist.david.skolarbeten.iv1350.seminarie3pos.controller;

import java.io.IOException;
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
    private final Logger logger;
    
    private Sale currentSale;
    private Sale finishedSale;
    
    /**
     * 
     * @param database the database used for items and external reporting.
     * @param printer the printer used for printing the recipt.
     * @throws java.io.IOException if log file couldn't be created
     */
    public Controller (DatabaseHandler database, Printer printer) throws IOException
    {
        this.database = database;
        this.printer = printer;
        
        register = new Register();
        logger = new Logger();
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
     * Tries to add an item to the current sale.This one should not be needed to be used.
     * 
     * @param itemIdentifier the items itemidentifier.
     * @param amount the quantity and type of amount to add of the item.
     * @return The current total price.
     * @throws se.stromqvist.david.skolarbeten.iv1350.seminarie3pos.integration.InvalidItemIdentifierException 
     * if the itemIdentifier is not valid for items in the database.
     * @throws se.stromqvist.david.skolarbeten.iv1350.seminarie3pos.controller.ExternalSystemException
     * if there is a problem with connections to external systems.
     * @throws se.stromqvist.david.skolarbeten.iv1350.seminarie3pos.controller.InvalidItemTypeException
     */
    public BigDecimal addItem(int itemIdentifier, Amount amount) throws InvalidItemIdentifierException, ExternalSystemException, InvalidItemTypeException
    {
        ItemDTO itemToAdd = getItemFromDataBase(itemIdentifier);
        if (itemToAdd.getType() == amount.getType()) 
            currentSale.addItem(itemToAdd, amount);
        else
            throw new InvalidItemTypeException(itemIdentifier, amount.getType());
        return currentSale.getTotalPrice();
    }
    
    /**
     * Tries to add an item to the current sale.Used if no amount is specified
 Instead the amount 1 is used.And amount gets the same type as the item.
     * 
     * @param itemIdentifier the items itemidentifier.
     * @return The current total price.
     * @throws se.stromqvist.david.skolarbeten.iv1350.seminarie3pos.integration.InvalidItemIdentifierException
     * if the entered item identifier is not valid for any item in the data base.
     * @throws se.stromqvist.david.skolarbeten.iv1350.seminarie3pos.controller.ExternalSystemException
     * if there is a problem with connections to external systems.
     */
    public BigDecimal addItem(int itemIdentifier) throws InvalidItemIdentifierException, ExternalSystemException
    {
        return addItem(itemIdentifier, 1.0);
    }
    
     /**
     * Tries to add an item to the current sale.Creates an amount object with the same type as the item.
     * 
     * @param itemIdentifier the items itemidentifier.
     * @param quantity the quantity of the sought after item..
     * @return The current total price.
     * @throws se.stromqvist.david.skolarbeten.iv1350.seminarie3pos.integration.InvalidItemIdentifierException
     * if the entered item identifier is not valid for any item in the data base.
     * @throws se.stromqvist.david.skolarbeten.iv1350.seminarie3pos.controller.ExternalSystemException
     * if there is a problem with connections to external systems.
     */
    public BigDecimal addItem(int itemIdentifier, double quantity) throws InvalidItemIdentifierException, ExternalSystemException
    {
        ItemDTO itemToAdd = getItemFromDataBase(itemIdentifier);
        Amount amount = new Amount(quantity, itemToAdd.getType());
        currentSale.addItem(itemToAdd, amount);
        return currentSale.getTotalPrice();
    }
    
    private ItemDTO getItemFromDataBase(int itemIdentifier) throws InvalidItemIdentifierException, ExternalSystemException
    {
        ItemDTO item = null;
        try 
        {
            item = database.getItem(itemIdentifier);
        } 
        catch (InvalidItemIdentifierException ex)
        {
            logger.logException(ex);
            throw new InvalidItemIdentifierException(ex.getItemIdentifier());
        }
        catch (ExternalDataBaseException ex) 
        {
            logger.logException(ex);
            throw new ExternalSystemException("Could not connect to the inventory system.\nPlease try again.");
        }
        return item;
    }
}
