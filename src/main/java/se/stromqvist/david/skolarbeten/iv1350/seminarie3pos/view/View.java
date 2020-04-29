package se.stromqvist.david.skolarbeten.iv1350.seminarie3pos.view;

import java.math.BigDecimal;
import java.math.RoundingMode;
import se.stromqvist.david.skolarbeten.iv1350.seminarie3pos.DTOs.*;
import se.stromqvist.david.skolarbeten.iv1350.seminarie3pos.controller.Controller;
import se.stromqvist.david.skolarbeten.iv1350.seminarie3pos.util.*;

/**
 * The view for the POS system. This class replaces the whole system
 * and are going to run a hardcoded path.
 * @author David
 */
public class View {
    
    private final Controller controller;
    
    public View (Controller controller)
    {
        this.controller = controller;
    }
    
    
    /**
     * runPointOfSale is used to start up the point of sale system.
     * 
     * in this project it is hardcoded to run a specific path.
     */
    public void runPointOfSale()
    {
        BigDecimal price;
        System.out.println("Start of sale");
        controller.startNewSale();
        
        System.out.println("Adding item nr 1");
        price = controller.addItem(100);
        System.out.println(price);
        
        System.out.println("Adding item nr 2");
        price = controller.addItem(101, 10);
        System.out.println(price);
        
        System.out.println("Adding item nr 3");
        price = controller.addItem(101, new Amount(10, AmountENUM.WEIGHT));
        System.out.println(price);
        
        System.out.println("Adding item nr 4");
        price = controller.addItem(107, 2);
        System.out.println(price);
        
        System.out.println("Adding item nr 5");
        price = controller.addItem(109, 0.58);
        System.out.println(price);
        
        price = controller.endSale().setScale(0, RoundingMode.HALF_UP);
        System.out.println(price.setScale(2));
        
        SaleInfoDTO sale = controller.closeSale(new BigDecimal("350.00"));
        System.out.println("end of recipt\n\n" + sale.change + " change");
        
    }
    
    
}