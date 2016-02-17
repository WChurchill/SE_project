public class Song {
    private String songName;
    private String artist;
    private String album;
    private int seconds;
    private int year;

    public Song(String song, String artist, String album, int seconds, int year){
	this.songName = song;
	this.artist = artist;
	this.album = album;
	this.seconds = seconds;
	this.year = year;
    }
    
    // Default Constructor
    public Song(){
	songName = "<unknown>";
	artist = "<unknown>";
	album = "<unknown>";
	seconds = -1;
	year = -1;
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
    
}
