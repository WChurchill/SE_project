
import java.util.ArrayList;

import java.io.FileNotFoundException;
import java.io.IOException;

class SongDBTester
{
    public static void main(String args[])
    {
	SongDB db =  SongDB.getInstance();
	ArrayList<Song> songList = new ArrayList<Song>(0);
	try{
	    db.loadArtistsFromFile();
	}catch(FileNotFoundException e){
	    System.out.println("ERROR: File not found");
	    e.printStackTrace();
	}
	try{
	    db.saveToFile();
	}catch(IOException e){
	    e.printStackTrace();
	}
    }
}
