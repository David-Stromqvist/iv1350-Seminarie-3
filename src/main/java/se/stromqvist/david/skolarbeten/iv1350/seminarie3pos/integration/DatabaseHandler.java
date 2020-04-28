package se.stromqvist.david.skolarbeten.iv1350.seminarie3pos.integration;

import se.stromqvist.david.skolarbeten.iv1350.seminarie3pos.DTOs.*;

/**
 * Handling the communication to the different databeses.
 * In this project everything is hardcoded.
 * 
 * @author David
 */
public class DatabaseHandler {
    
    private final static int MAX_NUMBER_OF_ITEMS = 10;
    private ItemDTO[] items;
    
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

    private void createItems() 
    {
        //TODO
    }
    
}
