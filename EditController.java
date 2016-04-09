import java.util.*;

public class EditController {
    public SongDB main = null;
    
    public EditController(SongDB newDB){
        main = newDB;
    }
    
    public void edit(){
        Scanner input = new Scanner(System.in);
        choiceMenu();
        int choice = input.nextInt();
        while (choice < 0 || choice > 3){     //validate sub-choice between 1-3 inclusive
                    System.out.print("\n>>>Invalid. Enter Choice 1-3, or 0 to exit: ");
                    choice = input.nextInt();
                }
        if (choice == 0)
            return;
        else if (choice == 1)
            main.editSong(input, main.Artists);
        else if (choice == 2)
            main.editAlbum(input, main.Artists);
        else if (choice == 3)
            main.editArtist(input, main.Artists);
    }
    
        public static void choiceMenu(){    //choiceMenu is a menu that displays 3 specific choices of music classification: Song, Album, and Artist
        System.out.print(
            "0.Exit\n1.Song \n2.Album \n3.Artist\n>>>Enter Choice: ");
    }
}
