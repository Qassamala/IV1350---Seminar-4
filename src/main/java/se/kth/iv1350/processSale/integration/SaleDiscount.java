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
public class SaleDiscount implements Discount {
    private int customerId;
    private double discountRate;
    
    SaleDiscount(int customerId, double discountRate ){
    this.customerId = customerId;
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
    
    public void applyDiscount(List<Item> items){
        for(Item item: items)
        {
                item.setPrice((1-this.discountRate)* item.getPrice());
        }
    }
}
