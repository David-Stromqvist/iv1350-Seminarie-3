package se.stromqvist.david.skolarbeten.iv1350.seminarie3pos.util;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author David
 */
public class AmountTest {
    
    private Amount instance;

    @BeforeEach
    public void setUp() {
        instance = new Amount(10.0, AmountENUM.NUMBER);
    }
    
    @AfterEach
    public void tearDown() {
        instance = null;
    }

    @Test
    public void testGetType() {
        System.out.println("getType");
        AmountENUM expResult = AmountENUM.NUMBER;
        AmountENUM result = instance.getType();
        assertEquals(expResult, result);
    }

    @Test
    public void testGetAmount() {
        System.out.println("getAmount");
        double expResult = 10.0;
        double result = instance.getAmount();
        assertEquals(expResult, result, 0.001);
    }

    @Test
    public void testSetAmount() {
        System.out.println("setAmount");
        double amount = 0.0;
        Amount expResult = new Amount(0.0, AmountENUM.NUMBER);
        Amount result = instance.setAmount(amount);
        assertEquals(expResult, result);
        //assertEquals(expResult.getAmount(), result.getAmount());
        //assertEquals(expResult.getType(), result.getType());
    }

    @Test
    public void testAddAmount_Amount() {
        System.out.println("addAmount");
        Amount amountToAdd = new Amount(20.0, AmountENUM.NUMBER);
        Amount expResult = new Amount(30.0, AmountENUM.NUMBER);
        Amount result = instance.addAmount(amountToAdd);
        assertEquals(expResult, result);
        //assertEquals(expResult.getAmount(), result.getAmount());
        //assertEquals(expResult.getType(), result.getType());
    }
    
    @Test
    public void testAddAmount_wrongType_Amount() {
        System.out.println("addAmount");
        Amount amountToAdd = new Amount(20.0, AmountENUM.SIZE);
        Amount expResult = null;
        Amount result = instance.addAmount(amountToAdd);
        assertEquals(expResult, result);
    }

    @Test
    public void testAddAmount_double() {
        System.out.println("addAmount");
        double amount = 20.0;
        Amount expResult = new Amount(30.0, AmountENUM.NUMBER);
        Amount result = instance.addAmount(amount);
        assertEquals(expResult, result);
        //assertEquals(expResult.getAmount(), result.getAmount());
        //assertEquals(expResult.getType(), result.getType());
    }
    
}
