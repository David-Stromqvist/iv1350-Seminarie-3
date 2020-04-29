package se.stromqvist.david.skolarbeten.iv1350.seminarie3pos.controller;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import se.stromqvist.david.skolarbeten.iv1350.seminarie3pos.DTOs.ItemDTO;
import se.stromqvist.david.skolarbeten.iv1350.seminarie3pos.DTOs.SaleInfoDTO;
import se.stromqvist.david.skolarbeten.iv1350.seminarie3pos.DTOs.SoldItemDTO;
import se.stromqvist.david.skolarbeten.iv1350.seminarie3pos.integration.DatabaseHandler;
import se.stromqvist.david.skolarbeten.iv1350.seminarie3pos.integration.Printer;
import se.stromqvist.david.skolarbeten.iv1350.seminarie3pos.integration.Register;
import se.stromqvist.david.skolarbeten.iv1350.seminarie3pos.util.Amount;
import se.stromqvist.david.skolarbeten.iv1350.seminarie3pos.util.AmountENUM;

/**
 *
 * @author David
 */
public class ControllerTest {
    
    private DatabaseHandler database;
    private Printer printer;
    
    public final int firstItemIdentifier = 100;
    public final int secondItemIdentifier = 101;
    private final AmountENUM firstType = AmountENUM.NUMBER;
    private final AmountENUM secondType = AmountENUM.WEIGHT;
    private final double firstQuantity = 2;
    private final double secondQuantity = 0.9;
    
    public ControllerTest() {
    }
    
    @BeforeEach
    public void setUp() {
        database = new DatabaseHandler();
        printer = new Printer();
    }
    
    @AfterEach
    public void tearDown() {
        database = null;
        printer = null;
    }

    @Test
    public void testEndSale() {
        System.out.println("endSale");
        Controller instance = new Controller(database, printer);
        instance.startNewSale();
        instance.addItem(firstItemIdentifier);
        instance.addItem(firstItemIdentifier, new Amount(firstQuantity, firstType));
        instance.addItem(secondItemIdentifier, new Amount(secondQuantity, secondType));
        BigDecimal expResult = new BigDecimal("81.81");
        BigDecimal result = instance.endSale();
        assertEquals(expResult, result);
    }

    @Test
    public void testCloseSale() {
        System.out.println("closeSale");
        BigDecimal payment = new BigDecimal("90.00");
        Controller instance = new Controller(database, printer);
        instance.startNewSale();
        instance.addItem(firstItemIdentifier);
        instance.addItem(firstItemIdentifier, new Amount(firstQuantity, firstType));
        instance.addItem(secondItemIdentifier, new Amount(secondQuantity, secondType));
        instance.endSale();
        BigDecimal expTotalPrice = new BigDecimal("81.81");
        BigDecimal expTotalVAT = new BigDecimal("15.34");
        BigDecimal expTotalPriceWithoutVAT = new BigDecimal("66.47");
        BigDecimal expPayed = new BigDecimal("90.00");
        BigDecimal expChange = new BigDecimal("8.00");
        SaleInfoDTO result = instance.closeSale(payment);
        assertEquals(expTotalPrice, result.totalPrice);
        assertEquals(expTotalVAT, result.totalVAT);
        assertEquals(expTotalPriceWithoutVAT, result.totalPriceWithoutVAT);
        assertEquals(expPayed, result.payed);
        assertEquals(expChange, result.change);
    }

    @Test
    public void testAddItem_int_Amount() {
        System.out.println("addItem");
        int itemIdentifier = 95;
        Amount amount = new Amount(firstQuantity, firstType);
        Controller instance = new Controller(database, printer);
        instance.startNewSale();
        BigDecimal expResult = new BigDecimal("0.00");
        BigDecimal result = instance.addItem(itemIdentifier, amount);
        assertEquals(expResult, result);
    }

    @Test
    public void testAddItem_int_double() {
        System.out.println("addItem");
        int itemIdentifier = 95;
        double quantity = 2.0;
        Controller instance = new Controller(database, printer);
        instance.startNewSale();
        BigDecimal expResult = new BigDecimal("0.00");
        BigDecimal result = instance.addItem(itemIdentifier, quantity);
        assertEquals(expResult, result);
    }
    
}
