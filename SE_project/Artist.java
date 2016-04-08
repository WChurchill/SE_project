
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
    
    public ArrayList <Album> getAlbums(){
        return this.Albums;
    }
    
    public Album getAlbum(String albumName){
        for (int i = 0; i < Albums.size(); i++)
            if (Albums.get(i).getName().equals(albumName)){
                return Albums.get(i);
            }
        return null;
    }
    
    public Album getAlbum(int i){
        return Albums.get(i);
    }
        
    public void addAlbum(Album addAlbum){
        Albums.add(addAlbum);
    }
    
    public boolean deleteAlbum(String albumDelete){
        boolean found = false;
        Album albumToDelete = null;
        
        for (int i = 0; i < Albums.size(); i++){
            if (Albums.get(i).getName().equals(albumDelete)){
                albumToDelete = Albums.get(i);
                Albums.remove(i);
                return true;
            }
        }
        return false;
    }
    
    public void clearAlbums(){
        this.Albums.clear();
    }
    
    public void changeArtist(String newArtist){
        for (int i = 0; i < Albums.size(); i++){
            Albums.get(i).changeArtist(newArtist);
        }
        this.name = newArtist;
    }
        
    public String albumsView(){
        String albumList = "";
        
        for (int i = 0; i < Albums.size(); i++)
            albumList+=(Albums.get(i).toString());
        
        return albumList;
    }
    
    public String simpArtist(){
        return(this.name);
    }
    
    @Override
    public String toString(){
        String rep = ("Artist: " + this.name);
        /*for (int i = 0; i < Albums.size(); i++){
            rep+=("\n       Album: " + Albums.get(i).getName());
        }*/
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
