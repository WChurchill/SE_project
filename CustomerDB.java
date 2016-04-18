import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileWriter;
import java.io.File;

import java.util.ArrayList;
import java.util.Scanner;

public class CustomerDB {
    static final String customerFile = "customers.txt";
    private ArrayList<User> users = null;
    private static CustomerDB instance;

    public static CustomerDB getInstance(){
	if(instance==null) instance = new CustomerDB();
	return instance;
    }
    
    private CustomerDB() {
	
    }
    
    public void loadFromFile() throws FileNotFoundException{
	// Open the file
	Scanner	scanner = new Scanner(new File(customerFile));
	
	// Instantiate the song ArrayList
	ArrayList<User> customerList = new ArrayList<>();

	// Loop over every 5-line song entry in the file
	while(scanner.hasNextLine()){
	    String username= scanner.nextLine(); // Parse the usermane
	    System.out.println("username: "+username);
	    String password = scanner.nextLine(); // Parse the password
	    System.out.println("password: "+password);
	    // parse the history
	    int numCarts = scanner.nextInt();
	    System.out.println("num carts: "+numCarts);
	    scanner.nextLine();

	    ArrayList<ShoppingCart> cartsList = new ArrayList<>();
	    for(int i = 0; i<numCarts; i++){
		// parse each shopping cart
		int numSongs = scanner.nextInt();
		System.out.println("num songs: "+numSongs);

		ShoppingCart cart = new ShoppingCart();
		for(int j = 0; j< numSongs; j++){
		    String songName = scanner.nextLine(); // Parse the name
		    String artist = scanner.nextLine(); // Parse the artist
		    String album = scanner.nextLine(); // Parse the album
		    // Time is stored as MM:SS
		    String line = scanner.nextLine();
		    int splitIndex = line.indexOf(":");
		    assert(line.length()>2);
		    assert(splitIndex>0);
		    assert(splitIndex<line.length()-2);
		    int minutes = Integer.parseInt(line.substring(0,splitIndex)); // Parse the minutes
		    int seconds = Integer.parseInt(line.substring(splitIndex+1)); // Parse seconds
		    seconds+=minutes*60; // convert minutes into seconds and add to seconds
	    
		    int year = scanner.nextInt();// Parse year YYYY
		    double price = scanner.nextDouble();
		    cart.add(new Song(songName, artist, album, seconds, year, price));
		}
		cartsList.add(cart);
	    }
	    customerList.add(new Customer(username, password, cartsList));
	}

	scanner.close(); // release the file
	customerList.add((User)Admin.getInstance());
	users = customerList;
    }

    public void saveToFile() throws IOException
    {
    	FileWriter save = new FileWriter(customerFile);
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

