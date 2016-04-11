import java.util.ArrayList;


public class SearchCustomerController {
    ArrayList<Customer> results = null;
    
    public SearchCustomerController(){
	// TODO: Singleton constructor design pattern
    }

    public Customer searchCustomer(){
	SearchCustomerDialog dialog = new SearchCustomerDialog();
	CustomerDB customerDB = CustomerDB.getInstance();
	results = null;
	while(true){
	    String username = dialog.usernamePrompt();
	    if(username==null){
		// the user has elected to quit the search
		return null; 
	    }else if(results==null){
		// this is the first search
		results = customerDB.searchUsername(username);
	    }else{
		// we are narrowing down results of a previous search
		dialog.printMatches(results);
		if(results.size()==1){
		    // only one result left, select it and return it
		    return results.get(0);
		}
	    }
	}
    }

    public void refineResults(String username){
	ArrayList<Customer> temp = new ArrayList<>();
	for(Customer c : results){
	    if(c.getUsername().toLowerCase().contains(username.toLowerCase())){
		temp.add(c);
	    }
	}
	results = temp;
    }
}
