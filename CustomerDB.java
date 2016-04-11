import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileWriter;
import java.io.File;

import java.util.ArrayList;
import java.util.Scanner;

public class CustomerDB {
    private ArrayList<User> users = null;
    private static CustomerDB instance;

    public static CustomerDB getInstance(){
	if(instance==null) instance = new CustomerDB();
	return instance;
    }
    
    private CustomerDB() {
	
    }
    
    public void loadFromFile(String filename) throws FileNotFoundException{
	// Open the file
	Scanner	scanner = new Scanner(new File(filename));
	
	// Instantiate the song ArrayList
	ArrayList<User> customerList = new ArrayList<>();

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
	customerList.add((User)Admin.getInstance());
	users = customerList;
    }

    public void saveToFile(String filename) throws IOException
    {
    	FileWriter save = new FileWriter(filename);
    	// Loop through the list and print each element to file
    	for(int i =0; i<users.size(); i++) {
	    User user = users.get(i);
	    if(!(user instanceof Admin))
		save.write(user.toString());
    	}
    	save.close();//close the file
    }

    public ArrayList<Customer> searchUsername(String name){
	ArrayList<Customer> results = new ArrayList<>();
	for(User c : users){
	    if(c.getUsername().toLowerCase().contains(name.toLowerCase())){
		results.add((Customer)c);
	    }
	}
	return results;
    }
    
    //return specific customer
    public User getUser(String name){
	//find customer in database based on username
	for(User user : users){
	    if(user.getUsername().toLowerCase().equals(name.toLowerCase())){
		return user; 
	    }
	}
        //if customer not found 
	return null;
    }

    public void addUser(User user){
	users.add(user);
    }
    
}

