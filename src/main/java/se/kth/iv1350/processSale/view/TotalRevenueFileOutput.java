/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.kth.iv1350.processSale.view;

import se.kth.iv1350.processSale.model.ReceiptObserver;

/**
 * Prints the total revenue to a file
 * @author abdig
 */
public class TotalRevenueFileOutput implements ReceiptObserver {
    private double totalRevenue;
    private FileLogger fileLogger = new FileLogger("revenueLog.txt");

    @Override
    public void newRevenue(double revenue) {
        this.totalRevenue += revenue;
        logTotalRevenue();
    }

    private void logTotalRevenue() {
        fileLogger.log("Total revenue is now: " + this.totalRevenue);
    }
    
}
