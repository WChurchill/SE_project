package SE_project;

import java.util.Scanner;
import java.util.ArrayList;

public class SearchCustomerDialog {
    
    
    public SearchCustomerDialog(){
	
    }

    public void noResults(){
	System.out.println("No results found");
    }
    
    
    public String usernamePrompt(){
	Scanner inputReader = new Scanner(System.in);
	
	System.out.print(">>> Enter a username (leave empty to quit): ");
	
	String response = inputReader.nextLine();

	return response.equals("") ? null : response;
    }

    public void printMatches(ArrayList<Customer> customerList){
	for(int index = 0; index < customerList.size(); index++){
	    System.out.println(customerList.get(index));
	}
    }

    // returns true if user wants to search again, false to quit
    public boolean newSearchPrompt(){
	Scanner inReader = new Scanner(System.in);
	String response = "";
	while(true){
	    System.out.print("Start new search? [y/n]:");
	    response = inReader.next();
	    if(response.equalsIgnoreCase("y")){
		return true;
	    }else if( response.equalsIgnoreCase("n")){
		return false;
	    }else{
		System.out.println("Please enter y or n");

	    }
	}
    }

    
    
}
