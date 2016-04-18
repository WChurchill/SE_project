import java.util.*;

public class SearchController {

    private static SearchController instance;

    public static SearchController getInstance(){
	if(instance==null) instance =  new SearchController();
	return instance;
    }
    
    private  SearchController(){

    }
    
    public void search(){
	SongDB main = SongDB.getInstance();
        choiceMenu();
        Scanner input = new Scanner(System.in);
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
            ArrayList <Song> songsFound = main.searchSongs(songSearch);
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
            ArrayList <Album> albumsFound = main.searchAlbums(albumSearch, main.Artists);
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
            Artist result = main.searchArtist(artistSearch, main.Artists);        //Run searchSong passing songSearch and Songs ArrayList
            if(result==null){
                System.out.println("Artist not found...");
            }else{
                System.out.println(result.toString());
                System.out.println(result.albumsView());
            }
        }    
    }
    
    public static void choiceMenu(){    //choiceMenu is a menu that displays 3 specific choices of music classification: Song, Album, and Artist
        System.out.print(
            "0.Exit\n1.Song \n2.Album \n3.Artist\n>>>Enter Choice: ");
    }
}
