import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Database {
    Scanner scanner = null;
    
    public Database(){
	
    }


    public ArrayList<Song> loadFromFile(String filename) throws FileNotFoundException{
	// Open the file
	try{}
	scanner = new Scanner(new File(filename));
	// Instantiate the song ArrayList
	ArrayList<Song> songList = new ArrayList<>();
	
	while(scanner.hasNextLine()){
	    scanner.nextLine();
	    
	    
	}
	return songList;
    }
}
