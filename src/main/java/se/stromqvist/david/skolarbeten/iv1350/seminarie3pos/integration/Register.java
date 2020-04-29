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

    public BigDecimal processPayment(BigDecimal payment, BigDecimal totalPrice) 
    {
        return (payment.subtract(totalPrice)).setScale(2, RoundingMode.HALF_UP);
    }

    public void logSale(SaleInfoDTO saleInfo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
