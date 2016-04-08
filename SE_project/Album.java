
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Gabriel
 */
import java.util.*;

public class Album {
    private String name;
    private String artist;
    private int year;
    private ArrayList <Song> Songs = new ArrayList<Song>();
    
    public Album(String name, String artist, int year){
        this.name = name;
        this.artist = artist;
        this.year = year;
    }
    
    public String getName(){
        return this.name;
    }
    
    public String getArtist(){
        return this.artist;
    }
    
    public int getYear(){
        return this.year;
    }
         
    public ArrayList<Song> getSongs(){
        return this.Songs;
    }
    
    public Song getSong(int i){
        return Songs.get(i);
    }
    
    public Song getSong(String songName){
        for (int i = 0; i < Songs.size(); i++)
            if (Songs.get(i).getName().equals(songName)){
                return Songs.get(i);
            }
        return null;
    }
    
    public void addSong(Song addSong){
        Songs.add(addSong);
    }

    public boolean deleteSong(String songDelete){
        boolean found = false;
        Song songToDelete = null;
        
        for (int i = 0; i < Songs.size(); i++){
            if (Songs.get(i).getName().equals(songDelete)){
                songToDelete = Songs.get(i);
                Songs.remove(i);
                return true;
            }
        }
        return false;
    }
    
    public void clearSongs(){
        Songs.clear();
    }
        
    public void changeArtist(String newArtist){
        for(int i = 0; i < Songs.size(); i++)
            Songs.get(i).setArtist(newArtist);
        this.artist = newArtist;
    }
    
    public void changeName(String newName){
        for(int i = 0; i < Songs.size(); i++)
            Songs.get(i).setAlbum(newName);
        this.name = newName;
    }
    
    public void changeYear(int newYear){
        this.year = newYear;
    }
    
    public String songsView(){
        String songsView = "";
        for(int i = 0; i < Songs.size(); i++)
            songsView+=Songs.get(i).toLabeledString();
        
        return songsView;
    }
    
    public String simpAlbum(){
        return (this.name + ", " + this.year);
    }
    
    @Override
    public String toString(){
        String rep = ("\nArtist: " + artist + "\nAlbum: " + this.name + "\nYear: " + this.year);
        for (int i = 0; i < Songs.size(); i++){
            rep+=("\n     Song: " + Songs.get(i).getName());
        }
        return rep;
    }
    
    @Override
    public boolean equals(Object o){
        if(o instanceof Album){
            Album toCompare = (Album) o;
            String nameComp = toCompare.getName().replaceAll("\\s+", "");
            String nameThisComp = name.replaceAll("\\s+","");
            return nameThisComp.equals(nameComp);
        }
    return false;
    }
}
