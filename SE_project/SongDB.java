package SE_project;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class SongDB {
    Scanner scanner = null;
    
    public SongDB(){
	
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
	    String line = scanner.nextLine();
	    int splitIndex = line.indexOf(":");
	    assert(line.length()>2);
	    assert(splitIndex>0);
	    assert(splitIndex<line.length()-2);
	    int minutes = Integer.parseInt(line.substring(0,splitIndex)); // Parse the minutes
	    int seconds = Integer.parseInt(line.substring(splitIndex+1)); // Parse seconds
	    seconds+=minutes*60; // convert minutes into seconds and add to seconds
	    
	    int year = scanner.nextInt();// Parse year YYYY
	    scanner.nextLine();// go to the next line
	    songList.add(new Song(songName, artist, album, seconds, year));
	}
	
	scanner.close(); // release the file
	return songList;
    }
        
    public ArrayList<Album> albums (ArrayList<Song> songList){
        ArrayList<Album> albums = new ArrayList<Album>();
        //ArrayList<Artist> artistList = new ArrayList<Artist>();
        Album seek = null;
        
        for(int i = 0; i < songList.size(); i++){
            seek = new Album(songList.get(i).getAlbum(), songList.get(i).getArtist(), songList.get(i).getYear());
            if (!albums.contains(seek)){
                //System.out.println("New album");
                albums.add(seek);
                seek.addSong(songList.get(i));
                //System.out.println("New Album");
            }
            else if (albums.contains(seek)){
                //System.out.println("Existing album");
                for (int j = 0; j < albums.size(); j++){
                    if(albums.get(j).getName().equals(songList.get(i).getAlbum())){
                        seek = albums.get(j);
                        break;
                    }
                }
                seek.addSong(songList.get(i));
                //System.out.println("Already in list");
            }
        }
        return albums;
    }
    
    public ArrayList<Artist> artists (String fileName){
        try{
            ArrayList<Song> songList = loadFromFile(fileName);
            ArrayList<Album> albumList = albums(songList);
            ArrayList<Artist> artistList = new ArrayList<Artist>();
           
            Artist seek = null;

            for(int i = 0; i < albumList.size(); i++){
                seek = new Artist(albumList.get(i).getArtist());
                if(!artistList.contains(seek)){
                    //System.out.println("New artist");
                    artistList.add(seek);
                    seek.addAlbum(albumList.get(i));
                }
                else if(artistList.contains(seek)){
                    //System.out.println("Existing Artist");
                    for (int j = 0; j < artistList.size(); j++){
                        if(artistList.get(j).getName().equals(albumList.get(i).getArtist())){
                            seek = artistList.get(j);
                            break;
                        }
                    }
                    seek.addAlbum(albumList.get(i));
                }
            }
        return artistList;
        } catch(FileNotFoundException e){
            System.out.println("Error: File does not exist. Exiting...");
            System.exit(0);
        }
        return null;
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
