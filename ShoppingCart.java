/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.DateFormat;
import java.util.ArrayList;

/**
 *
 * @author Catherine Jojo
 */

class ShoppingCart extends ArrayList<Song> {
    
    private DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    private Date timeOfPurchase;

    public void setPurchaseTime(){
	timeOfPurchase = new Date();
    }
    
    public int remove(String toRemove){
	int numRemoved = 0;
	for(int index = this.size()-1; index>=0; index--){
	    String songName = this.get(index).getName();
	    if (songName.toLowerCase().contains(toRemove.toLowerCase())) {
		this.remove(index);
		numRemoved++;
	    }
	}
	return numRemoved;
    }

    public void add(ArrayList<Song> addList){
	for(Song s : addList){
	    this.add(s);
	}
    }
    
    public String prettyString(){
	String str = "";
	for(int i = 0; i<this.size(); i++){
	    Song s = this.get(i);
	    str+= s.getName()+"\t";
	    str+= s.getArtist()+"\t";
	    str+= s.getAlbum()+"\t";
	    int minutes = s.getSeconds() / 60;
	    int seconds = s.getSeconds()-(minutes * 60);
	    str+= minutes+":"+seconds+"\t";
	    str+= s.getYear()+"\t";
	    str+= s.getPrice()+"\n";
	}
	return str;
    }

    public String fileString(){
	String str = "";
	for (Song song : this){
	    str+=song;
	}
	return str;
    }

    public String toString(){
	String str = timeOfPurchase==null ? "" : dateFormat.format(timeOfPurchase);
	str+= "\tsongs: "+this.size()+"\ttotal: "+String.format("% .2f",getTotal());
	return str;
    }    
    
    public double getTotal(){
	double total = 0;
	for(Song song : this){
	    total += song.getPrice();
	}
	return total;
    }

}
