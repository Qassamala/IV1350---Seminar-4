/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.kth.iv1350.processSale.view;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 *
 * @author abdig
 */
public class FileLogger {
    private PrintWriter logStream;
    
    
    public FileLogger(){
        try {
            logStream = new PrintWriter(new FileWriter("log.txt"), true);
        } catch (IOException ioe) {
            System.out.println("CAN NOT LOG.");
            ioe.printStackTrace();
        }
    }
    
    public void log(String message) {
        logStream.println(message);
    }
    
}
