package SE_project;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;

public class SongDB{
    Scanner scanner = null;
    static final int curYear = Calendar.getInstance().get(Calendar.YEAR);       //Get current year from calendar, will be used to verify music creation
    static final int tooOld = curYear - 100;        //static final int tooOld is curYear minus 100 signifies lower boundary of song and album years
    static final int tooNew = curYear + 10;         //static final int tooNew is curYear plus 10 signifies upper boundary of song and album years
    ArrayList<Artist> Artists = new ArrayList <Artist>();
    
    public static void choiceMenu(){    //choiceMenu is a menu that displays 3 specific choices of music classification: Song, Album, and Artist
        System.out.print("0.Exit\n1.Song \n2.Album \n3.Artist\n>>>Enter Choice: ");
    }
    
    public ArrayList<Artist> getArtists(){
        return this.Artists;
    }
    
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
        
    private ArrayList<Album> albums (ArrayList<Song> songList){
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
    
    public void loadArtistsFromFile (String fileName) throws FileNotFoundException{
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
        
	for (int i = 0; i < artistList.size(); i++){
	    for (int j = 0; j < artistList.get(i).getAlbums().size(); j++)
		artistList.get(i).getAlbum(j).getSongs().sort(new SongComparator());
	    artistList.get(i).getAlbums().sort(new AlbumTimeComparator());
	}

    	artistList.sort(new ArtistComparator());
	Artists = artistList;
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
    
    //addSong is a static void taking input and Artists, asks user for fields of data including artist and album, and adds a new song
    //with these specified fields to correct album and artist
    public static void addSong(Scanner input, ArrayList <Artist> Artists){
        Song addSong = null;        //Song addSong initialized to null, will hold the new song to be added to album of artist
        Artist addArtist = null;    //Artist addArtist initialized to null, will hold the artist the song will be added to
        Album addAlbum = null;      //Album addAlbum initialized to null, will hold the album the song will be added to
        boolean newArtist = false;  //boolean newArtist intialized to false, signifies whether or not new Artist was created for added song
        String artist;      //String Artist will hold artist name of new song
        String album;       //String album will hold album name of new song
        int albumYear;      //int albumYear will hold album year of new song
        String name;        //String name will hold name of new song
        int songYear;       //int songYear will hold new song year
        String time;        //String time will hold new song time in string format to be converted later
        
        //Print out formatted list of artists that currently exist
        System.out.println("+-----------+"
                       + "\n|Artist List|" 
                       + "\n+-----------+");
        
        for(Artist art: Artists)
            System.out.println(art.simpArtist());
        
        System.out.println();       //newLine
        
        //Get name of artist for song from user
        input.nextLine();
        System.out.print("Enter Artist name: ");        
        artist = input.nextLine();
        addArtist = new Artist(artist);        //addArtist is equal to new Artist passing String artist
        while (!Artists.contains(addArtist)){       //while Artists does not contained addArtist
            int choice;     //int choice to hold choice of user
            //Tell user artist doesn't exist, give option to create new Artist, exit, or try again with new artist name
            System.out.print("Artist does not exist. Enter 1 to create new Artist for this Song, 0 to exit, any other number to try again: ");      
            choice = input.nextInt();       //take choice from input
            if (choice == 0){       //if choice is zero
                System.out.println("Returning to menu...");     //Tell user returning to menu
                return;     //return
            }
            else if (choice == 1){      //if choice is one
                newArtist = true;       //set newArtist to true
                addArtist(artist, Artists);     //addArtist passing artist and Artists
                System.out.println("Artist created!");      //Success message
                addArtist = searchArtist(artist, Artists);      //set addArtist to this newly created artist
            }
            else{       //else
                input.nextLine();
                System.out.print("Enter Artist for Song: ");        //prompt user to enter artist for song
                artist = input.nextLine();      //take new artist name
                addArtist = new Artist(artist);     //set addArtist to new Artist passing artist String
            }
        }  
        
        addArtist = searchArtist(artist, Artists);
        
        //Print out formatted list of albums that currently exist for selected artist
        System.out.println("+----------+"
                       + "\n|Album List|" 
                       + "\n+----------+");
        System.out.println("|Artist: " + artist);

        for(Album alb: addArtist.getAlbums())
                System.out.println(alb.simpAlbum());

        System.out.println();       //newLine
        
        //Get album name and year from user
        System.out.print("Enter Album name: ");     
        album = input.nextLine();
        System.out.print("Enter Album Year: ");
        albumYear = input.nextInt();
        while (albumYear < tooOld || albumYear > tooNew){       //Make sure that album year isn't too old or too new
            System.out.print("Album year must be between the year " + tooOld + " and the year " + tooNew + ". Try again: ");
            albumYear = input.nextInt();
        }
        
        if (newArtist){     //if artist is a newly created artist   
            addArtist.addAlbum(new Album(album, artist, albumYear));        //add new album with user parameters to this selected artist
        }

        addAlbum = new Album (album, artist, albumYear);        //addAlbum is set to new Album with user parameters

        while (!addArtist.getAlbums().contains(addAlbum)){      //while artist album list doesn't contain specified album
            int twoChoice;      //int twoChoice to hold choice of user
            input.nextLine();
            //Tell user album doesn't exist, give option to create new album, exit, or try again
            System.out.print("Album does not exist. Enter 1 to create new Album for this Song, 0 to exit, any other number to try again: ");
            twoChoice = input.nextInt();        //take choice from user
            if (twoChoice == 0){        //if choice is zero
                System.out.println("Returning to menu...");     //tell user returning to menu
                return;     //return
            }
            else if (twoChoice == 1){       //if choice is one
                addArtist.addAlbum(addAlbum);       //add addAlbum to addArtist
                System.out.println("Album created!");       //Album creation message
                addAlbum = addArtist.getAlbum(album);       //addAlbum is set to this new album
            }       
            else{       //else
                //Try again with new album name
                System.out.print("Enter Album for Song: ");     
                input.nextLine();
                album = input.nextLine();
                addAlbum = new Album (album, artist, albumYear);
            }
        }

        addAlbum = addArtist.getAlbum(album);       //set addAlbum to album returned by addArtist.getAlbum passing album name
        input.nextLine();       

        //Print out formatted list of songs in specified album by specified artist
        System.out.println("+---------+"
                        +"\n|Song list|"
                        +"\n+---------+");
        System.out.println("|Artist: " + artist + "\n|Album: " + addAlbum.simpAlbum());

        for(Song song: addAlbum.getSongs())
            System.out.println(song.simpSong());

        System.out.println();

        //Get song name and year of release from user
        System.out.print("Enter Song name: ");
        name = input.nextLine();
        System.out.print("Enter Year of Release: ");
        songYear = input.nextInt();
        while (songYear < tooOld || songYear > addAlbum.getYear()){     //ensure song year is not too old or newer than album year
            System.out.print("Year of song must be between " + tooOld + " and album release year "  + addAlbum.getYear() + ". Please try again: ");
            songYear = input.nextInt();
        }
        //Get time of song in String format from user
        input.nextLine();
        System.out.print("Enter Time of song (in MM:SS): ");        
        time = input.nextLine();
        int splitIndex = time.indexOf(":");     //Get index of colon character
        assert(time.length()>2);        //assertions to make sure song time isn't invalid
        assert(splitIndex>0);
        assert(splitIndex<time.length()-2);
        int minutes = Integer.parseInt(time.substring(0,splitIndex)); // Parse the minutes
        int seconds = Integer.parseInt(time.substring(splitIndex+1)); // Parse seconds
        seconds+=minutes*60; // convert minutes into seconds and add to seconds

        if (addAlbum.getSong(name) != null){        //if song is returned by addAlbum.getSong passing name of song
            System.out.println("Song already exists. Returning...");        //Tell user song already exists
            return;     //return
        }
        else{       //else
            addAlbum.addSong(new Song (name, album, artist, seconds, songYear));        //add new song with user values to addAlbum
        } 
    }
        
    //addAlbum is a static void taking input and Artists, asks user for fields of data including artist and album name, and adds a new song
    //with these specified fields to correct artist
    public static void addAlbum(Scanner input, ArrayList <Artist> Artists){
        String albumName;       //String albumName holds name of new album
        String artistName;      //String artistName holds name of artist for new album
        int albumYear;          //int albumYear holds year of new album
        Artist artistAdd = null;        //initialize Artist artistAdd to null, this will hold Artist for new album
        Album albumAdd = null;
        
        
        //Print out formatted list of artists in Artists
        System.out.println("+-----------+"
                       + "\n|Artist List|" 
                       + "\n+-----------+");
        
        for(Artist art: Artists)
            System.out.println(art.simpArtist());
        
        System.out.println();
        
        //Take Artist name from user, set artistAdd to new Artist passing artistName
        System.out.print("Enter Artist for New Album: ");
        input.nextLine();
        artistName = input.nextLine();
        artistAdd = new Artist(artistName);
        
        while (!Artists.contains(artistAdd)){       //while Artists doesn't contain this artist
            int choice;     //int choice holds user choice
            //Tell user artist doesn't exist, give option to create new Artist, exit, or try again with new artist name
            System.out.print("Artist does not exist. Enter 1 to create new Artist for this Album, 0 to exit, any other number to try again: ");
            choice = input.nextInt();       //take choice from user
            if (choice == 0){       //if choice is equal to zero
                System.out.println("Returning to menu...");     //tell user returning to menu
                return;     //return 
            }
            else if (choice == 1){      //else if choice is equal to one
                addArtist(artistName, Artists);     //addArtist passing artistName and Artists
                System.out.println("Artist created!");      //Artist creation message
                artistAdd = searchArtist(artistName, Artists);      //artistAdd is equal to artist returned by searchArtist passing artistName and Artists
            }
            else{       //else
                //Try again, restart process with new Artist name from user
                System.out.print("Enter Artist for Album: ");
                input.nextLine();
                artistName = input.nextLine();
                artistAdd = new Artist(artistName);
            }
        }
        
        artistAdd = searchArtist(artistName, Artists);      //set artistAdd to artist returned by searchArtist passing artistName and Artists

        //Print out formatted list of albums that currently exist for selected artist
        System.out.println("+----------+"
                       + "\n|Album List|" 
                       + "\n+----------+");
        System.out.println("|Artist: " + artistName);
        
        for(Album alb: artistAdd.getAlbums())
            System.out.println(alb.simpAlbum());

        System.out.println();       

        //Get album name and year from user
        System.out.print("Enter name of new album: ");
        albumName = input.nextLine();
        System.out.print("Enter year of release of album: ");
        albumYear = input.nextInt();
        albumAdd = new Album (albumName, artistName, albumYear);        //albumAdd equals new Album passing user specified values
        if(artistAdd.getAlbums().contains(albumAdd)){       //if artistAdd already contains this album
            System.out.println("Album already exists. Returning...");       //tell user
            return;     //return
        }
        artistAdd.addAlbum(albumAdd);           //addAlbum to artistAdd
        System.out.println("Album added!");     //success message
    }
    
    //addArtist is a static void taking input and Artists, will create new Artist from new artist name String, and add it to Artists
    //only if it doesn't already exist in the list
    public static void addArtist(String newArtist, ArrayList <Artist> Artists){
        Artist addArtist = null;        //initialize Artist addArtist to null, will hold new artist to be added to Artists
        addArtist = new Artist(newArtist);      //addArtist is new Artist passing name of new artist
              
        if(!Artists.contains(addArtist)) {      //if Artists doesn't contain this artist
            Artists.add(addArtist);     //add addArtist to Artists
            System.out.println("Artist added!");        //success message
        }
        else if (Artists.contains(addArtist)){      //else if Artists contains this artist
            System.out.println("Artist already exists. Returning...");      //Tell user
        }
    }
    
    public static void editSong(Scanner input, ArrayList <Artist> Artists){
        
    }
    
    public static void editAlbum(Scanner input, ArrayList <Artist> Artists){
        String oldAlbumName;
        Album oldAlbumCopy = null;
        Album oldAlbumReal = null;
        Artist oldAlbumArtist = null;
        
        Artist newArtistForAlbum = null;
        String newAlbumName;
        String newArtist;
        int newYear;
        ArrayList <Album> foundAlbums = new ArrayList <Album>();
        
        int albumChoice;
        int keepChoice;
        int albumNum = 0;

        
        System.out.print("Enter name of Album to edit: ");
        input.nextLine();
        oldAlbumName = input.nextLine();
        
        foundAlbums = searchAlbums(oldAlbumName, Artists);
        
        while (foundAlbums.isEmpty()){
            int choice;
            System.out.print("Album not found. Enter 0 to exit, any other number to try again: ");
            choice = input.nextInt();
            if (choice == 0)
                System.out.println("Returning to menu...");
            else{
                System.out.print("Enter name of Album to edit: ");
                input.nextLine();
                oldAlbumName = input.nextLine();
                foundAlbums = searchAlbums(oldAlbumName, Artists);
            }
        }
        
        for(int i = 0; i < foundAlbums.size(); i++){
            albumNum++;
            System.out.println("(" + i + ") " + foundAlbums.get(i).simpAlbum());
        }
        
        System.out.print("Enter number of album to edit, or enter -1 to exit: ");
        albumChoice = input.nextInt();
        while(albumChoice < -1 || albumChoice > albumNum){
            System.out.print("Invalid. Enter number of album to edit, or enter -1 to exit");
            albumChoice = input.nextInt();
        }
        if (albumChoice == -1)
            return;
        else{
            oldAlbumCopy = foundAlbums.get(albumChoice);
            oldAlbumArtist = searchArtist(oldAlbumCopy.getArtist(), Artists);
            oldAlbumReal = oldAlbumArtist.getAlbum(oldAlbumName);
            System.out.print("Enter 1 to keep Album name, any other number to edit: ");
            input.nextLine();
            keepChoice = input.nextInt();
            if (keepChoice == 1){
                newAlbumName = oldAlbumCopy.getName();
            }
            else{
                System.out.print("Enter new Album name: ");
                input.nextLine();
                newAlbumName = input.nextLine();
            }
            System.out.print("Enter 1 to keep Artist, any other number to edit: ");
            input.nextLine();
            keepChoice = input.nextInt();
            if (keepChoice == 1){
                newArtist = oldAlbumCopy.getArtist();
                newArtistForAlbum = searchArtist(newArtist, Artists);
            }
            else{
                System.out.print("Enter new Artist for Album: ");
                input.nextLine();
                newArtist = input.nextLine();
                newArtistForAlbum = new Artist(newArtist);
                while(!Artists.contains(newArtistForAlbum)){
                    int choice;
                    System.out.print("Artist does not exist. Enter 1 to create new Artist for this album, 0 to exit, any other number to try again: ");
                    input.nextLine();
                    choice = input.nextInt();
                    if (choice == 0){
                        System.out.println("Returning to menu...");
                        return;
                    }
                    else if (choice == 1){
                        addArtist(newArtist, Artists);
                        System.out.println("Artist created!");
                        newArtistForAlbum = searchArtist(newArtist, Artists);
                    }
                    else{
                        System.out.print("Enter new Artist for Album: ");
                        input.nextLine();
                        newArtist = input.nextLine();
                        newArtistForAlbum = new Artist(newArtist);
                    }
                }
                newArtistForAlbum = searchArtist(newArtist, Artists);
            }
            System.out.print("Enter 1 to keep Year of Release, any other number to edit: ");
            keepChoice = input.nextInt();
            if (keepChoice == 1){
                newYear = oldAlbumCopy.getYear();
            }
            else{
                System.out.print("Enter new Year of Release: ");
                newYear = input.nextInt(); 
                while (newYear < tooOld || newYear > tooNew){
                    System.out.print("Album year must be between the year " + tooOld + " and the year " + tooNew + ". Try again: ");
                    newYear = input.nextInt();
                }
            }
            System.out.print("Enter 0 to CLEAR Songs, any other number to keep songs: ");
            keepChoice = input.nextInt();
            if(keepChoice == 0){
                oldAlbumCopy.clearSongs();
            }
            oldAlbumCopy.changeName(newAlbumName);
            oldAlbumCopy.changeArtist(newArtist);
            oldAlbumCopy.changeYear(newYear);
            newArtistForAlbum.addAlbum(oldAlbumCopy);
            oldAlbumArtist.getAlbums().remove(oldAlbumReal);
            System.out.println("Edit success!");
        }        
    }
    
    public static void editArtist(Scanner input, ArrayList <Artist> Artists){
        String oldArtist;
        String newArtist;
        Artist oldArtistObj = null;
        
        System.out.println("+-----------+"
                       + "\n|Artist List|" 
                       + "\n+-----------+");
        for(Artist art: Artists)
            System.out.println(art.simpArtist());
        
        System.out.println();
        
        System.out.print("Enter name of Artist to edit: ");
        input.nextLine();
        oldArtist = input.nextLine();
        oldArtistObj = new Artist(oldArtist);
        
        while(!Artists.contains(oldArtistObj)){
            int choice;
            System.out.print("Artist does not exist. Enter 0 to exit, any other number to try again: ");
            choice = input.nextInt();
            if (choice == 0){
                System.out.println("Returning to menu...");
                return;
            }
            else{
                System.out.print("Enter name of Artist to edit: ");
                oldArtist = input.nextLine();
                oldArtistObj = new Artist(oldArtist);
            }
        }
        
        oldArtistObj = searchArtist(oldArtist, Artists);
        System.out.print("Enter new name for " + oldArtist + ": ");
        newArtist = input.nextLine();
        oldArtistObj.changeArtist(newArtist);
        System.out.println("Artist name changed!");
    }
    
    public static void deleteSong(Song songDelete, ArrayList <Artist> Artists){
        String artistName = songDelete.getArtist();
        String albumName = songDelete.getAlbum();
        String songName = songDelete.getName();
        Artist songArtist = null;
        Album songAlbum = null;
        songArtist = searchArtist(artistName, Artists);
        songAlbum = songArtist.getAlbum(albumName);
        songAlbum.deleteSong(songName);
    }
    
    public static void deleteAlbum(Album albumDelete, ArrayList <Artist> Artists){
        String artistName = albumDelete.getArtist();
        String albumName = albumDelete.getName();
        Artist albumArtist = null;
        albumArtist = searchArtist(artistName, Artists);
        albumArtist.deleteAlbum(albumName);
    }
    
    public static void deleteArtist(Artist artistDelete, ArrayList  <Artist> Artists){
        Artists.remove(artistDelete);
    }
    
    public static ArrayList <Song> searchSongs(String songSearch, ArrayList<Artist> Artists){
        songSearch = songSearch.replaceAll("\\s+", "");
        Artists.sort(new ArtistComparator());
        ArrayList<Album> Albums = new ArrayList<Album>();
        ArrayList<Song> Songs = new ArrayList<Song>();
        ArrayList<Song> foundSongs = new ArrayList<Song>();
        
        for (int i = 0; i < Artists.size(); i++){
            for(int j = 0; j < Artists.get(i).getAlbums().size(); j++)
                Albums.add(Artists.get(i).getAlbum(j));
        }
        Albums.sort(new AlbumComparator());
        for (int k = 0; k < Albums.size(); k++){
            for (int l = 0; l < Albums.get(k).getSongs().size(); l++)
                Songs.add(Albums.get(k).getSong(l));
        }
        Songs.sort(new SongComparator());
        int first = firstSong(Songs, songSearch);
        int last = lastSong(Songs, songSearch);
        
        if(first == -1){
            return foundSongs;			
        }
        else{
            for(int i = first; i <= last; i++){
                foundSongs.add(Songs.get(i));
            }
        }
        return foundSongs;
    }
    
    public static ArrayList<Album> searchAlbums(String albumSearch, ArrayList<Artist> Artists){
        albumSearch = albumSearch.replaceAll("\\s+", "");
        Artists.sort(new ArtistComparator());
        ArrayList<Album> Albums = new ArrayList<Album>();
        ArrayList<Album> foundAlbums = new ArrayList<Album>();
        for (int i = 0; i < Artists.size(); i++){
            for(int j = 0; j < Artists.get(i).getAlbums().size(); j++)
                Albums.add(Artists.get(i).getAlbum(j));
        }
        Albums.sort(new AlbumComparator());
        int first = firstAlbum(Albums, albumSearch);
        int last = lastAlbum(Albums, albumSearch);
        
        if(first == -1){
            return foundAlbums;			
        }
        else{
            for(int i = first; i <= last; i++){
                foundAlbums.add(Albums.get(i));
            }
        }
        return foundAlbums;
    }
    
    public static Artist searchArtist (String artistSearch, ArrayList<Artist> Artists){
        Artists.sort(new ArtistComparator());
        artistSearch = artistSearch.replaceAll("\\s+", "");
        int low = 0;        //initialize low to 0
        int high = Artists.size()-1;  //initialize high to size of list minus 1
        while (low <= high){        //while low is less than or equal to high
            int mid = low + (high-low)/2;       //middle is high minus low divided by 2
            if (artistSearch.compareTo(Artists.get(mid).getName().replaceAll("\\s+", "")) < 0)     //if songSearch is less than tested mid name
                high = mid - 1;     //high is middle minus 1
            else if (artistSearch.compareTo(Artists.get(mid).getName().replaceAll("\\s+", "")) > 0)    //if songSearch is higher than tested mid name
                low = mid + 1;      //low is middle plus 1
            else if (artistSearch.compareTo(Artists.get(mid).getName().replaceAll("\\s+", "")) == 0){  //else if equal
                //System.out.println("Song found!");      //Success message
                return Artists.get(mid);      //return Song
            }
        }
        //System.out.println("Song not found");       //Failure message
        return null;        //return null
    }
    
    public static int firstSong(ArrayList<Song> songs, String songSearch){
                
        //songSearch = songSearch.replaceAll("\\s+", "");
        
        int l = 0, r = songs.size()-1, result = -1;		
        while(l <= r){	
            int mid = (l+r)/2; 	
            String albumFound = songs.get(mid).getName().replaceAll("\\s+", "");

            if(songSearch.compareTo(albumFound) < 0){
                    r = mid-1;

            }else if(songSearch.compareTo(albumFound) > 0){
                    l = mid+1;

            }else{ 
                    result = mid;
                    r = mid-1;
            }
        }
        return result;
    }
    
    public static int lastSong(ArrayList<Song> songs, String songSearch){
 
        //songSearch = songSearch.replaceAll("\\s+", "");

        int l = 0, r = songs.size()-1, result = -1;		
        while(l <= r){			
            int mid = (l+r)/2; 	
            String albumFound = songs.get(mid).getName().replaceAll("\\s+", "");
            
            if(songSearch.compareTo(albumFound) < 0){
                    r = mid-1;

            }else if(songSearch.compareTo(albumFound) > 0){
                    l = mid+1;

            }else { 
                    result = mid;
                    l = mid+1;
            }
        }
        return result;
    }
    
    public static int firstAlbum(ArrayList<Album> albums, String albumSearch){
                
        albumSearch = albumSearch.replaceAll("\\s+", "");
        
        int l = 0, r = albums.size()-1, result = -1;		
        while(l <= r){	
            int mid = (l+r)/2; 	
            String albumFound = albums.get(mid).getName().replaceAll("\\s+", "");

            if(albumSearch.compareTo(albumFound) < 0){
                    r = mid-1;

            }else if(albumSearch.compareTo(albumFound) > 0){
                    l = mid+1;

            }else{ 
                    result = mid;
                    r = mid-1;
            }
        }
        return result;
    }
    
    public static int lastAlbum(ArrayList<Album> albums, String albumSearch){
 
        albumSearch = albumSearch.replaceAll("\\s+", "");

        int l = 0, r = albums.size()-1, result = -1;		
        while(l <= r){			
            int mid = (l+r)/2; 	
            String albumFound = albums.get(mid).getName().replaceAll("\\s+", "");
            
            if(albumSearch.compareTo(albumFound) < 0){
                    r = mid-1;

            }else if(albumSearch.compareTo(albumFound) > 0){
                    l = mid+1;

            }else { 
                    result = mid;
                    l = mid+1;
            }
        }
        return result;
    }
}
