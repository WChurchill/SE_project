/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.ArrayList;

/**
 *
 * @author Catherine Jojo
 */

class ShoppingCart extends ArrayList<Song> {
    
    
    public double getTotal(){
	double total = 0;
	for(Song song : this){
	    total += song.getPrice();
	}
	return total;
    }
}
