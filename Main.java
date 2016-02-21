/**
 *
 * @author Gabriel
 */
import java.util.*;

public class Main {
    public static void main(String[] args) {
        ArrayList<Song> Songs = new ArrayList <Song>();
        Scanner input = new Scanner(System.in);     //input scanner
        int choice = 0;     //int for main menu choice
        int subChoice;      //int for sub-menu choice
        
        Song Korn = new Song ("It's On!", "Korn","Follow the Leader",120,1998);
        Song Chevelle = new Song ("Family System","Chevelle","Wonder What's Next",250,2002);
        Song Bloc = new Song ("Helicopter","Bloc Party","Silent Alarm",120,2001);
        Song Stone = new Song ("Xeno","Stone Temple Pilots","Hello",120,200);
        
        Songs.add(Korn);
        Songs.add(Chevelle);
        Songs.add(Bloc);
        Songs.add(Stone);
        
        for (int i = 0; i < Songs.size(); i++){
            System.out.println(Songs.get(i).getName());
        }
        
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
                    
                }
                else if (subChoice ==2){
                    
                }
                else if (subChoice ==3){
                    
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
                    
                }
                else if (subChoice ==2){
                    
                }
                else if (subChoice ==3){
                    
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
                    
                }
                else if (subChoice ==2){
                    
                }
                else if (subChoice ==3){
                    
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
                    Song result = binSearch(songSearch, Songs);        //Run searchSong passing songSearch and Songs ArrayList
                }
                else if (subChoice ==2){
                    
                }
                else if (subChoice ==3){
                    
                }
                break;
            case 5:
             
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
    
    public static Song searchSong(String songSearch, ArrayList<Song> Songs){ //searchSong will take string of name of song and Song ArrayList
        Songs.sort(new SongComparator());
        boolean found = false;  //Initial boolean for found is false
        int ind = 0;    //Initial index for song return is 0
        
        for (int i = 0; i < Songs.size(); i++){     //for loop to run through Songs ArrayList
            if (Songs.get(i).getName().equals(songSearch)){     //if name of current element is equal to song request
                found = true;       //found is true
                ind = i;        //ind is i
            }
        }
        if (found){     //if song is found
            System.out.println("Song found!");       //print success message
            return Songs.get(ind);      //return Song
        }
        else {      //else
            System.out.println("Not found, null return");       //print failure message
            return null;        //return null
        }
    }
    
    public static Song binSearch(String songSearch, ArrayList<Song> Songs){
        int low = 0;
        int high = Songs.size()-1;
        while (low <= high){
            int mid = low + (high-low)%2;
            String songTry = Songs.get(mid).getName();
            int compare = songTry.compareTo(songSearch);
            if (compare < 0)
                high = mid - 1;
            else if (compare > 0)
                low = mid + 1;
            else if (compare == 0){
                System.out.println("Song found!");
                return Songs.get(mid);
            }
        }
        System.out.println("Song not found");
        return null;
    }
}
