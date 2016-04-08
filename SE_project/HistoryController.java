/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SE_project.SE_project;
import java.util.*;
/**
 *
 * @author Catherine Jojo
 */
public class HistoryController {
    public HistoryController(){}
    
    public void getCustomer(String un){
        CustomerDB customerDB=new CustomerDB(); 
        Customer c; 
        
        //get customer from database
        c=customerDB.getCustomer(un);
        
        //print results 
        if(c!=null){
            c.printPurchases();
        }
        else{
            System.out.println("Customer not found.");
        }
    }
}
