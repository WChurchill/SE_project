import java.util.*;

public class Song {
    private String songName;
    private String artist;
    private String album;
    private int seconds;
    private int year;
    private double price;

    public Song(String song, String artist, String album, int seconds, int year){
	this.songName = song;
	this.artist = artist;
	this.album = album;
	this.seconds = seconds;
	this.year = year;
	this.price = 0.99;
    }

    public Song(String song, String artist, String album, int seconds, int year, double price){
       	this.songName = song;
	this.artist = artist;
	this.album = album;
	this.seconds = seconds;
	this.year = year;
	this.price = price;
    }
    
    // Default Constructor
    public Song(){
	songName = "<unknown>";
	artist = "<unknown>";
	album = "<unknown>";
	seconds = -1;
	year = -1;
	price = 0;
    }
    
    // Accessors
    public String getName() {
	return songName;
    }

    public String getArtist() {
	return artist;
    }

    public String getAlbum() {
	return album;
    }

    public int getSeconds() {
	return seconds;
    }

    public int getYear() {
	return year;
    }

    public double getPrice(){
	return price;
    }

    // Mutators
    public void setName(String name) {
	songName = name;
    }

    public void setArtist(String artist) {
	this.artist = artist;
    }

    public void setAlbum(String album) {
	this.album = album;
    }

    public void setSeconds(int seconds) {
	this.seconds = seconds;
    }

    public void setYear(int year) {
	this.year = year;
    }
    
    public void setPrice(double newPrice){
	price = newPrice;
    }
    
    public String simpSong(){
        return this.songName;
    }

    // Returns a string representation of a song in human readable format
    // with labeled attributes
    public String toLabeledString(){
	String songString;
    	String duration;
    	songString=  " Title:\t"+songName+"\n";
    	songString+= "Artist:\t"+artist+"\n";
    	songString+= " Album:\t"+album+"\n";
    	duration= Integer.toString(seconds/60)+':';
    	if((seconds%60)<10)// convert from seconds to MM:SS
	    duration+='0'+Integer.toString(seconds%60); 
    	else
	    duration+=Integer.toString(seconds%60); 
    	songString+= "Length:\t"+duration+"\n";
    	songString+= "  Year:\t"+year;
    	return songString;
    }
    
    //toString override
    public String toString()
    {
    	String songString;
    	String duration;
    	songString= songName+"\r\n";
    	songString+= artist+"\r\n";
    	songString+= album+"\r\n";
    	duration= Integer.toString(seconds/60)+':';
    	if((seconds%60)<10)// convert from seconds to MM:SS
    		duration+='0'+Integer.toString(seconds%60); 
    	else
    		duration+=Integer.toString(seconds%60); 
    	songString+= duration+"\r\n";
    	songString+= year+"\r\n";
	songString+= price+"\r\n";
    	return songString;
    }
    
    public int compareTo(Song o){
        return this.songName.compareTo(o.songName);
    }
    
    public boolean equals(Object o){
        Song toCompare = (Song) o;
        String compName = toCompare.getName().replaceAll("\\s+", "");
        String compArtist = toCompare.getArtist().replaceAll("\\s+", "");
        String compAlbum = toCompare.getArtist().replaceAll("\\s+", "");
        
        String thisName = songName.replaceAll("\\s+", "");
        String thisArtist = artist.replaceAll("\\s+", "");
        String thisAlbum = album.replaceAll("\\s+", "");
        
        if (compName.equals(thisName) && compArtist.equals(thisArtist) 
            && compAlbum.equals(thisAlbum) && toCompare.getSeconds() == seconds && toCompare.getYear() == year)
                return true;
        
        return false;
    }
}
