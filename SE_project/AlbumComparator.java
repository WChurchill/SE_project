/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SE_project;

import java.util.*;
/**
 *
 * @author Gabriel
 */
public class AlbumComparator 
    implements Comparator<Album>, java.io.Serializable{
    @Override
    public int compare(Album o1, Album o2){
        String nameOne = o1.getName();
        String nameTwo = o2.getName();
        int comparison = nameOne.compareTo(nameTwo);
        return comparison;
    }
}
