package se.stromqvist.david.skolarbeten.iv1350.seminarie3pos.integration;

import java.math.BigDecimal;
import java.math.RoundingMode;
import se.stromqvist.david.skolarbeten.iv1350.seminarie3pos.DTOs.SaleInfoDTO;

/**
 * Used for communicating with a register.
 * Hardcoded in this project.
 * 
 * @author David
 */
public class Register {
    private BigDecimal registerCash = new BigDecimal("2000.00");

    
    /**
     * Processes payment, updates amount of cash in the register
     * 
     * @param payment the amount payed
     * @param totalPrice the price for the sale
     * @return the change (paymen - totalPrice)
     */
    public BigDecimal processPayment(BigDecimal payment, BigDecimal totalPrice) 
    {
        registerCash = registerCash.add(totalPrice).setScale(0, RoundingMode.HALF_UP);
        return (payment.subtract(totalPrice)).setScale(0, RoundingMode.HALF_UP);
    }

    /**
     * logs the sale doesn't do anything atm
     * @param saleInfo the sale to log.
     */
    public void logSale(SaleInfoDTO saleInfo) 
    {
        System.out.println("\nSale is logged");
    }
    
}
