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
public class AlbumTimeComparator 
    implements Comparator<Album>, java.io.Serializable{
    @Override
    public int compare(Album o1, Album o2){
        int yearOne = o1.getYear();
        int yearTwo = o2.getYear();
        int comparison = yearOne - yearTwo;
        return comparison;
    }
}
