import java.util.Scanner;
import java.util.NoSuchElementException;
import java.util.InputMismatchException;

public class CustomerDialog {
    private final int ADD_CART = 1;
    private final int REMOVE_CART = 2;
    private final int SEARCH_SONG = 3;
    private final int VIEW_CART = 4;
    private final int CHECKOUT = 5;
    private final int LOGOUT = 6;

    private static CustomerDialog instance;
    public static CustomerDialog getInstance(){
	if(instance==null) instance = new CustomerDialog();
	return instance;
    }

    private CustomerDialog(){
	
    }

    private void printMenu(){
	System.out.println(ADD_CART+") Add Song");
	System.out.println(REMOVE_CART+") Remove from Cart");
	System.out.println(SEARCH_SONG+") Search Songs");
	System.out.println(VIEW_CART+") View Cart");
	System.out.println(CHECKOUT+") Checkout");
	System.out.println(LOGOUT+") Logout");
	
	System.out.print(">>>Enter Choice: ");      //Prompt user for choice
    }
    
    public void mainLoop(){
	Scanner input = new Scanner(System.in);
	while(true){         //Display main menu and take choice
	    printMenu();         //Display main menu
	    try{
		int choice = input.nextInt();       //Take as choice
            
		switch (choice) {       //switch case to process main menu choice
		case ADD_CART:
		    System.out.println("Not Implemented");
		    break;
		case REMOVE_CART:
		    System.out.println("Not Implemented");
		    break;
		case VIEW_CART:
		    System.out.println("Not Implemented");
		    break;
		case CHECKOUT:
		    System.out.println("Not Implemented");
		    break;
		case SEARCH_SONG:
		    System.out.println("Not Implemented");
		    break;
		case LOGOUT:
		    return;
		default:
		    throw new InputMismatchException();
		}
	    }catch (InputMismatchException e) {
		System.out.println("Invalid Choice");
	    }
	    catch(NoSuchElementException e){
		System.out.println("Please enter a number.");
	    }
	}
    }
}
