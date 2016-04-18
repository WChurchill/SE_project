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
	Scanner in = new Scanner(System.in);
	//customer.cart
	System.out.println("not implemented :(");
	//System.out.println("Enter a song name to remove: ");
	//String response = in.nextLine();
    }
}
