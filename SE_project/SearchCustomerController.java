
import java.util.ArrayList;


public class SearchCustomerController {
    ArrayList<Customer> results = null;
    
    public SearchCustomerController(){
	
    }

    public Customer searchCustomer(){
	SearchCustomerDialog dialog = new SearchCustomerDialog();
	CustomerDB customerDB = new CustomerDB();
	
	
	while(true){
	    String username = dialog.usernamePrompt();
	    if(username==null){
		return null; // the user has elected to quit the search
	    }
	    if(results==null){// if this is the first search
		results = customerDB.searchUsername(username);
	    }else{// if we are narrowing down results of a previous search
		if(results.isEmpty()){
		    dialog.noResults();
		    
		}
	    }
	}
    }

    public void Search(String username){
	
    }
}
