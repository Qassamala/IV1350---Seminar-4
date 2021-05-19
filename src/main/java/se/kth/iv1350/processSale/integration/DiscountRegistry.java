/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.kth.iv1350.processSale.integration;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author abdig
 */
public class DiscountRegistry {
    private static final DiscountRegistry INSTANCE = new DiscountRegistry();
    private List<Discount> discounts = new ArrayList<Discount>();
    
    
    private DiscountRegistry() {
           discounts.add(new SaleDiscount(2, 0.01));
           discounts.add(new ItemDiscount(2, new Item(1, 5, 0.1, "Standard Milk", "Vitamin D Enriched Milk.") ,0.1));
           discounts.add(new SaleDiscount(3, 0.1));
    }
    
    public List<Discount> getCustomerDiscounts(int id){
        List<Discount> customerDiscounts = new ArrayList<Discount>();
        for(Discount d : this.discounts)
        {
            if (d.getCustomer()== id) {
                customerDiscounts.add(d);
            }
        }
        
        return customerDiscounts;
    }
    
    public static DiscountRegistry getInstance(){
        return INSTANCE;
    }
}
