package se.stromqvist.david.skolarbeten.iv1350.seminarie3pos.integration;

import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.List;
import se.stromqvist.david.skolarbeten.iv1350.seminarie3pos.DTOs.*;
import se.stromqvist.david.skolarbeten.iv1350.seminarie3pos.util.AmountENUM;

/**
 * Used to foramt sale information into a string that can be printed.
 * @author David
 */
class Recipt {
    private final String dateOfSale;
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
        dateOfSale = DateTimeFormatter.ISO_LOCAL_DATE.format(saleInfo.timeOfSale);
        timeOfSale = DateTimeFormatter.ISO_LOCAL_TIME.format(saleInfo.timeOfSale.withNano(0));
        storeName = storeInfo.storeName;
        adress = storeInfo.adress;
        saleTotal = saleInfo.totalPrice.toString();
        saleVAT = saleInfo.totalVAT.toString();
        payment = saleInfo.payed.toString();
        change = saleInfo.change.toString();
        
        items = new LinkedList<>();
        addItems(saleInfo.items);
    }
    
    
    /**
     * Formats and returns a string that can be printed.
     * @return returns a string representing the recipt
     */
     String createReciptString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("RECIEPT\n");
        sb.append(storeName).append("\n");
        sb.append(adress).append("\n");
        sb.append("Sale Date: ").append(dateOfSale).append("\n");
        sb.append("Sale Time: ").append(timeOfSale).append("\n");
        sb.append("\n\n");
        items.forEach((item) -> 
        {
            sb.append(item).append("\n");
        });
        
        sb.append("\nTotal: ").append(saleTotal).append("kr\n");
        sb.append("Of wich VAT is: ").append(saleVAT).append("kr\n\n");
        sb.append("Payed: ").append(payment).append("kr\n");
        sb.append("Change: ").append(change).append("kr");
        sb.append("\n\n");
        return sb.toString();
    }
    
    private void addItems(SoldItemDTO[] items)
    {
        for(SoldItemDTO item : items){
             this.items.add(itemAsString(item));
        }
    }
        
    private String itemAsString(SoldItemDTO item)
    {
        StringBuilder sb = new StringBuilder();
        
        if (item.item.getType() == AmountENUM.WEIGHT)
        {
            sb.append(String.format("%.2f", item.amount.getAmount()));
            sb.append("kg");
        }
        else
        {
            sb.append(String.format("%.0f", item.amount.getAmount()));
        }
        sb.append(" - ");
        sb.append(item.item.itemDescription).append(" - ");
        sb.append(item.item.price.toString()).append(" kr");
        if (item.item.getType() == AmountENUM.WEIGHT)
            sb.append("/kg");
        else
            sb.append("/st");
        
        sb.append("\n\t\t\t").append(item.priceForAllItems).append("kr");
        
        return sb.toString();
    }
}