package SE_project;

import java.util.*;

public class SongComparator 
    implements Comparator<Song>, java.io.Serializable{
    @Override
    public int compare(Song o1, Song o2){
        String nameOne = o1.getName();
        String nameTwo = o2.getName();
        int comparison = nameOne.compareTo(nameTwo);
        return comparison;
    }
}
