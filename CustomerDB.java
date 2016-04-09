import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileWriter;
import java.io.File;

import java.util.ArrayList;
import java.util.Scanner;

public class CustomerDB {
    private ArrayList<Customer> customers = null;

    public CustomerDB() {
	// TODO: Singleton design pattern so we don't get multiple copies of this database
    }
    
    public void loadFromFile(String filename) throws FileNotFoundException{
	// Open the file
	Scanner	scanner = new Scanner(new File(filename));
	
	// Instantiate the song ArrayList
	ArrayList<Customer> customerList = new ArrayList<>();

	// Loop over every 5-line song entry in the file
	while(scanner.hasNextLine()){
	    String username= scanner.nextLine(); // Parse the name
	    String password = scanner.nextLine(); // Parse the artist

	    // parse the history
	    int numCarts = scanner.nextInt();
	    for(int i = 0; i<numCarts; i++){
		// TODO: parse each shopping cart
	    }
	    
	    customerList.add(new Customer(username, password));
	}

	scanner.close(); // release the file
	customers = customerList;
    }

    public void saveToFile(String filename) throws IOException
    {
    	FileWriter save = new FileWriter(filename);
    	// Loop through the list and print each element to file
    	for(int i =0; i<customers.size(); i++) {
	    save.write(customers.get(i).toString());
    	}
    	save.close();//close the file
    }

    public ArrayList<Customer> searchUsername(String name){
	ArrayList<Customer> results = new ArrayList<>();
	for(Customer c : customers){
	    if(c.getUsername().toLowerCase().contains(name.toLowerCase())){
		results.add(c);
	    }
	}
	return results;
    }
    
    //return specific customer
    public Customer getCustomer(String name){
	//find customer in database based on username
	for(Customer c : customers){
	    if(c.getUsername().toLowerCase().equals(name.toLowerCase())){
		return c; 
	    }
	}
        //if customer not found 
	return null;
    }
    
}

