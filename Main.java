import java.util.*;
import java.io.FileNotFoundException;
import java.util.InputMismatchException;

public class Main {
    static final int LOGIN_OPTION = 1;
    static final int CREATE_ACCOUNT_OPTION = 2;
    static final int SHUTDOWN_OPTION = 3;
    
    public static void main(String[] args) {
	//ArrayList <Artist> Artists = new ArrayList <Artist>();      //initialize new ArrayList <Artist> Artists which will hold all artists, albums, and songs
        SongDB songDB = SongDB.getInstance();     //initialize new SongDB main which will be used to fill Artists with all items
	CustomerDB customerDB = CustomerDB.getInstance();
	try{
	    songDB.loadArtistsFromFile();
	    
	}catch(FileNotFoundException e){
	    System.out.println("ERROR: songs.txt not found. ");
	    System.out.println("Exiting...");
	    System.exit(0);
	}
	try{
	    customerDB.loadFromFile();
	}catch(FileNotFoundException e){
	    System.out.println("ERROR: customers.txt not found. ");
	    System.out.println("Exiting...");
	    System.exit(0);	    
	}
        //Artists = main.artists("songs.txt");        //Artists equals the ArrayList<Artist> returned by main.artists passing file name "songs.txt" 
        //Artists.sort(new ArtistComparator());       //sort Artists using ArtistComparator
        Scanner input = new Scanner(System.in);     //input scanner
        int choice = 0;     //int for main menu choice
        int subChoice;      //int for sub-menu choice
        int loginStat;
        Logo();     //Display Logo

	boolean validChoice = false;
	LoginController lController = LoginController.getInstance();
	int loginChoice;
	do{
	    promptLogin();
	    
	    try {
		loginChoice = input.nextInt();

		
		switch(loginChoice){
		case LOGIN_OPTION:
		    lController.processLogin();
		    break;
		case CREATE_ACCOUNT_OPTION:
		    lController.createAccount();
		    break;
		case SHUTDOWN_OPTION:
		    try{
			customerDB.saveToFile();
			songDB.saveToFile();
		    }catch(Exception e){
			e.printStackTrace();
		    }
		    printGoodbye();
		    System.exit(0);
		    break;
		default:
		    throw new InputMismatchException();
		}
	    }
	    catch (InputMismatchException e) {
		System.out.println("Invalid Choice");
	    }
	    catch(NoSuchElementException e){
		System.out.println("Please enter a number.");
	    }
	}while(!validChoice);
	
	//loginStat = logIn(admin, input);
	
        
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
    
    public static void choiceMenu(){    //choiceMenu is a menu that displays 3 specific choices of music classification: Song, Album, and Artist
        System.out.print(
            "0.Exit\n1.Song \n2.Album \n3.Artist\n>>>Enter Choice: ");
    }
    
    public static void searchOptionMenu(){      //searchOptionMenu is a menu that displays 2 choices of search criteria: Name and Year
        System.out.print("1.By Name \n2.By Year\n>>>Enter Choice: ");
    }
    
    public static void printGoodbye(){
 	System.out.println("Thank you for using Massive Music Megastore.");
 
     }
     
     public static void promptLogin(){
 	System.out.print("1. Log In \n2. Create Account \n3. Shut Down\n>>>Enter Choice: ");
     }
     
 }
   
