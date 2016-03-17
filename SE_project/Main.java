package SE_project;

/**
 *
 * @author Gabriel
 */
import java.util.*;
import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) {
        
        ArrayList <Artist> Artists = new ArrayList <Artist>();
        
        SongDB main = new SongDB();
        Artists = main.artists("songs.txt");

        Artists.sort(new ArtistComparator());

        /*for(int g = 0; g < Albums.size(); g++)
            System.out.println(Albums.get(g).toString());
        
        System.out.println();
        for(int h = 0; h < Songs.size(); h++)
            System.out.println(Songs.get(h).toString());
        */
        System.out.println();
        /*for(int i = 0; i < Artists.size(); i++){
                System.out.println(Artists.get(i).toString());
                for(int j = 0; j <Artists.get(i).getAlbums().size(); j++)
                    System.out.println(Artists.get(i).getAlbum(j).toString());
        }*/

        Scanner input = new Scanner(System.in);     //input scanner
        int choice = 0;     //int for main menu choice
        int subChoice;      //int for sub-menu choice
        Logo();     //Display Logo
        do{         //Display main menu and take choice
            mainMenu();         //Display main menu
            System.out.print("                            >>>Enter Choice: ");      //Prompt user for choice
            choice = input.nextInt();       //Take as choice
            while (choice < 1 || choice > 6){   //while choice is not between 1-6 inclusive, tell user invalid and take new choice
                System.out.print("                            >>>Invalid. Enter Choice 1-6: ");
                choice = input.nextInt();
            }

            switch (choice) {       //switch case to process main menu choice
            case 1:
                choiceMenu();       //Display choice menu
                subChoice = input.nextInt();        //Take sub-choice
                while (subChoice < 1 || subChoice > 3){     //validate sub-choice between 1-3 inclusive
                    System.out.print("\n>>>Invalid. Enter Choice 1-3: ");
                    subChoice = input.nextInt();
                }
                if (subChoice == 1) {   //if and else if cases based on subChoice to run one of 3 choices
                    addController(0, Artists);
                }
                else if (subChoice ==2){
                    addController(1, Artists);
                }
                else if (subChoice ==3){
                    addController(2, Artists);                   
                }
                break;
            case 2:
                choiceMenu();       //Display choice menu
                subChoice = input.nextInt();        //Take sub-choice
                while (subChoice < 1 || subChoice > 3){     //validate sub-choice between 1-3 inclusive
                    System.out.print("\n>>>Invalid. Enter Choice 1-3: ");
                    subChoice = input.nextInt();
                }
                if (subChoice == 1) {   //if and else if cases based on subChoice to run one of 3 choices
                    System.out.println("Not Implemented");                   
                } 
                else if (subChoice ==2){
                    System.out.println("Not Implemented");
                }
                else if (subChoice ==3){
                    System.out.println("Not Implemented");
                }
                break;
            case 3:
                choiceMenu();       //Display choice menu
                subChoice = input.nextInt();        //Take sub-choice
                while (subChoice < 1 || subChoice > 3){     //validate sub-choice between 1-3 inclusive
                    System.out.print("\n>>>Invalid. Enter Choice 1-3: ");
                    subChoice = input.nextInt();
                }
                if (subChoice == 1) {   //if and else if cases based on subChoice to run one of 3 choices
                    System.out.println("Not Implemented");
                }
                else if (subChoice ==2){
                    System.out.println("Not Implemented");
                }
                else if (subChoice ==3){
                    System.out.println("Not Implemented");
                }
                break;
            case 4:
                choiceMenu();       //Display choice menu
                subChoice = input.nextInt();        //Take sub-choice
                while (subChoice < 1 || subChoice > 3){     //validate sub-choice between 1-3 inclusive
                    System.out.print("\n>>>Invalid. Enter Choice 1-3: ");
                    subChoice = input.nextInt();
                }
                if (subChoice == 1) {   //if choice is 1, songSearch
                    String songSearch;  //String to hold song requested
                    input.nextLine();   //Clear buffer
                    System.out.print("Enter name of song: ");   //Prompt user for requested song
                    songSearch = input.nextLine();      //Take result
                    ArrayList <Song> songsFound = searchSongs(songSearch, Artists);
                    if (songsFound.isEmpty())
                        System.out.println("No songs found...");
                    else{
                        for (int i = 0; i < songsFound.size(); i++)
                            System.out.println(songsFound.get(i).toLabeledString());
                    }
                }
                else if (subChoice ==2){
                    String albumSearch;  //String to hold song requested
                    input.nextLine();   //Clear buffer
                    System.out.print("Enter name of album: ");   //Prompt user for requested song
                    albumSearch = input.nextLine();      //Take result
                    ArrayList <Album> albumsFound = searchAlbums(albumSearch, Artists);
                    if (albumsFound.isEmpty())
                        System.out.println("No albums found...");
                    else{
                        for(int i = 0; i < albumsFound.size(); i++)
                            System.out.println(albumsFound.get(i).toString());
                    }
                }
                else if (subChoice ==3){
                    String artistSearch;  //String to hold song requested
                    input.nextLine();   //Clear buffer
                    System.out.print("Enter name of artist: ");   //Prompt user for requested song
                    artistSearch = input.nextLine();      //Take result
                    Artist result = searchArtist(artistSearch, Artists);        //Run searchSong passing songSearch and Songs ArrayList
                    if(result==null){
                        System.out.println("Artist not found...");
                    }else{
                        System.out.println(result.toString());
                        System.out.println(result.albumsView());
                    }
                }
                break;
            case 5:
                System.out.println("Not Implemented");
                break;
            case 6:
                System.exit(0);     //exit program
                break;
            }                     
        } while(choice != 0);       //while choice is not to exit    
    }
    
    public static void Logo(){      //Logo is an ASCII art version of the title- Massive Music Megastore
        System.out.println(
            "+-----------------------------------------------------------------------------------------------------------------+\n" +
            "|  __  __               _             __  __           _        __  __                      _                     |\n" +
            "| |  \\/  | __ _ ___ ___(_)_   _____  |  \\/  |_   _ ___(_) ___  |  \\/  | ___  __ _  __ _ ___| |_ ___  _ __ ___     |\n" +
            "| | |\\/| |/ _` / __/ __| \\ \\ / / _ \\ | |\\/| | | | / __| |/ __| | |\\/| |/ _ \\/ _` |/ _` / __| __/ _ \\| '__/ _ \\    |\n" +
            "| | |  | | (_| \\__ \\__ \\ |\\ V /  __/ | |  | | |_| \\__ \\ | (__  | |  | |  __/ (_| | (_| \\__ \\ || (_) | | |  __/    |\n" +
            "| |_|  |_|\\__,_|___/___/_| \\_/ \\___| |_|  |_|\\__,_|___/_|\\___| |_|  |_|\\___|\\__, |\\__,_|___/\\__\\___/|_|  \\___|    |\n" +
            "|                                                                           |___/                                 |\n" +
            "+-----------------------------------------------------------------------------------------------------------------+");
           }
    
    public static void mainMenu(){      //mainMenu is a menu that displays 6 different choices of operation: Add, Edit, Delete, Search, Purchase, and Exit
        System.out.println(
            "+------------[1. Add]------[2. Edit]------[3. Delete]------[4. Search]------[5. Purchase]------[6. Exit]----------+");
    }
    
    public static void choiceMenu(){    //choiceMenu is a menu that displays 3 specific choices of music classification: Song, Album, and Artist
        System.out.print(
            "1.Song \n2.Album \n3.Artist\n>>>Enter Choice: ");
    }
    
    public static void searchOptionMenu(){      //searchOptionMenu is a menu that displays 2 choices of search criteria: Name and Year
        System.out.print("1.By Name \n2.By Year\n>>>Enter Choice: ");
    }
    
    public static void addController(int choice, ArrayList <Artist> Artists){
        if (choice == 0)
            addSong(Artists);
        if (choice == 1)
            addAlbum(Artists);
        if (choice == 2)
            addArtist(Artists);
    }
    
    public static void addSong(ArrayList <Artist> Artists){
        Scanner input = new Scanner(System.in);
        Song addSong = null;
        Artist addArtist = null;
        Album addAlbum = null;
        
        String name;
        String album;
        String artist;
        int songYear;
        String time;
        int albumYear;
        
        System.out.println("+-----------+"
                       + "\n|Artist List|" 
                       + "\n+-----------+");
        
        for(Artist art: Artists)
            System.out.println(art.simpArtist());
        
        System.out.println();
        
        System.out.print("Enter Artist name: ");
        artist = input.nextLine();
        addArtist = new Artist (artist);
        if (!Artists.contains(addArtist)){
            System.out.println("Artist doesn't exist. Returning...");
            return;
        }
        else
            addArtist = searchArtist(artist, Artists);
        
        System.out.println("+----------+"
                       + "\n|Album List|" 
                       + "\n+----------+");
        System.out.println("|Artist: " + artist);
        
        for(Album alb: addArtist.getAlbums())
                System.out.println(alb.simpAlbum());
        
        System.out.println();
        
        System.out.print("Enter Album name: ");
        album = input.nextLine();
        System.out.print("Enter Album Year: ");
        albumYear = input.nextInt();
        addAlbum = new Album (album, artist, albumYear);
        
        if (!addArtist.getAlbums().contains(addAlbum)){
            System.out.println("Album doesn't exist. Returning...");
            return;
        }
        else {
            addAlbum = addArtist.getAlbum(album);
            input.nextLine();
            
            System.out.println("+---------+"
                            +"\n|Song list|"
                            +"\n+---------+");
            System.out.println("|Artist: " + artist + "\n|Album: " + addAlbum.simpAlbum());
            
            for(Song song: addAlbum.getSongs())
                System.out.println(song.simpSong());
            
            System.out.println();
            
            System.out.print("Enter Song name: ");
            name = input.nextLine();
            System.out.print("Enter Year of Release: ");
            songYear = input.nextInt();
            input.nextLine();
            System.out.print("Enter Time of song (in MM:SS): ");
            time = input.nextLine();
            int splitIndex = time.indexOf(":");
            assert(time.length()>2);
            assert(splitIndex>0);
            assert(splitIndex<time.length()-2);
            int minutes = Integer.parseInt(time.substring(0,splitIndex)); // Parse the minutes
            int seconds = Integer.parseInt(time.substring(splitIndex+1)); // Parse seconds
            seconds+=minutes*60; // convert minutes into seconds and add to seconds

            if (addAlbum.getSong(name) != null){
                System.out.println("Song already exists. Returning...");
                return;
            }
            else{
                addAlbum.addSong(new Song (name, album, artist, seconds, songYear));
            }
        }
    }
        
    public static void addAlbum(ArrayList <Artist> Artists){
        Scanner input = new Scanner(System.in);
        String albumName;
        String artistName;
        int albumYear;
        Artist artistAdd = null;
        
        System.out.println("+-----------+"
                       + "\n|Artist List|" 
                       + "\n+-----------+");
        
        for(Artist art: Artists)
            System.out.println(art.simpArtist());
        
        System.out.println();
        
        System.out.print("Enter Artist for New Album: ");
        artistName = input.nextLine();
        artistAdd = new Artist(artistName);
        
        if (!Artists.contains(artistAdd)){
            System.out.println("Artist doesn't exist. Please add artist first. Returning...");
            return;
        }
        else if (Artists.contains(artistAdd)){
            artistAdd = searchArtist(artistName, Artists);
            
            System.out.println("+----------+"
                           + "\n|Album List|" 
                           + "\n+----------+");
            System.out.println("|Artist: " + artistName);
            for(Album alb: artistAdd.getAlbums())
                System.out.println(alb.simpAlbum());
            
            System.out.println();
            
            System.out.print("Enter name of new album: ");
            albumName = input.nextLine();
            if(artistAdd.getAlbum(albumName)!= null){
                System.out.println("Album already exists. Returning...");
                return;
            }
            System.out.print("Enter year of release of album: ");
            albumYear = input.nextInt();
            artistAdd.addAlbum(new Album(albumName, artistName, albumYear));
        }
    }
    
    public static void addArtist(ArrayList <Artist> Artists){
        Scanner input = new Scanner(System.in);
        String name;
        Artist addArtist = null;
        
        System.out.println("+-----------+"
                       + "\n|Artist List|" 
                       + "\n+-----------+");
        for(Artist art: Artists)
            System.out.println(art.simpArtist());
        
        System.out.println();
        
        System.out.print("Enter Name of Artist: ");
        name = input.nextLine();
        addArtist = new Artist(name);
        
        if(!Artists.contains(addArtist)) {
            Artists.add(addArtist);
        }
        else if (Artists.contains(addArtist)){
            System.out.println("Artist already exists. Returning...");
        }
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
    

    
    /*public static ArrayList <Song> searchAlbum(String albumSearch, ArrayList<Song> Songs){
        
    }*/
}
