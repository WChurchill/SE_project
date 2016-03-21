import java.util.ArrayList;

import java.io.FileNotFoundException;

class SongDBTester
{
    public static void main(String args[])
    {
	SongDB db = new SongDB();
	ArrayList<Song> songList = new ArrayList<Song>(0);
	try{
	    songList = db.loadFromFile("songs.txt");
	}catch(FileNotFoundException e){
	    System.out.println("ERROR: File not found");
	    e.printStackTrace();
	}
	for (int i = 0; i<songList.size(); i++) {
	    System.out.println(songList.get(i).toLabeledString());
	}
    }
}
