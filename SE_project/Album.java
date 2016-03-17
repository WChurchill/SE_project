package SE_project;

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
    
    public void addSong(Song addSong){
        Songs.add(addSong);
    }
    
    @Override
    public String toString(){
        return ("\nName: " + this.name + "\nYear: " + this.year);
    }
    
    @Override
    public boolean equals(Object o){
        if(o instanceof Album){
            Album toCompare = (Album) o;
            if(this.name.equals(toCompare.name) && this.artist.equals(toCompare.artist))
                return true;
        }
    return false;
    }
}
