import java.util.Scanner;

public class CheckoutDialog {
    private static CheckoutDialog instance;
    
    public static CheckoutDialog getInstance(){
	if(instance==null) instance = new CheckoutDialog();
	return instance;
    }
    
    private CheckoutDialog(){
	
    }

    public void checkout(Customer customer){
	CheckoutController controller = CheckoutController.getInstance();
	Scanner in = new Scanner(System.in);
	System.out.println(customer.cart);

	String response = null;
	do{
	    System.out.println("Purchase Songs?: [y/n] ");
	    response = in.nextLine().trim().toLowerCase();
	}while(!response.equals("y") && !response.equals("n"));
	if(response.equals("n")) return;
	else{
	    customer.addPurchase();
	}

    }

    
}
