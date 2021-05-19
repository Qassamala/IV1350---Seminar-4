/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.kth.iv1350.processSale.model.applier;

import java.util.List;
import se.kth.iv1350.processSale.integration.Discount;
import se.kth.iv1350.processSale.model.Sale;

/**
 * Need to refactor to change price of all items in sale
 * @author abdig
 */
public class SaleDiscountApplier implements DiscountApplier{
    
    SaleDiscountApplier(){}
    
    @Override
    public double applyDiscount(List<Discount> discounts, Sale sale){
        double sum = sale.getRunningTotal();
        return (1-(discounts.get(0).getDiscountRate()))* sum;
        
    }
}
