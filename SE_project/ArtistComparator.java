/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.*;
/**
 *
 * @author Gabriel
 */
public class ArtistComparator 
    implements Comparator<Artist>, java.io.Serializable{
    @Override
    public int compare(Artist o1, Artist o2){
        String nameOne = o1.getName();
        String nameTwo = o2.getName();
        int comparison = nameOne.compareTo(nameTwo);
        return comparison;
    }
}
