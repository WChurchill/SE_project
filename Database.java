import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class Database {
    Scanner scanner = null;
    
    public Database(){
	
    }


    public ArrayList<Song> loadFromFile(String filename) throws FileNotFoundException{
	// Open the file
	scanner = new Scanner(new File(filename));
	
	// Instantiate the song ArrayList
	ArrayList<Song> songList = new ArrayList<>();

	// Loop over every 5-line song entry in the file
	while(scanner.hasNextLine()){
	    String songName = scanner.nextLine(); // Parse the name
	    String artist = scanner.nextLine(); // Parse the artist
	    String album = scanner.nextLine(); // Parse the album
	    // Time is stored as MM:SS
	    int minutes = scanner.nextInt(); // Parse the minutes
	    int seconds = scanner.nextInt(); // Parse seconds
	    seconds+=minutes*60; // convert minutes into seconds and add to seconds
	    int year = scanner.nextInt();// Parse year YYYY

	    songList.add(new Song(songName, artist, album, seconds, year));
	}
	
	scanner.close(); // release the file
	return songList;
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