package se.stromqvist.david.skolarbeten.iv1350.seminarie3pos.integration;

import java.math.BigDecimal;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import se.stromqvist.david.skolarbeten.iv1350.seminarie3pos.DTOs.ItemDTO;
import se.stromqvist.david.skolarbeten.iv1350.seminarie3pos.util.AmountENUM;

/**
 *
 * @author David
 */
public class DatabaseHandlerTest {
    
    public final String itemDescription = "Tvål";
    public final BigDecimal price = new BigDecimal("24.90");
    public final double VATrate = 25;
    private final AmountENUM type = AmountENUM.NUMBER;
    
    DatabaseHandler instance;
    
    
    public DatabaseHandlerTest() {
    }
    
    @BeforeEach
    public void setUp() {
        instance = new DatabaseHandler();
    }
    
    @AfterEach
    public void tearDown() {
        instance = null;
    }

    @Test
    public void testGetItemRight() {
        try {
            System.out.println("getItem");
            int itemIdentifier = 100;
            ItemDTO expResult = new ItemDTO(itemIdentifier, itemDescription, price, VATrate, type);
            ItemDTO result = instance.getItem(itemIdentifier);
            assertEquals(expResult.itemIdentifier, result.itemIdentifier);
            assertEquals(expResult.itemDescription, result.itemDescription);
        } catch (InvalidItemIdentifierException | ExternalDataBaseException ex) {
            fail("Something went wrong");
        }
    }

    @Test
    public void testGetItemInvalidItemIdentifier() {
        try {
            System.out.println("getItem");
            int itemIdentifier = 5;
            ItemDTO expResult = null;
            ItemDTO result = instance.getItem(itemIdentifier);
            fail("Got a result");
        } catch (InvalidItemIdentifierException ex) {
            assertTrue(ex.getItemIdentifier() == 5, "Error message shows wrong message");
        } catch (ExternalDataBaseException ex) {
            fail("Something went wrong.");
        }
    }

    
    @Test
    public void testGetItemNoDatabaseConnection() {
        try {
            System.out.println("getItem");
            int itemIdentifier = 0;
            ItemDTO expResult = null;
            ItemDTO result = instance.getItem(itemIdentifier);
            fail("Got a result");
        } catch (InvalidItemIdentifierException ex) {
            fail("Something went wrong.");
        } catch (ExternalDataBaseException ex) {
            assertTrue(ex.getMessage().contains("connection with"), "Error message shows wrong message");
        }
    }
    
}
