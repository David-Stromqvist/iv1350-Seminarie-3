package se.stromqvist.david.skolarbeten.iv1350.seminarie3pos.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import se.stromqvist.david.skolarbeten.iv1350.seminarie3pos.DTOs.*;
import se.stromqvist.david.skolarbeten.iv1350.seminarie3pos.util.*;

/**
 * Keeps track of items, time for start of sale, price and VAT of the ongoing sale.
 * @author David
 */
public class Sale {
    
    private final LocalDateTime timeOfSale; 
    private final Basket basket;
    
    private final List<FinishedSaleObserver> finishedSaleObserver = new ArrayList<>();
    
    public Sale()
    {
        timeOfSale = LocalDateTime.now();
        basket = new Basket();
    }

    /**
     * 
     * @return the current total price of the sale formated to show 2 decimal places
     */
    public BigDecimal getTotalPrice() 
    {
        return basket.getTotalPrice().setScale(2, RoundingMode.HALF_UP);
    }

    /**
     * 
     * @param itemToAdd the item to add.
     * @param amount the amount of selected item to add.
     */
    public void addItem(ItemDTO itemToAdd, Amount amount)
    {
        basket.addItem(itemToAdd, amount);
    }

    /**
     * Used to generate the finale stte of a sale when the sale is closed.
     * @param payment The amount payed
     * @param change The amount that should be returned as change
     * @return A SaleInfoDTO with all relevant information concerning the sale when it is ended.
     */
    public SaleInfoDTO getFinalSaleInfo(BigDecimal payment, BigDecimal change) 
    {
        Item[] items = basket.getItemArray();
        SoldItemDTO[] soldItems = new SoldItemDTO[items.length];
        for (int i = 0; i < items.length; i++)
        {
            soldItems[i] = items[i].getSoldItemDTO();
        }
        SaleInfoDTO saleInfo;
        saleInfo = new SaleInfoDTO(soldItems, basket.getTotalPrice(), basket.getTotalVAT(),
                basket.getTotalPriceWithoutVAT(), timeOfSale, payment, change);
        
        notifyObservers(saleInfo);
        return saleInfo;
    }

    private void notifyObservers(SaleInfoDTO saleInfo) {
        for(FinishedSaleObserver observer : finishedSaleObserver)
        {
            observer.saleIsFinished(saleInfo);
        }
    }
    
    /**
     * The specified observer will be notified of a finished sale.
     * @param observer The obser to be notified.
     */
    public void addFinishedSaleObserver(FinishedSaleObserver observer)
    {
        finishedSaleObserver.add(observer);
    }
    
    /**
     * The specified observers will be notified of a finished sale.
     * @param observer The obser to be notified.
     */
    public void addFinishedSaleObserver(List<FinishedSaleObserver> observer)
    {
        finishedSaleObserver.addAll(observer);
    }
}
