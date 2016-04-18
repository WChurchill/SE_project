import java.util.*;

public class AddCartDialog {

    private static AddCartDialog instance = null;
    private final int QUIT = 0;
    private final int SEARCH_NAMES = 1;
    private final int SEARCH_ALBUMS = 2;
    private final int SEARCH_ARTISTS = 3;
    
    
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
	while(true){         //Display main menu and take choice
	    printMenu();         //Display main menu
	    try{
		int choice = input.nextInt();       //Take as choice
		input.nextLine();
		
		String query;
		switch (choice) {       //switch case to process main menu choice
		case SEARCH_NAMES:
		    System.out.print("Enter a name: ");
		    query = input.nextLine().trim().toLowerCase();
		    ArrayList<Song> results = songDB.searchSongs(query);
		    cart.add(results);
		    System.out.println("Added "+results.size()+" songs to cart");
		    return;
		case SEARCH_ALBUMS:
		    System.out.println("Not Implemented");
		    return;
		case SEARCH_ARTISTS:
		    System.out.println("Not Implemented");
		    return;
		case QUIT:
		    return;
		default:
		    throw new InputMismatchException();
		}
	    }catch (InputMismatchException e) {
		System.out.println("Invalid Choice");
		input.nextLine();
	    }
	    catch(NoSuchElementException e){
		System.out.println("Please enter a number.");
	    }
	}
    }
    
    private void printMenu(){
	System.out.println(QUIT+") Cancel");
	System.out.println(SEARCH_NAMES+") Name");
	System.out.println(SEARCH_ALBUMS+") Album");
	System.out.println(SEARCH_ARTISTS+") Artist");
	
	System.out.print(">>>Enter Choice: ");      //Prompt user for choice
    }
}
