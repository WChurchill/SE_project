import java.util.Scanner;
import java.util.NoSuchElementException;
import java.util.InputMismatchException;

public class CustomerDialog {
    private final int ADD_CART = 1;
    private final int REMOVE_CART = 2;
    private final int SEARCH_SONG = 3;
    private final int VIEW_CART = 4;
    private final int VIEW_HISTORY = 5;
    private final int CHECKOUT = 6;
    private final int LOGOUT = 7;

    private static CustomerDialog instance;
    public static CustomerDialog getInstance(){
	if(instance==null) instance = new CustomerDialog();
	return instance;
    }

    private CustomerDialog(){
	
    }

    private void printMenu(){
	System.out.println(ADD_CART+") Add Song to Cart");
	System.out.println(REMOVE_CART+") Remove from Cart");
	System.out.println(SEARCH_SONG+") Search Songs");
	System.out.println(VIEW_CART+") View Cart");
	System.out.println(VIEW_HISTORY+") View Purchase History");
	System.out.println(CHECKOUT+") Checkout");
	System.out.println(LOGOUT+") Logout");
	
	System.out.print(">>>Enter Choice: ");      //Prompt user for choice
    }
    
    public void mainLoop(Customer customer){
	Scanner input = new Scanner(System.in);

	AddCartDialog addCartDialog = AddCartDialog.getInstance();
	RemoveCartDialog removeCartDialog = RemoveCartDialog.getInstance();
	CheckoutDialog checkoutDialog = CheckoutDialog.getInstance();
	SearchController searchController = SearchController.getInstance();
	// RemoveCartDialog removeCartDialog = RemoveCartDialog.getInstance();
	// SearchDialog searchDialog = SearchDialog.getInstance();
	// ViewCartDialog viewCartDialog = ViewCartDialog.getInstance();
	// AddCartDialog addCartDialog = AddCartDialog.getInstance();
	while(true){         //Display main menu and take choice
	    printMenu();         //Display main menu
	    try{
		int choice = input.nextInt();       //Take as choice
		switch (choice) {       //switch case to process main menu choice
		case ADD_CART:
		    addCartDialog.add(customer);
		    break;
		case REMOVE_CART:
		    removeCartDialog.remove(customer);
		    break;
		case VIEW_CART:
		    System.out.println(customer.cart.prettyString());
		    break;
		case CHECKOUT:
		    checkoutDialog.checkout(customer);
		    break;
		case SEARCH_SONG:
		    searchController.search();
		    break;
		case VIEW_HISTORY:
		    customer.printPurchases();
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
	    finally{
		input.nextLine();
	    }
	}
    }
}
