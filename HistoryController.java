/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.*;
/**
 *
 * @author Catherine Jojo
 */
public class HistoryController {
    public HistoryController(){
        System.out.println("Customer Username:");
        Scanner input = new Scanner(System.in);
        String username=input.nextLine();
        getCustomer(username); 
    }
    
    
    public void getCustomer(String un){
        CustomerDB customerDB= CustomerDB.getInstance(); 
        Customer c; 
        
        //get customer from database
        c=(Customer)customerDB.getUser(un);
        
        //print results 
        if(c!=null){
            c.printPuchases();
        }
        else{
            System.out.println("Customer not found.");
        }
    }
}
