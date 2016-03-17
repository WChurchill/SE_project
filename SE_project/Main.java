package SE_project;

/**
 *
 * @author Gabriel
 */
import java.util.*;
import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) {
        
        ArrayList <Song> Songs = new ArrayList <Song>();
        ArrayList <Album> Albums = new ArrayList <Album>();
        ArrayList <Artist> Artists = new ArrayList <Artist>();
        try{
            SongDB main = new SongDB();
            Songs = main.loadFromFile("songs.txt");
            Albums = main.albums(Songs);
            Artists = main.artists(Albums);
            
            //Albums.sort(new AlbumComparator());
            
            /*for(int g = 0; g < Albums.size(); g++)
                System.out.println(Albums.get(g).toString());
            
            System.out.println();
            for(int h = 0; h < Songs.size(); h++)
                System.out.println(Songs.get(h).toString());
            
            System.out.println();
            for(int i = 0; i < Artists.size(); i++){
                //if(Artists.get(i).getName().equals("Korn "))
                    System.out.println(Artists.get(i).toString());
                //else
                    //System.out.println("Mismatch");
            }*/
            
            Scanner input = new Scanner(System.in);     //input scanner
            int choice = 0;     //int for main menu choice
            int subChoice;      //int for sub-menu choice
            /*for (int i = 0; i < Albums.size(); i++){
                System.out.print(Albums.get(i).toString());
            }*/
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
                        System.out.print(">>>Invalid. Enter Choice 1-3: ");
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
                case 2:
                    choiceMenu();       //Display choice menu
                    subChoice = input.nextInt();        //Take sub-choice
                    while (subChoice < 1 || subChoice > 3){     //validate sub-choice between 1-3 inclusive
                        System.out.print(">>>Invalid. Enter Choice 1-3: ");
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
                        System.out.print(">>>Invalid. Enter Choice 1-3: ");
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
                        System.out.print(">>>Invalid. Enter Choice 1-3: ");
                        subChoice = input.nextInt();
                    }
                    if (subChoice == 1) {   //if choice is 1, songSearch
                        String songSearch;  //String to hold song requested
                        input.nextLine();   //Clear buffer
                        System.out.print("Enter name of song: ");   //Prompt user for requested song
                        songSearch = input.nextLine();      //Take result
                        Song result = searchSong(songSearch, Songs);        //Run searchSong passing songSearch and Songs ArrayList
                        if(result==null){
                            System.out.println("Search Unsuccessful");
                        }else{
                            System.out.println(result.toLabeledString());
                        }
                    }
                    else if (subChoice ==2){
                        String albumSearch;  //String to hold song requested
                        input.nextLine();   //Clear buffer
                        System.out.print("Enter name of album: ");   //Prompt user for requested song
                        albumSearch = input.nextLine();      //Take result
                        Album result = searchAlbum(albumSearch, Albums);        //Run searchSong passing songSearch and Songs ArrayList
                        if(result==null){
                            System.out.println("Search Unsuccessful");
                        }else{
                            System.out.println(result.toString());
                        }
                    }
                    else if (subChoice ==3){
                        String artistSearch;  //String to hold song requested
                        input.nextLine();   //Clear buffer
                        System.out.print("Enter name of artist: ");   //Prompt user for requested song
                        artistSearch = input.nextLine();      //Take result
                        Artist result = searchArtist(artistSearch, Artists);        //Run searchSong passing songSearch and Songs ArrayList
                        if(result==null){
                            System.out.println("Search Unsuccessful");
                        }else{
                            System.out.println(result.toString());
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
       catch (FileNotFoundException e){
           System.out.println("error");
           System.exit(0);
       }
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
    
    public static Song searchSong(String songSearch, ArrayList<Song> Songs){        //searchSong will take String name of song and ArrayList of Songs and use
        Songs.sort(new SongComparator());//Sort list using songComparator          //binary search to find and return song                
        int low = 0;        //initialize low to 0
        int high = Songs.size()-1;  //initialize high to size of list minus 1
        while (low <= high){        //while low is less than or equal to high
            int mid = low + (high-low)/2;       //middle is high minus low divided by 2
            if (songSearch.compareTo(Songs.get(mid).getName()) < 0)     //if songSearch is less than tested mid name
                high = mid - 1;     //high is middle minus 1
            else if (songSearch.compareTo(Songs.get(mid).getName()) > 0)    //if songSearch is higher than tested mid name
                low = mid + 1;      //low is middle plus 1
            else if (songSearch.compareTo(Songs.get(mid).getName()) == 0){  //else if equal
                //System.out.println("Song found!");      //Success message
                return Songs.get(mid);      //return Song
            }
        }
        
        //System.out.println("Song not found");       //Failure message
        return null;        //return null
    }
    
    public static Album searchAlbum (String albumSearch, ArrayList<Album> Albums){
        Albums.sort(new AlbumComparator());
        int low = 0;        //initialize low to 0
        int high = Albums.size()-1;  //initialize high to size of list minus 1
        while (low <= high){        //while low is less than or equal to high
            int mid = low + (high-low)/2;       //middle is high minus low divided by 2
            if (albumSearch.compareTo(Albums.get(mid).getName()) < 0)     //if songSearch is less than tested mid name
                high = mid - 1;     //high is middle minus 1
            else if (albumSearch.compareTo(Albums.get(mid).getName()) > 0)    //if songSearch is higher than tested mid name
                low = mid + 1;      //low is middle plus 1
            else if (albumSearch.compareTo(Albums.get(mid).getName()) == 0){  //else if equal
                //System.out.println("Song found!");      //Success message
                return Albums.get(mid);      //return Song
            }
        }
        
        //System.out.println("Song not found");       //Failure message
        return null;        //return null
    }
    
    public static Artist searchArtist (String artistSearch, ArrayList<Artist> Artists){
        Artists.sort(new ArtistComparator());
        int low = 0;        //initialize low to 0
        int high = Artists.size()-1;  //initialize high to size of list minus 1
        while (low <= high){        //while low is less than or equal to high
            int mid = low + (high-low)/2;       //middle is high minus low divided by 2
            if (artistSearch.compareTo(Artists.get(mid).getName()) < 0)     //if songSearch is less than tested mid name
                high = mid - 1;     //high is middle minus 1
            else if (artistSearch.compareTo(Artists.get(mid).getName()) > 0)    //if songSearch is higher than tested mid name
                low = mid + 1;      //low is middle plus 1
            else if (artistSearch.compareTo(Artists.get(mid).getName()) == 0){  //else if equal
                //System.out.println("Song found!");      //Success message
                return Artists.get(mid);      //return Song
            }
        }
        
        //System.out.println("Song not found");       //Failure message
        return null;        //return null
    }
    
    /*public static ArrayList <Song> searchAlbum(String albumSearch, ArrayList<Song> Songs){
        
    }*/
}
