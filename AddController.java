import java.util.*;

public class AddController {
        
    public AddController(){
        
    }
    
    public void add(){
        Scanner input = new Scanner(System.in);
	SongDB main = SongDB.getInstance();
        int choice;         //int to hold choice  
        choiceMenu();       //display choice menu
        choice = input.nextInt();       //take choice from user
        input.nextLine();
        while (choice < 0 || choice > 3){     //validate sub-choice between 0-3 inclusive
                    System.out.print("\n>>>Invalid. Enter Choice 1-3, or 0 to exit: ");
                    choice = input.nextInt();
                }
        if (choice == 0)        //if choice is zero
            return;     //return to menu
        else if (choice == 1)   //if choice is one
            main.addSong(input, main.Artists);        //call to addSong passing input scanner and Artists
        else if (choice == 2)   //if choice is two
            main.addAlbum(input, main.Artists);       //call to addAlbum passing input scanner and Artists
        else if (choice == 3){      //if choice is three
            String newArtist;       //String to hold name of new artist
            
            //Display list of artists in Artists arrayList
            System.out.println("+-----------+"
                           + "\n|Artist List|" 
                           + "\n+-----------+");
            for(Artist art: main.getArtists())
                System.out.println(art.simpArtist());

            System.out.println();       //newLine

            //Get name of new Artist from user and call addArtist passing newArtist String and Artists list, this will create new artist in Artists
            System.out.print("Enter Name of Artist: ");     
            newArtist = input.nextLine();
            main.addArtist(newArtist, main.Artists);
        }
    }
    
    public static void choiceMenu(){    //choiceMenu is a menu that displays 3 specific choices of music classification: Song, Album, and Artist
        System.out.print(
            "0.Exit\n1.Song \n2.Album \n3.Artist\n>>>Enter Choice: ");
    }
}
