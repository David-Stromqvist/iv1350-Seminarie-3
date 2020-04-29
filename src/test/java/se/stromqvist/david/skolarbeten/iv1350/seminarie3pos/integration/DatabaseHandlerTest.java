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
        System.out.println("getItem");
        int itemIdentifier = 100;
        ItemDTO expResult = new ItemDTO(itemIdentifier, itemDescription, price, VATrate, type);
        ItemDTO result = instance.getItem(itemIdentifier);
        assertEquals(expResult.itemIdentifier, result.itemIdentifier);
        assertEquals(expResult.itemDescription, result.itemDescription);
    }

    @Test
    public void testGetItemWrong() {
        System.out.println("getItem");
        int itemIdentifier = 0;
        ItemDTO expResult = null;
        ItemDTO result = instance.getItem(itemIdentifier);
        assertEquals(expResult, result);
    }
    
}
