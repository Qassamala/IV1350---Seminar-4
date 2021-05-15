/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.kth.iv1350.processSale.view;

import se.kth.iv1350.processSale.integration.ItemNotFoundException;

/**
 *
 * @author abdig
 */
public class ErrorMessageHandler {
    
    public void showErrorMessage(String message){
        System.out.println("Error: " + message);
    }
    
}
