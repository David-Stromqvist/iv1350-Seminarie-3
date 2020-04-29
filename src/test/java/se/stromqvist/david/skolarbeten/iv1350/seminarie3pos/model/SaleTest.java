package se.stromqvist.david.skolarbeten.iv1350.seminarie3pos.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import se.stromqvist.david.skolarbeten.iv1350.seminarie3pos.DTOs.ItemDTO;
import se.stromqvist.david.skolarbeten.iv1350.seminarie3pos.DTOs.SaleInfoDTO;
import se.stromqvist.david.skolarbeten.iv1350.seminarie3pos.util.Amount;
import se.stromqvist.david.skolarbeten.iv1350.seminarie3pos.util.AmountENUM;

/**
 *
 * @author David
 */
public class SaleTest {
    
    private ItemDTO startItemDTO;
    private Amount startAmount;
    

    public final int itemIdentifier = 105;
    public final String itemDescription = "En sak";
    public final BigDecimal price = new BigDecimal("62.3018");
    public final double VATrate = 25;
    private final AmountENUM type = AmountENUM.NUMBER;
    
    private final double quantity = 2;
    Sale instance;
    
    @BeforeEach
    public void setUp() {
        startItemDTO = new ItemDTO(itemIdentifier, itemDescription, price, VATrate, type);
        startAmount = new Amount(quantity, type);
        instance = new Sale();
    }
    
    @AfterEach
    public void tearDown() {
        startItemDTO = null;
        startAmount = null;
        instance = null;
    }

    @Test
    public void testGetTotalPrice() {
        System.out.println("getTotalPrice");
        instance.addItem(startItemDTO, startAmount);
        BigDecimal expResult = new BigDecimal("124.60");
        BigDecimal result = instance.getTotalPrice().setScale(2, RoundingMode.HALF_UP);
        assertEquals(expResult, result);
    }

    
    
}
