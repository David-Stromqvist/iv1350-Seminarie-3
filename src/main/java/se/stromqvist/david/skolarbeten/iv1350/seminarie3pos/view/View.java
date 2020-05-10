package se.stromqvist.david.skolarbeten.iv1350.seminarie3pos.view;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.logging.Level;
import java.util.logging.Logger;
import se.stromqvist.david.skolarbeten.iv1350.seminarie3pos.DTOs.*;
import se.stromqvist.david.skolarbeten.iv1350.seminarie3pos.controller.Controller;
import se.stromqvist.david.skolarbeten.iv1350.seminarie3pos.controller.ExternalSystemException;
import se.stromqvist.david.skolarbeten.iv1350.seminarie3pos.controller.InvalidItemTypeException;
import se.stromqvist.david.skolarbeten.iv1350.seminarie3pos.integration.InvalidItemIdentifierException;
import se.stromqvist.david.skolarbeten.iv1350.seminarie3pos.util.*;

/**
 * The view for the POS system. This class replaces the whole system
 * and are going to run a hardcoded path.
 * @author David
 */
public class View {
    
    private final Controller controller;
    private final ExceptionMessageHandler exceptionMessageHandler;
    
    public View (Controller controller)
    {
        this.controller = controller;
        controller.addFinishedSaleObserver(new TotalRevenueView());
        exceptionMessageHandler = new ExceptionMessageHandler();
    }
    
    
    /**
     * runPointOfSale is used to start up the point of sale system.
     * 
     * in this project it is hardcoded to run a specific path.
     */
    public void runPointOfSale()
    {
        runFirstSale(); 
        runSecondSale(); 
    }

    private void runFirstSale()
    {
        System.out.println("First sale");
        BigDecimal price = new BigDecimal("0.00");
        System.out.println("Start of sale");
        controller.startNewSale();
        
        
        System.out.println("Adding item nr 1");
        try 
        {
            price = controller.addItem(100);
        } 
        catch (InvalidItemIdentifierException | ExternalSystemException ex)
        {
            exceptionMessageHandler.showExceptionMessage(ex);
        }
        System.out.println(price);
        
        
        System.out.println("Adding item nr 2");
        try {
            price = controller.addItem(101, 10);
        } catch (InvalidItemIdentifierException | ExternalSystemException ex)
        {
            exceptionMessageHandler.showExceptionMessage(ex);
        }
        System.out.println(price);
        
        
        System.out.println("Adding item nr 3");
        try {
            price = controller.addItem(101, new Amount(10, AmountENUM.WEIGHT));
        } catch (InvalidItemIdentifierException | ExternalSystemException | InvalidItemTypeException ex)
        {
            exceptionMessageHandler.showExceptionMessage(ex);
        }
        System.out.println(price);
        
        
        System.out.println("Adding item nr 4, database error");
        try {
            price = controller.addItem(0, 2);
        } catch (InvalidItemIdentifierException | ExternalSystemException ex)
        {
            exceptionMessageHandler.showExceptionMessage(ex);
        }
        System.out.println(price);
        
        System.out.println("Adding item nr 4, faulty identifier");
        try {
            price = controller.addItem(97, 2);
        } catch (InvalidItemIdentifierException | ExternalSystemException ex)
        {
            exceptionMessageHandler.showExceptionMessage(ex);
        }
        System.out.println(price);
        
        System.out.println("Adding item nr 4");
        try {
            price = controller.addItem(107, 2);
        } catch (InvalidItemIdentifierException | ExternalSystemException ex)
        {
            exceptionMessageHandler.showExceptionMessage(ex);
        }
        System.out.println(price);
        
        
        System.out.println("Adding item nr 5");
        try {
            price = controller.addItem(109, 0.58);
        } catch (InvalidItemIdentifierException | ExternalSystemException ex)
        {
            exceptionMessageHandler.showExceptionMessage(ex);
        }
        System.out.println(price);
        
        price = controller.endSale().setScale(0, RoundingMode.HALF_UP);
        System.out.println(price.setScale(2));
        
        SaleInfoDTO sale = controller.closeSale(new BigDecimal("350.00"));
        System.out.println("end of recipt\n\n" + sale.change + "kr change");
    }

    private void runSecondSale()
    {
        System.out.println("Second sale");
        BigDecimal price = new BigDecimal("0.00");
        System.out.println("Start of sale");
        controller.startNewSale();
        
        
        System.out.println("Adding item nr 1");
        try 
        {
            price = controller.addItem(100);
        } 
        catch (InvalidItemIdentifierException | ExternalSystemException ex)
        {
            exceptionMessageHandler.showExceptionMessage(ex);
        }
        System.out.println(price);
        
        
        System.out.println("Adding item nr 2");
        try {
            price = controller.addItem(101, 10);
        } catch (InvalidItemIdentifierException | ExternalSystemException ex)
        {
            exceptionMessageHandler.showExceptionMessage(ex);
        }
        System.out.println(price);
        
        
        System.out.println("Adding item nr 3");
        try {
            price = controller.addItem(101, new Amount(10, AmountENUM.WEIGHT));
        } catch (InvalidItemIdentifierException | ExternalSystemException | InvalidItemTypeException ex)
        {
            exceptionMessageHandler.showExceptionMessage(ex);
        }
        System.out.println(price);
        
        
        System.out.println("Adding item nr 4, database error");
        try {
            price = controller.addItem(0, 2);
        } catch (InvalidItemIdentifierException | ExternalSystemException ex)
        {
            exceptionMessageHandler.showExceptionMessage(ex);
        }
        System.out.println(price);
        
        System.out.println("Adding item nr 4, faulty identifier");
        try {
            price = controller.addItem(97, 2);
        } catch (InvalidItemIdentifierException | ExternalSystemException ex)
        {
            exceptionMessageHandler.showExceptionMessage(ex);
        }
        System.out.println(price);
        
        System.out.println("Adding item nr 4");
        try {
            price = controller.addItem(107, 2);
        } catch (InvalidItemIdentifierException | ExternalSystemException ex)
        {
            exceptionMessageHandler.showExceptionMessage(ex);
        }
        System.out.println(price);
        
        
        System.out.println("Adding item nr 5");
        try {
            price = controller.addItem(109, 0.58);
        } catch (InvalidItemIdentifierException | ExternalSystemException ex)
        {
            exceptionMessageHandler.showExceptionMessage(ex);
        }
        System.out.println(price);
        
        price = controller.endSale().setScale(0, RoundingMode.HALF_UP);
        System.out.println(price.setScale(2));
        
        SaleInfoDTO sale = controller.closeSale(new BigDecimal("350.00"));
        System.out.println("end of recipt\n\n" + sale.change + "kr change");
       
    }
    
    
}