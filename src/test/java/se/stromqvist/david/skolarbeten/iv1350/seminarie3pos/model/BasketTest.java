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
public class BasketTest {
    
    private ItemDTO startItemDTO;
    private Amount startAmount;
    

    public final int itemIdentifier = 105;
    public final String itemDescription = "En sak";
    public final BigDecimal price = new BigDecimal("140");
    public final double VATrate = 25;
    private final AmountENUM type = AmountENUM.NUMBER;
    
    private final double quantity = 2;
    
    public BasketTest() {
    }
    
    @BeforeEach
    public void setUp() {
        startItemDTO = new ItemDTO(itemIdentifier, itemDescription, price, VATrate, type);
        startAmount = new Amount(quantity, type);
        
    }
    
    @AfterEach
    public void tearDown() {
        startItemDTO = null;
        startAmount = null;
    }

    @Test
    public void testAddItem() {
        System.out.println("addItem");
        ItemDTO newItem = new ItemDTO(itemIdentifier, itemDescription, price, VATrate, type);
        Amount amount = new Amount(quantity, type);
        Basket instance = new Basket();
        instance.addItem(startItemDTO, amount);
        instance.addItem(newItem, amount);
        instance.addItem(newItem, amount);
        //assertTrue(instance.getItemArray().length == 2, "real length" + instance.getItemArray().length);
    }

    @Test
    public void testGetTotalPrice() {
        System.out.println("getTotalPrice");
        Basket instance = new Basket();
        BigDecimal expResult = new BigDecimal("0.00");
        BigDecimal result = instance.getTotalPrice();
        assertEquals(expResult, result);
    }

    @Test
    public void testGetTotalVAT() {
        System.out.println("getTotalVAT");
        Basket instance = new Basket();
        BigDecimal expResult = new BigDecimal("0.00");
        BigDecimal result = instance.getTotalVAT();
        assertEquals(expResult, result);
    }

    @Test
    public void testGetItemArray() {
        System.out.println("getItemArray");
        Basket instance = new Basket();
        instance.addItem(startItemDTO, startAmount);
        Item testItem = new Item(startItemDTO, startAmount);
        Item[] expResult = new Item[1];
        expResult[0] = testItem;
        Item[] result = instance.getItemArray();
        assertEquals(expResult[0].itemIdentifier, result[0].itemIdentifier);
        
        ItemDTO newItem = new ItemDTO(itemIdentifier, itemDescription, price, VATrate, type);
        instance.addItem(newItem, startAmount);
        instance.addItem(newItem, startAmount);
        expResult = new Item[2];
        expResult[0] = testItem;
        expResult[0] = new Item(newItem, (new Amount(4, type)));
        
    }
    
}
