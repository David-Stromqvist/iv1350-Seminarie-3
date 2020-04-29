package se.stromqvist.david.skolarbeten.iv1350.seminarie3pos.integration;

import java.util.LinkedList;
import java.util.List;
import se.stromqvist.david.skolarbeten.iv1350.seminarie3pos.DTOs.*;

/**
 * Used to foramt sale information into a string that can be printed.
 * @author David
 */
class Recipt {
    private final String timeOfSale;
    private final String storeName;
    private final String adress;
    private final List<String> items;
    private final String saleTotal;
    private final String saleVAT;
    private final String payment;
    private final String change;

    
    Recipt(SaleInfoDTO saleInfo, StoreInfoDTO storeInfo) 
    {
        timeOfSale = saleInfo.timeOfSale.toString();
        storeName = storeInfo.storeName;
        adress = storeInfo.adress;
        saleTotal = saleInfo.totalPrice.toString();
        saleVAT = saleInfo.totalVAT.toString();
        payment = saleInfo.payed.toString();
        change = saleInfo.change.toString();
        
        items = new LinkedList<>();
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
