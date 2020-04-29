package se.stromqvist.david.skolarbeten.iv1350.seminarie3pos.integration;

import java.math.BigDecimal;
import se.stromqvist.david.skolarbeten.iv1350.seminarie3pos.DTOs.*;
import se.stromqvist.david.skolarbeten.iv1350.seminarie3pos.util.AmountENUM;

/**
 * Handling the communication to the different databeses.
 * In this project everything is hardcoded.
 * 
 * @author David
 */
public class DatabaseHandler {
    
    private final static int MAX_NUMBER_OF_ITEMS = 10;
    private final ItemDTO[] items;
    
    public DatabaseHandler()
    {
        items = new ItemDTO[MAX_NUMBER_OF_ITEMS];
        createItems();
    }

    /**
     * Returns an item DTO based on itemidentifer number.
     * Null is returned if it isn't a valid number.
     * 
     * @param itemIdentifier the identification number for an Item
     * @return the itemDTO coresponding to item identifier.
     */
    public ItemDTO getItem(int itemIdentifier) 
    {
        int itemNR = itemIdentifier - 100;
        if (itemNR < MAX_NUMBER_OF_ITEMS)
            return items[itemNR];
        else
            return null;
    }

    /**
     * Gets the stores information
     * @return store information
     */
    public StoreInfoDTO getStoreInfo() 
    {
        return new StoreInfoDTO();
    }

    /**
     * Reports the sale to external databases for leagal reasoning
     * and inventory manegment.
     * @param saleInfo inormation about the sale.
     * @param storeInfo the store the sale happened at.
     */
    public void reportSale(SaleInfoDTO saleInfo, StoreInfoDTO storeInfo) 
    {
        System.out.println("\nResultat raporterade.");
    }
    
    
    
    
    private void createItems() 
    {
        items[0] = new ItemDTO(100, "Tvål", new BigDecimal("24.90"), 25, AmountENUM.NUMBER);
        items[1] = new ItemDTO(101, "Potatis", new BigDecimal("7.90"), 6, AmountENUM.WEIGHT);
        items[2] = new ItemDTO(102, "Tidning", new BigDecimal("15"), 6, AmountENUM.NUMBER);
        items[3] = new ItemDTO(103, "Stekpanna", new BigDecimal("249"), 12, AmountENUM.NUMBER);
        items[4] = new ItemDTO(104, "Läsk", new BigDecimal("19.90"), 12, AmountENUM.NUMBER);
        
        items[5] = new ItemDTO(105, "Soppa", new BigDecimal("29.90"), 6, AmountENUM.NUMBER);
        items[6] = new ItemDTO(106, "Salad", new BigDecimal("14.90"), 25, AmountENUM.WEIGHT);
        items[7] = new ItemDTO(107, "Glass", new BigDecimal("34.90"), 12, AmountENUM.NUMBER);
        items[8] = new ItemDTO(108, "Mjölk", new BigDecimal("11.50"), 6, AmountENUM.NUMBER);
        items[9] = new ItemDTO(109, "Godis", new BigDecimal("79.90"), 25, AmountENUM.WEIGHT);
    }
    
}
