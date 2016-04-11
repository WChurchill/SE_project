import java.util.Scanner;
import java.util.ArrayList;

public class SearchCustomerDialog {
    
    public SearchCustomerDialog(){
	// TODO: Singleton constructor design pattern
    }
    
    public String usernamePrompt(){
	Scanner inputReader = new Scanner(System.in);
	
	System.out.print(">>> Enter a username (leave empty to quit): ");
	
	String response = inputReader.nextLine().trim();

	return response.equals("") ? null : response;
    }

    public void printMatches(ArrayList<Customer> customerList){
	if(customerList.isEmpty()){
	    System.out.println("No results found");
	}else{
	    for(int index = 0; index < customerList.size(); index++){
		System.out.println(customerList.get(index));
	    }
	}
    }
}
