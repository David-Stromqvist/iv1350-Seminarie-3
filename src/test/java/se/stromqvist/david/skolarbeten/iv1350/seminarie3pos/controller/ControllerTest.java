package se.stromqvist.david.skolarbeten.iv1350.seminarie3pos.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import se.stromqvist.david.skolarbeten.iv1350.seminarie3pos.DTOs.SaleInfoDTO;
import se.stromqvist.david.skolarbeten.iv1350.seminarie3pos.integration.DatabaseHandler;
import se.stromqvist.david.skolarbeten.iv1350.seminarie3pos.integration.InvalidItemIdentifierException;
import se.stromqvist.david.skolarbeten.iv1350.seminarie3pos.integration.Printer;
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
        try {
            System.out.println("endSale");
            Controller instance;
            instance = new Controller(database, printer);
            instance.startNewSale();
            instance.addItem(firstItemIdentifier);
            instance.addItem(firstItemIdentifier, new Amount(firstQuantity, firstType));
            instance.addItem(secondItemIdentifier, new Amount(secondQuantity, secondType));
            BigDecimal expResult = new BigDecimal("81.81");
            BigDecimal result = instance.endSale();
            assertEquals(expResult, result);
        } catch (Exception ex) {
            fail("Something went wrong with item handling");
        }
    }

    @Test
    public void testCloseSale() {
        try {
            System.out.println("closeSale");
            BigDecimal payment = new BigDecimal("90.00");
            Controller instance;
            instance = new Controller(database, printer);
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
        } catch (Exception ex) {
            fail("Something went wrong with item handling");
        }
    }

    @Test
    public void testAddItem_int_Amount_InvalidItemIdentifier() {
        System.out.println("addItem");
        int itemIdentifier = 95;
        Amount amount = new Amount(firstQuantity, firstType);
        Controller instance;
        try {
            instance = new Controller(database, printer);
            instance.startNewSale();
            BigDecimal expResult = new BigDecimal("0.00");
            BigDecimal result;
            try {
                result = instance.addItem(itemIdentifier, amount);
                fail("Something went wrong with item handling");
            } catch (ExternalSystemException | InvalidItemTypeException ex) {
                fail("Wrong Exception");
            } catch (InvalidItemIdentifierException ex) {
                assertTrue(ex.getMessage().contains("valid for any item"), "Wrong exception text.");
            }
        } catch (IOException ex) {
                fail("IOException in test");
        }
    }

    @Test
    public void testAddItem_int_double_InvalidItemIdentifier() {
        System.out.println("addItem");
        int itemIdentifier = 95;
        double quantity = 2.0;
        Controller instance;
        try {
            instance = new Controller(database, printer);
            instance.startNewSale();
            BigDecimal expResult = new BigDecimal("0.00");
            BigDecimal result;
            try {
                result = instance.addItem(itemIdentifier, quantity);
                fail("Something went wrong with item handling");
            } catch (InvalidItemIdentifierException  ex) {
                assertTrue(ex.getMessage().contains("valid for any item"), "Wrong exception text.");
                assertTrue(95 == ex.getItemIdentifier());
            } catch (ExternalSystemException ex) {
                fail("Wrong Exception");
            }
        } catch (IOException ex) {
                fail("IOException in test");
        }
    }

    @Test
    public void testAddItem_int_double_databaseNotConnected() {
        System.out.println("addItem");
        int itemIdentifier = 0;
        double quantity = 2.0;
        Controller instance;
        try {
            instance = new Controller(database, printer);
            instance.startNewSale();
            BigDecimal expResult = new BigDecimal("0.00");
            BigDecimal result;
            try {
                result = instance.addItem(itemIdentifier, quantity);
                fail("Something went wrong with item handling");
            } catch (ExternalSystemException ex) {
                assertTrue(ex.getMessage().contains("connection with"), "Error message shows wrong message");
            } catch (InvalidItemIdentifierException ex) {
                fail("Wrong Exception");
            }
        } catch (IOException ex) {
                fail("IOException in test");
        }
    }
    
}
