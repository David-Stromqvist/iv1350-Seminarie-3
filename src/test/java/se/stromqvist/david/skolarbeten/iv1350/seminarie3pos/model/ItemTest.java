package se.stromqvist.david.skolarbeten.iv1350.seminarie3pos.model;

import java.math.BigDecimal;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import se.stromqvist.david.skolarbeten.iv1350.seminarie3pos.DTOs.ItemDTO;
import se.stromqvist.david.skolarbeten.iv1350.seminarie3pos.util.*;

/**
 *
 * @author David
 */
public class ItemTest {
    
    private ItemDTO itemDTO;
    private Amount amount;
    

    public final int itemIdentifier = 105;
    public final String itemDescription = "En sak";
    public final BigDecimal price = new BigDecimal("140");
    public final double VATrate = 25;
    private final AmountENUM type = AmountENUM.WEIGHT;
    
    private final double quantity = 0.7;
    
    private Item instance = null;
    
    
    
    public ItemTest() {
    }
    
    @BeforeEach
    public void setUp() {
        itemDTO = new ItemDTO(itemIdentifier, itemDescription, price, VATrate, type);
        amount = new Amount(quantity, type);
        
        instance = new Item(itemDTO, amount);
    }
    
    @AfterEach
    public void tearDown() {
        instance = null;
        itemDTO = null;
        amount = null;
    }

    @Test
    public void testAddAmountOfItem() {
        System.out.println("addAmountOfItem");
        Amount amountToAdd = new Amount(0.5, type);
        instance.addAmountOfItem(amountToAdd);
        Amount expectedAmount = new Amount(1.2, type);
        assertEquals(instance.getAmount(), expectedAmount, "FEL med doubles");
        assertFalse(instance.getAmount().equals(amountToAdd));
        amountToAdd = new Amount(1.2, AmountENUM.NUMBER);
        assertFalse(instance.getAmount().equals(amountToAdd));
        
    }

    @Test
    public void testGetItemDTO() {
        System.out.println("getItemDTO");
        int expResult = itemIdentifier;
        ItemDTO result = instance.getItemDTO();
        assertEquals(expResult, result.itemIdentifier);
    }

    @Test
    public void testGetAmount() {
        System.out.println("getAmount");
        Amount expResult = amount;
        Amount result = instance.getAmount();
        assertEquals(expResult, result);
    }
    
}
