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
public class Customer {
    private String username;
    private String password; 
    LinkedList<ShoppingCart> history=new LinkedList<>();
    
    Customer(){}
    
    Customer(String un, String pwd){
        username=un;
        password=pwd;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public void addPurchase(ShoppingCart purchase){
        history.add(purchase);
    }
    
    public void printPuchases(){
        for(int i=0;i<history.size();i++){
            System.out.print(history.get(i));
        }
    }
    
}
