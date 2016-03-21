package SE_project;

/**
 *
 * @author Gabriel
 */
import java.util.*;
import java.util.Calendar;
import java.io.FileNotFoundException;

public class Main {
    static final int curYear = Calendar.getInstance().get(Calendar.YEAR);       //Get current year from calendar, will be used to verify music creation
    static final int tooOld = curYear - 100;        //static final int tooOld is curYear minus 100 signifies lower boundary of song and album years
    static final int tooNew = curYear + 10;         //static final int tooNew is curYear plus 10 signifies upper boundary of song and album years

    public static void main(String[] args) {
        ArrayList <Artist> Artists = new ArrayList <Artist>();      //initialize new ArrayList <Artist> Artists which will hold all artists, albums, and songs
        SongDB main = new SongDB();     //initialize new SongDB main which will be used to fill Artists with all items
        Artists = main.artists("songs.txt");        //Artists equals the ArrayList<Artist> returned by main.artists passing file name "songs.txt" 
        Artists.sort(new ArtistComparator());       //sort Artists using ArtistComparator
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
                addController(input, Artists);      //call addController passing input scanner and Artists
                break;
            case 2:
                editController(input, Artists);     //call editController passing input scanner and Artists
                break;
            case 3:
                deleteController(input, Artists);   //call deleteController passing input scanner and Artists    
                break;
            case 4:
                searchController(input, Artists);   //call searchController passing input scanner and Artists
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
            "0.Exit\n1.Song \n2.Album \n3.Artist\n>>>Enter Choice: ");
    }
    
    public static void searchOptionMenu(){      //searchOptionMenu is a menu that displays 2 choices of search criteria: Name and Year
        System.out.print("1.By Name \n2.By Year\n>>>Enter Choice: ");
    }
    
    //addController is a static void taking Scanner input, ArrayList<Artist> Artists, will call to addSong, addAlbum, or addArtist 
    //based on choice by user, and these sub-functions will add a song, album, or artist, respectively
    public static void addController(Scanner input, ArrayList <Artist> Artists){        
        int choice;         //int to hold choice  
        choiceMenu();       //display choice menu
        choice = input.nextInt();       //take choice from user
        while (choice < 0 || choice > 3){     //validate sub-choice between 0-3 inclusive
                    System.out.print("\n>>>Invalid. Enter Choice 1-3, or 0 to exit: ");
                    choice = input.nextInt();
                }
        if (choice == 0)        //if choice is zero
            return;     //return to menu
        else if (choice == 1)   //if choice is one
            addSong(input, Artists);        //call to addSong passing input scanner and Artists
        else if (choice == 2)   //if choice is two
            addAlbum(input, Artists);       //call to addAlbum passing input scanner and Artists
        else if (choice == 3){      //if choice is three
            String newArtist;       //String to hold name of new artist
            
            //Display list of artists in Artists arrayList
            System.out.println("+-----------+"
                           + "\n|Artist List|" 
                           + "\n+-----------+");
            for(Artist art: Artists)
                System.out.println(art.simpArtist());

            System.out.println();       //newLine

            //Get name of new Artist from user and call addArtist passing newArtist String and Artists list, this will create new artist in Artists
            System.out.print("Enter Name of Artist: ");     
            newArtist = input.nextLine();
            addArtist(newArtist, Artists);
        }
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
        
        //Print out formatted list of albums that currently exist for selected artist
        System.out.println("+----------+"
                       + "\n|Album List|" 
                       + "\n+----------+");
        System.out.println("|Artist: " + artist);

        for(Album alb: addArtist.getAlbums())
                System.out.println(alb.simpAlbum());

        System.out.println();       //newLine
        
        input.nextLine();
        
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
    
    public static void editController(Scanner input, ArrayList <Artist> Artists){
        choiceMenu();
        int choice = input.nextInt();
        while (choice < 0 || choice > 3){     //validate sub-choice between 1-3 inclusive
                    System.out.print("\n>>>Invalid. Enter Choice 1-3, or 0 to exit: ");
                    choice = input.nextInt();
                }
        if (choice == 0)
            return;
        else if (choice == 1)
            editSong(input, Artists);
        else if (choice == 2)
            editAlbum(input, Artists);
        else if (choice == 3)
            editArtist(input, Artists);
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
    
    public static void deleteController(Scanner input, ArrayList <Artist> Artists){
        choiceMenu();
        int choice;
        int nextChoice; 
        int delMethod ;
        ArrayList <Integer> delChoices = new ArrayList<>();
        
        choice = input.nextInt();
        while (choice < 0 || choice > 3){     //validate sub-choice between 1-3 inclusive
                    System.out.print("\n>>>Invalid. Enter Choice 1-3, or 0 to exit: ");
                    choice = input.nextInt();
                }
        if (choice == 0)
            return;
        else if (choice == 1){
            System.out.print("Enter 1 to delete Song(s) by name, 2 to delete Song(s) by Album, 3 to delete Songs(s) by Artist: ");
            delMethod= input.nextInt();
            while(delMethod < 1 || delMethod >3){
                System.out.print("Invalid. Try again: ");
                delMethod = input.nextInt();
            }
            if (delMethod == 1){
                int songNum = 0;
                int songChoice = -4;
                String songDelete;
                ArrayList <Song> songsFound = new ArrayList<>();
                ArrayList <Song> songsToDelete = new ArrayList<>();
                System.out.print("Enter name of song(s) to delete: ");
                input.nextLine();
                songDelete = input.nextLine();
                songsFound = searchSongs(songDelete, Artists);
                while (songsFound.isEmpty()){
                    System.out.print("Song(s) not found. Enter 0 to exit, any other number to try again: ");
                    nextChoice = input.nextInt();
                    if (choice == 0){
                        System.out.println("Returning to menu...");
                        return;
                    }
                    else{
                        System.out.print("Enter name of Song(s) to delete: ");
                        input.nextLine();
                        songDelete = input.nextLine();
                        songsFound = searchSongs(songDelete, Artists);
                    }
                }

                for(int i = 0; i < songsFound.size(); i++){
                    songNum++;
                    System.out.println("(" + i + ") " + songsFound.get(i).simpSong());
                }

                System.out.println("Enter number of song to delete, enter again to keep, continue until all songs to delete are chosen and then enter -2 to finish. \nEnter -3 to delete all, or enter -1 to exit");
                while (songChoice != -2){
                    System.out.print("Delete/Restore Song#:");
                    songChoice = input.nextInt();
                    while(songChoice < -3 || songChoice > songNum){
                        System.out.print("Invalid. Try again: ");
                        songChoice = input.nextInt();
                    }
                    if (songChoice == -2){
                        break;
                    }
                    else if (songChoice == -3){
                        for (int j = 0; j < songNum; j++){
                            if (delChoices.contains(j))
                                delChoices.remove(j);
                            delChoices.add(j);
                        }
                        for(int i = 0; i < songsFound.size(); i++){
                            if(delChoices.contains(i))
                                System.out.println("X(" + i + ") " + songsFound.get(i).simpSong());
                            else
                                System.out.println("(" + i + ") " + songsFound.get(i).simpSong());
                        }
                    }
                    else{
                        if (delChoices.contains(songChoice))
                            delChoices.remove(songChoice);
                        else
                            delChoices.add(songChoice);
                        for(int i = 0; i < songsFound.size(); i++){
                            if(delChoices.contains(i))
                                System.out.println("X(" + i + ") " + songsFound.get(i).simpSong());
                            else
                                System.out.println("(" + i + ") " + songsFound.get(i).simpSong());
                        }
                    }
                }
                delChoices.sort(new IntComparator());
                for(int i= 0; i < delChoices.size(); i++)
                    songsToDelete.add(songsFound.get(delChoices.get(i)));
                for(int j = 0; j < songsToDelete.size(); j++)
                    deleteSong(songsToDelete.get(j), Artists);
                System.out.println("Operation complete...");
            }
            else if(delMethod == 2){
                int albumNum = 0;
                int albumChoice;
                int songNum = 0;
                int songChoice = -4;
                String fromAlbumDelete;
                ArrayList <Song> songsFound = new ArrayList<>();
                ArrayList <Song> songsToDelete = new ArrayList<>();
                ArrayList <Album> albumsFound = new ArrayList<>();
                Album albumFromDelete = null;
                
                System.out.print("Enter name of album to delete songs from: ");
                input.nextLine();
                fromAlbumDelete = input.nextLine();
                albumsFound = searchAlbums(fromAlbumDelete, Artists);
                while (albumsFound.isEmpty()){
                    System.out.print("Album not found. Enter 0 to exit, any other number to try again: ");
                    nextChoice = input.nextInt();
                    if (choice == 0){
                        System.out.println("Returning to menu...");
                        return;
                    }
                    else{
                        System.out.print("Enter name of album to delete songs from: ");
                        input.nextLine();
                        fromAlbumDelete = input.nextLine();
                        albumsFound = searchAlbums(fromAlbumDelete, Artists);
                    }
                }
                
                for(int j = 0; j < albumsFound.size(); j++){
                    albumNum++;
                    System.out.println("(" + j + ") " + albumsFound.get(j).simpAlbum());
                }
                
                System.out.print("Enter number of album to delete songs from, -1 to exit: ");
                albumChoice = input.nextInt();
                while(albumChoice < -1 || albumChoice > albumNum){
                    System.out.print("Invalid. Try again: ");
                    albumChoice = input.nextInt();
                }
                if (albumChoice == -1){
                    System.out.println("Returning to menu...");
                    return;
                }
                
                albumFromDelete = albumsFound.get(albumChoice);
                songsFound = albumFromDelete.getSongs();
                

                for(int i = 0; i < songsFound.size(); i++){
                    songNum++;
                    System.out.println("(" + i + ") " + songsFound.get(i).simpSong());
                }

                System.out.println("Enter number of song to delete, enter again to keep, continue until all songs to delete are chosen and then enter -2 to finish. \nEnter -3 to delete all, or enter -1 to exit");
                while (songChoice != -2){
                    System.out.print("Delete/Restore Song#:");
                    songChoice = input.nextInt();
                    while(songChoice < -3 || songChoice > songNum){
                        System.out.print("Invalid. Try again: ");
                        songChoice = input.nextInt();
                    }
                    if (songChoice == -2){
                        break;
                    }
                    else if (songChoice == -3){
                        for (int j = 0; j < songNum; j++){
                            if (delChoices.contains(j))
                                delChoices.remove(j);
                            delChoices.add(j);
                        }
                        for(int i = 0; i < songsFound.size(); i++){
                            if(delChoices.contains(i))
                                System.out.println("X(" + i + ") " + songsFound.get(i).simpSong());
                            else
                                System.out.println("(" + i + ") " + songsFound.get(i).simpSong());
                        }
                    }
                    else{
                        if (delChoices.contains((Integer)songChoice))
                            delChoices.remove((Integer)songChoice);
                        else
                            delChoices.add(songChoice);
                        for(int i = 0; i < songsFound.size(); i++){
                            if(delChoices.contains(i))
                                System.out.println("X(" + i + ") " + songsFound.get(i).simpSong());
                            else
                                System.out.println("(" + i + ") " + songsFound.get(i).simpSong());
                        }
                    }
                }
                delChoices.sort(new IntComparator());
                for(int i= 0; i < delChoices.size(); i++)
                    songsToDelete.add(songsFound.get(delChoices.get(i)));
                for(int j = 0; j < songsToDelete.size(); j++)
                    deleteSong(songsToDelete.get(j), Artists);
                System.out.println("Operation complete...");
            }
            else if(delMethod == 3){
                int songNum = 0;
                int songChoice = -4;
                String fromArtistDelete;
                Artist artistFromDelete = null;
                ArrayList <Song> songsFound = new ArrayList<>();
                ArrayList <Song> songsToDelete = new ArrayList<>();
                
                                
                System.out.print("Enter name of Artist to delete songs from: ");
                input.nextLine();
                fromArtistDelete = input.nextLine();
                artistFromDelete = searchArtist(fromArtistDelete, Artists);
                while(artistFromDelete == null){
                    int tryAgain;
                    System.out.print("Artist not found. Press 0 to exit, any other number to try again: ");
                    tryAgain = input.nextInt();
                    if (tryAgain == 0)
                        return;
                    else{
                        System.out.print("Enter name of Artist to delete songs from: ");
                        fromArtistDelete=input.nextLine();
                        artistFromDelete = searchArtist(fromArtistDelete, Artists);
                    }
                    
                }
                for (int i = 0; i < artistFromDelete.getAlbums().size(); i++){
                    for(int j = 0; j < artistFromDelete.getAlbum(i).getSongs().size(); j++)
                        songsFound.add(artistFromDelete.getAlbum(i).getSong(j));
                }
                
                for(int i = 0; i < songsFound.size(); i++){
                    songNum++;
                    System.out.println("(" + i + ") " + songsFound.get(i).simpSong());
                }

                System.out.println("Enter number of song to delete, enter again to keep, continue until all songs to delete are chosen and then enter -2 to finish. \nEnter -3 to delete all, or enter -1 to exit");
                while (songChoice != -2){
                    System.out.print("Delete/Restore Song#:");
                    songChoice = input.nextInt();
                    while(songChoice < -3 || songChoice > songNum){
                        System.out.print("Invalid. Try again: ");
                        songChoice = input.nextInt();
                    }
                    if (songChoice == -2){
                        break;
                    }
                    else if (songChoice == -3){
                        for (int j = 0; j < songNum; j++){
                            if (delChoices.contains(j))
                                delChoices.remove(j);
                            delChoices.add(j);
                        }
                        for(int i = 0; i < songsFound.size(); i++){
                            if(delChoices.contains(i))
                                System.out.println("X(" + i + ") " + songsFound.get(i).simpSong());
                            else
                                System.out.println("(" + i + ") " + songsFound.get(i).simpSong());
                        }
                    }
                    else{
                        if (delChoices.contains((Integer)songChoice))
                            delChoices.remove((Integer)songChoice);
                        else
                            delChoices.add(songChoice);
                        for(int i = 0; i < songsFound.size(); i++){
                            if(delChoices.contains(i))
                                System.out.println("X(" + i + ") " + songsFound.get(i).simpSong());
                            else
                                System.out.println("(" + i + ") " + songsFound.get(i).simpSong());
                        }
                    }
                }
                delChoices.sort(new IntComparator());
                for(int i= 0; i < delChoices.size(); i++)
                    songsToDelete.add(songsFound.get(delChoices.get(i)));
                for(int j = 0; j < songsToDelete.size(); j++)
                    deleteSong(songsToDelete.get(j), Artists);
                System.out.println("Operation complete...");
            }    
        }
        
        else if (choice == 2){
            System.out.print("Enter 1 to delete Album(s) by name, 2 to delete Album(s) by Artist: ");
            delMethod = input.nextInt();
            while(delMethod < 1 || delMethod > 2){
                System.out.print("Invalid. Try again: ");
                delMethod = input.nextInt();
            }
            if (delMethod == 1){
                String albumSearch;  //String to hold song requested
                int albumNum = 0;
                int albumChoice = -4;
                ArrayList <Album> albumsFound = new ArrayList<Album>();
                ArrayList <Album> albumsToDelete = new ArrayList<Album>();
                input.nextLine();   //Clear buffer
                System.out.print("Enter name of album to delete: ");   //Prompt user for requested album
                albumSearch = input.nextLine();      //Take result
                albumsFound = searchAlbums(albumSearch, Artists);
                while (albumsFound.isEmpty()){
                    System.out.println("No albums found.");
                    System.out.print("Album not found. Enter 0 to exit, any other number to try again: ");
                    nextChoice = input.nextInt();
                    if (choice == 0){
                        System.out.println("Returning to menu...");
                        return;
                    }
                    else{
                        System.out.print("Enter name of album to delete: ");
                        input.nextLine();
                        albumSearch = input.nextLine();
                        albumsFound = searchAlbums(albumSearch, Artists);
                    }
                }
                
                for(int i = 0; i < albumsFound.size(); i++){
                    albumNum++;
                    System.out.println("(" + i + ") " + albumsFound.get(i).simpAlbum());
                }

                System.out.println("Enter number of album to delete, enter again to keep, continue until all songs to delete are chosen and then enter -2 to finish. \nEnter -3 to delete all, or enter -1 to exit");
                while (albumChoice != -2){
                    System.out.print("Delete/Restore Album#:");
                    albumChoice = input.nextInt();
                    while(albumChoice < -3 || albumChoice > albumNum){
                        System.out.print("Invalid. Try again: ");
                        albumChoice = input.nextInt();
                    }
                    if (albumChoice == -2){
                        break;
                    }
                    else if (albumChoice == -3){
                        for (int j = 0; j < albumNum; j++){
                            if (delChoices.contains(j))
                                delChoices.remove(j);
                            delChoices.add(j);
                        }
                        for(int i = 0; i < albumsFound.size(); i++){
                            if(delChoices.contains(i))
                                System.out.println("X(" + i + ") " + albumsFound.get(i).simpAlbum());
                            else
                                System.out.println("(" + i + ") " + albumsFound.get(i).simpAlbum());
                        }
                    }
                    else{
                        if (delChoices.contains((Integer)albumChoice))
                            delChoices.remove((Integer)albumChoice);
                        else
                            delChoices.add(albumChoice);
                        for(int i = 0; i < albumsFound.size(); i++){
                            if(delChoices.contains(i))
                                System.out.println("X(" + i + ") " + albumsFound.get(i).simpAlbum());
                            else
                                System.out.println("(" + i + ") " + albumsFound.get(i).simpAlbum());
                        }
                    }
                }
                delChoices.sort(new IntComparator());
                for(int i= 0; i < delChoices.size(); i++)
                    albumsToDelete.add(albumsFound.get(delChoices.get(i)));
                for(int j = 0; j < albumsFound.size(); j++)
                    deleteAlbum(albumsToDelete.get(j), Artists);
                System.out.println("Operation complete...");
                
            }
            else if (delMethod == 2){
                String albumSearch;  //String to hold song requested
                int albumNum = 0;
                int albumChoice = -4;
                String fromArtistDelete;
                Artist artistFromDelete = null;
                ArrayList <Album> albumsFound = new ArrayList<Album>();
                ArrayList <Album> albumsToDelete = new ArrayList<Album>();
                input.nextLine();   //Clear buffer                      
                System.out.print("Enter name of Artist to delete albums from: ");
                fromArtistDelete = input.nextLine();
                artistFromDelete = searchArtist(fromArtistDelete, Artists);
                while(artistFromDelete == null){
                    int tryAgain;
                    System.out.print("Artist not found. Press 0 to exit, any other number to try again: ");
                    tryAgain = input.nextInt();
                    if (tryAgain == 0)
                        return;
                    else{
                        System.out.print("Enter name of Artist to delete albums from: ");
                        fromArtistDelete=input.nextLine();
                        artistFromDelete = searchArtist(fromArtistDelete, Artists);
                    }
                    
                }
                for (int i = 0; i < artistFromDelete.getAlbums().size(); i++){
                        albumsFound.add(artistFromDelete.getAlbum(i));
                }
                
                for(int i = 0; i < albumsFound.size(); i++){
                    albumNum++;
                    System.out.println("(" + i + ") " + albumsFound.get(i).simpAlbum());
                }

                System.out.println("Enter number of album to delete, enter again to keep, continue until all albums to delete are chosen and then enter -2 to finish. \nEnter -3 to delete all, or enter -1 to exit");
                while (albumChoice != -2){
                    System.out.print("Delete/Restore Album#:");
                    albumChoice = input.nextInt();
                    while(albumChoice < -3 || albumChoice > albumNum){
                        System.out.print("Invalid. Try again: ");
                        albumChoice = input.nextInt();
                    }
                    if (albumChoice == -2){
                        break;
                    }
                    else if (albumChoice == -3){
                        for (int j = 0; j < albumNum; j++){
                            if (delChoices.contains(j))
                                delChoices.remove(j);
                            delChoices.add(j);
                        }
                        for(int i = 0; i < albumsFound.size(); i++){
                            if(delChoices.contains(i))
                                System.out.println("X(" + i + ") " + albumsFound.get(i).simpAlbum());
                            else
                                System.out.println("(" + i + ") " + albumsFound.get(i).simpAlbum());
                        }
                    }
                    else{
                        if (delChoices.contains((Integer)albumChoice))
                            delChoices.remove((Integer)albumChoice);
                        else
                            delChoices.add(albumChoice);
                        for(int i = 0; i < albumsFound.size(); i++){
                            if(delChoices.contains(i))
                                System.out.println("X(" + i + ") " + albumsFound.get(i).simpAlbum());
                            else
                                System.out.println("(" + i + ") " + albumsFound.get(i).simpAlbum());
                        }
                    }
                }
                delChoices.sort(new IntComparator());
                for(int i= 0; i < delChoices.size(); i++)
                    albumsToDelete.add(albumsFound.get(delChoices.get(i)));
                for(int j = 0; j < albumsToDelete.size(); j++)
                    deleteAlbum(albumsToDelete.get(j), Artists);
                System.out.println("Operation complete...");
            }
        }
        else if (choice == 3){
            String artistDelete;
            Artist artistToDelete = null;
            System.out.print("Enter name of Artist to delete: ");
            input.nextLine();
            artistDelete = input.nextLine();
            artistToDelete = searchArtist(artistDelete, Artists);
            while(artistToDelete == null){
                int tryAgain;
                System.out.print("Artist not found. Press 0 to exit, any other number to try again: ");
                tryAgain = input.nextInt();
                if (tryAgain == 0)
                    return;
                else{
                    System.out.print("Enter name of Artist to delete: ");
                    artistDelete=input.nextLine();
                    artistToDelete = searchArtist(artistDelete, Artists);
                }

            }
            deleteArtist(artistToDelete, Artists);
            System.out.println("Operation complete...");
        }
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
        
    public static void searchController(Scanner input, ArrayList <Artist> Artists){
        choiceMenu();
        int choice = input.nextInt();
        while (choice < 0 || choice > 3){     //validate sub-choice between 1-3 inclusive
                    System.out.print("\n>>>Invalid. Enter Choice 1-3, or 0 to exit: ");
                    choice = input.nextInt();
                }
        if (choice == 0){
            System.out.println("Returning to menu...");
            return;
        }
        else if (choice == 1){
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
        else if (choice == 2){
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
        else if (choice == 3){
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
