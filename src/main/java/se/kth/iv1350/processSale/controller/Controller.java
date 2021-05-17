package se.kth.iv1350.processSale.controller;

import java.util.ArrayList;
import java.util.List;
import se.kth.iv1350.processSale.integration.Accounting;
import se.kth.iv1350.processSale.integration.DatabaseNotRunningException;
import se.kth.iv1350.processSale.integration.Inventory;
import se.kth.iv1350.processSale.integration.Item;
import se.kth.iv1350.processSale.integration.ItemNotFoundException;
import se.kth.iv1350.processSale.integration.Printer;
import se.kth.iv1350.processSale.model.Receipt;
import se.kth.iv1350.processSale.model.ReceiptObserver;
import se.kth.iv1350.processSale.model.Register;
import se.kth.iv1350.processSale.model.Sale;
import se.kth.iv1350.processSale.model.Store;

/**
 *  This is the application's only controller. All calls to the model pass
 * through this class.
 * @author abdig
 */
public class Controller {
    private Sale sale;
    private Inventory inventory;
    private Accounting accounting;
    private Printer printer;
    private Register register;
    private Store store;
    private List<ReceiptObserver> receiptObservers = new ArrayList<>();
    
    public Controller (Inventory inventory, Accounting accounting, Printer printer, Register register, Store store){
        this.inventory = inventory;
        this.accounting = accounting;
        this.printer = printer;
        this.register = register;
        this.store = store;
    }
    
    /**
     * Starts a new sale. This method must be called before doing anything else
     * during a sale.
     */
    public void startSale() {
        sale = new Sale();
    }
    
    /**
     * First checks if an item with the same identifier exists in the sale, if
     * not, it checks if an item with the identifier exists in the inventory. If
     * still not found, the item returned is null and the View will handle that
     * present and errorMessage. If item was found either in the sale or 
     * inventory it will be added to the sale and returned to the view.
     * @param identifier The value used to find a matching item.
     * @return Is either a successfully identified item or null.
     * @throws ItemNotFoundException is thrown when the identifier provided to
     * checkIfItemInInventory is not found in the Inventory.
     * @throws DatabaseNotRunningException is thrown when the the database
     * cannot be reached. This is simulated by providing argument 5 to
     * checkIfItemInInventory
     */
    public Item addItemToSale(int identifier)throws ItemNotFoundException, DatabaseNotRunningException {
        Item item = sale.checkIfItemInSale(identifier);
        
        if(item == null)
        {
            item = inventory.checkIfItemInInventory(identifier);
        }

        if(item != null)
        {
            sale.addItemToSale(item);
        }
        
        return item;
    }
    
    /**
     * Returns the sale's running Total.
     * @return Is the current total amount of the sale.
     */
    public double getRunningTotal(){
        
        return sale.getRunningTotal();
    }
    
    public double endSale(){
        return this.getRunningTotal();
    } 

    /**
     * Is called when Cashier enters payment. This will calculate change to be given back 
     * to customer as well as update external systems and create a receipt that will
     * be sent to the external system Printer for printing.
     * @param amount the amount that the cashier entered as payment.
     * @return The change to be taken from the register and given to the customer.
     */
    public double enterAmountPaid(double amount) {
        double change = sale.calculateChange(amount);
        
        updateExternalSystems();
        
        createAndPrintReceipt(amount, change);
        
        return change;
    }
    
    private void updateExternalSystems()
    {
        accounting.bookSale(sale);
        inventory.updateInventory(sale);
        register.increaseRegisterAmount(sale.getRunningTotal());
    }
    
    private void createAndPrintReceipt(double amount, double change)
    {
        Receipt receipt = new Receipt(sale, store, amount, change);
        receipt.addReceiptObservers(this.receiptObservers);
        printer.print(receipt);
    }
    
    public void addReceiptObserver(ReceiptObserver obs) {
        this.receiptObservers.add(obs);
    }
    
    
    
}
