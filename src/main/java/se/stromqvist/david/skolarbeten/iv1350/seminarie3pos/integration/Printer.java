package se.stromqvist.david.skolarbeten.iv1350.seminarie3pos.integration;

import se.stromqvist.david.skolarbeten.iv1350.seminarie3pos.DTOs.*;

/**
 * Used to communicate with the printer to print recipt.
 * In this project it is giving printouts using System.out.print()
 * 
 * @author David
 */
public class Printer {
    
    public Printer()
    {
        
    }

    /**
     * prints a recipt
     * @param saleInfo information about the sale
     * @param storeInfo information about the store.
     */
    public void printRecipt(SaleInfoDTO saleInfo, StoreInfoDTO storeInfo) 
    {
        System.out.println(createReciptString(saleInfo, storeInfo));
    }
    
    private String createReciptString(SaleInfoDTO saleInfo, StoreInfoDTO storeInfo)
    {
        StringBuilder reciptString = new StringBuilder();
        addLine(storeInfo.storeName, reciptString);
        addLine(storeInfo.adress, reciptString);
        
        return reciptString.toString();
    }
    
    private void addItem(SoldItemDTO item, StringBuilder reciptString)
    {
        
    }
    
    private void addLine(String lineToAdd, StringBuilder reciptString)
    {
        
    }
}
