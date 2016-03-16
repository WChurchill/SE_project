import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.File;

public class CustomerDB {

    public ArrayList<Song> loadFromFile(String filename) throws FileNotFoundException{
	// Open the file
	Scanner scanner = new Scanner(new File(filename));
	
	// Instantiate the song ArrayList
	ArrayList<Customer> customerList = new ArrayList<>();

	// Loop over every 5-line song entry in the file
	while(scanner.hasNextLine()){
	    String userName = scanner.nextLine(); // Parse the username
	    String password = scanner.nextLine(); // Parse the password
	    // TODO: parse shopping history
	}
	
	scanner.close(); // release the file
	return customerList;
    }
    
    public void saveToFile(String filename, ArrayList<Song> songList) throws IOException
    {
    	FileWriter save = new FileWriter(filename);
    	// Loop through the list and print each element to file
    	for(int i =0; i<songList.size(); i++)
	    {
    		save.write(songList.get(i).toString());
	    }
    	save.close();//close the file
    }

}
