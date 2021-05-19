/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.kth.iv1350.processSale.integration;

import java.util.List;

/**
 *
 * @author abdig
 */
public class ItemDiscount implements Discount {
    private int customerId;
    private Item item;
    private double discountRate;
    
    ItemDiscount(int customerId, Item item, double discountRate ){
        this.customerId = customerId;
        this.item = item;
        this.discountRate = discountRate;
    }
    
    public int getCustomer()
    {
        return this.customerId;
    }
    
    public double getDiscountRate()
    {
        return this.discountRate;
    }
    
    /**
     * implementation of strategy algorithm
     * @param items 
     */
    public void applyDiscount(List<Item> items){
        for(Item item: items)
        {
            if(item.getIdentifier() == this.item.getIdentifier())
                item.setPrice((1-discountRate)* item.getPrice());
        }
    }
}
