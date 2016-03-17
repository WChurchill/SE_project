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

public class Artist {
    private String name;
    private ArrayList <Album> Albums = new ArrayList<Album>();
    
    public Artist(String name){
        this.name = name;
    }
    
    public String getName(){
        return name;
    }
    
    public void addAlbum(Album addAlbum){
        Albums.add(addAlbum);
    }
    
    @Override
    public String toString(){
        String rep = ("Artist: " + this.name);
        for (int i = 0; i < Albums.size(); i++){
            rep+=("\n       Album: " + Albums.get(i).getName());
        }
        return rep;
    }
    
    @Override
    public boolean equals(Object o){
    if(o instanceof Artist){
            Artist toCompare = (Artist) o;
            String nameComp = toCompare.getName().replaceAll("\\s+", "");
            String nameThisComp = name.replaceAll("\\s+", "");
            return nameThisComp.equals(nameComp);
        }
    return false;
    }
}
