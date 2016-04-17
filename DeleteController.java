import java.util.*;

public class DeleteController {

    
    public DeleteController(){

    }
    
    public void delete(){
	SongDB main = SongDB.getInstance();
        Scanner input = new Scanner(System.in);
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
                songsFound = main.searchSongs(songDelete);
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
                        songsFound = main.searchSongs(songDelete);
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
                    main.deleteSong(songsToDelete.get(j), main.Artists);
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
                albumsFound = main.searchAlbums(fromAlbumDelete, main.Artists);
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
                        albumsFound = main.searchAlbums(fromAlbumDelete, main.Artists);
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
                    main.deleteSong(songsToDelete.get(j), main.Artists);
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
                artistFromDelete = main.searchArtist(fromArtistDelete, main.Artists);
                while(artistFromDelete == null){
                    int tryAgain;
                    System.out.print("Artist not found. Press 0 to exit, any other number to try again: ");
                    tryAgain = input.nextInt();
                    if (tryAgain == 0)
                        return;
                    else{
                        System.out.print("Enter name of Artist to delete songs from: ");
                        fromArtistDelete=input.nextLine();
                        artistFromDelete = main.searchArtist(fromArtistDelete, main.Artists);
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
                    main.deleteSong(songsToDelete.get(j), main.Artists);
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
                albumsFound = main.searchAlbums(albumSearch, main.Artists);
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
                        albumsFound = main.searchAlbums(albumSearch, main.Artists);
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
                    main.deleteAlbum(albumsToDelete.get(j), main.Artists);
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
                artistFromDelete = main.searchArtist(fromArtistDelete, main.Artists);
                while(artistFromDelete == null){
                    int tryAgain;
                    System.out.print("Artist not found. Press 0 to exit, any other number to try again: ");
                    tryAgain = input.nextInt();
                    if (tryAgain == 0)
                        return;
                    else{
                        System.out.print("Enter name of Artist to delete albums from: ");
                        fromArtistDelete=input.nextLine();
                        artistFromDelete = main.searchArtist(fromArtistDelete, main.Artists);
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
                    main.deleteAlbum(albumsToDelete.get(j), main.Artists);
                System.out.println("Operation complete...");
            }
        }
        else if (choice == 3){
            String artistDelete;
            Artist artistToDelete = null;
            System.out.print("Enter name of Artist to delete: ");
            input.nextLine();
            artistDelete = input.nextLine();
            artistToDelete = main.searchArtist(artistDelete, main.Artists);
            while(artistToDelete == null){
                int tryAgain;
                System.out.print("Artist not found. Press 0 to exit, any other number to try again: ");
                tryAgain = input.nextInt();
                if (tryAgain == 0)
                    return;
                else{
                    System.out.print("Enter name of Artist to delete: ");
                    artistDelete=input.nextLine();
                    artistToDelete = main.searchArtist(artistDelete, main.Artists);
                }

            }
            main.deleteArtist(artistToDelete, main.Artists);
            System.out.println("Operation complete...");
        }
    }
    
    public static void choiceMenu(){    //choiceMenu is a menu that displays 3 specific choices of music classification: Song, Album, and Artist
        System.out.print(
            "0.Exit\n1.Song \n2.Album \n3.Artist\n>>>Enter Choice: ");
    }
}
