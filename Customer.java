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
    private ArrayList<ShoppingCart> history=new ArrayList<ShoppingCart>();
    public ShoppingCart cart = new ShoppingCart();
    
    public Customer(String username, String password, ArrayList<ShoppingCart> history){
	super(username, password);
	this.history = history;
    }
    
    public void printPuchases(){
        for(int i=0;i<history.size();i++){
            System.out.print(history.get(i));
        }
    }

    public void addPurchase(){
	cart = new ShoppingCart();
        history.add(cart);
    }

    public void startDialog(){
	CustomerDialog dialog = CustomerDialog.getInstance();
	dialog.mainLoop();
    }

    public String toString(){
	String str = username+"\n";
	str += password+"\n";
	str += history.size()+"\n";
	for(int i = 0; i<history.size(); i++){
	    str+=history.get(i).toString();
	}
	return str;
    }
}
