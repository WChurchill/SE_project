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
        String rep = ("\nName: " + this.name + "\nYear: " + this.year);
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
