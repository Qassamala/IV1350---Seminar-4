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
 *
 * @author abdig
 */
public interface DiscountApplier {
    
    /**
     * Applies a certain type of discount to a sale
     * @param discounts the type of discount
     */
    double applyDiscount(List<Discount> discounts, Sale sale);
}
