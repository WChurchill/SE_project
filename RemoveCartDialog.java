import java.util.Scanner;

public class RemoveCartDialog {
    private static RemoveCartDialog instance;
    
    public static RemoveCartDialog getInstance(){
	if(instance==null) instance = new RemoveCartDialog();
	return instance;
    }

    private RemoveCartDialog(){
	
    }
    
    public void remove(Customer customer){
	Scanner input = new Scanner(System.in);
	ShoppingCart cart = customer.cart;
	
	System.out.print("Enter a name: ");
	String query = input.nextLine().trim().toLowerCase();
	int num = cart.remove(query);
	
	System.out.print(num+" song");
	if(num!=1) System.out.print("s");
	System.out.println(" removed from the cart.");
    }
}
