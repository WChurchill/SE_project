import java.util.*;

public class AddCartDialog {

    private static AddCartDialog instance = null;
    
    private AddCartDialog(){
        
    }
    
    public static AddCartDialog getInstance(){
	if(instance==null) instance = new AddCartDialog();
	return instance;
    }
    
    public void add(Customer customer){
        Scanner input = new Scanner(System.in);
	SongDB songDB = SongDB.getInstance();
	ShoppingCart cart = customer.cart;
	//AddCartController addController = new AddCartController(this);
        int choice;         //int to hold choice  
        choiceMenu();       //display choice menu
        choice = input.nextInt();       //take choice from user
        input.nextLine();
        while (choice < 0 || choice > 3){     //validate sub-choice between 0-3 inclusive
	    System.out.print("\n>>>Invalid. Enter Choice 1-3, or 0 to exit: ");
	    choice = input.nextInt();
	}
	ArrayList<Song> results;
        if (choice == 0)
            return;
        else{
	    String response = input.nextLine();
	    if (choice == 1){  
		//call to addSong passing input scanner and Artists
		results = songDB.searchSongs(response);
		for(Song s : results){
		    cart.add(s);        
		}
	    }else if (choice == 2){
		System.out.println("Not Implemented");
		
		//results = songDB.searchSongs();
	    } else if (choice == 3){
		System.out.println("Not Implemented");
	    }    
	}
	
    }
    
    public static void choiceMenu(){    //choiceMenu is a menu that displays 3 specific choices of music classification: Song, Album, and Artist
        System.out.print(
			 "0.Exit\n1.Song \n2.Album \n3.Artist\n>>>Enter Choice: ");
    }
}
