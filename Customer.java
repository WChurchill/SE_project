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
public class Customer extends User {
    private LinkedList<ShoppingCart> history=new LinkedList<ShoppingCart>();
    
    public Customer(String username, String password){
	super(username, password);
    }
    
    public void printPuchases(){
        for(int i=0;i<history.size();i++){
            System.out.print(history.get(i));
        }
    }

    public void addPurchase(ShoppingCart purchase){
        history.add(purchase);
    }

    public void startDialog(){
	CustomerDialog dialog = CustomerDialog.getInstance();
	dialog.mainLoop();
    }
}
