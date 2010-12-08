package gov.nih.nci.cabig.caaers;

import gov.nih.nci.cabig.caaers.domain.Retireable;

import java.util.*;

/**
 * @author Ion C. Olaru
 */
public class CollectionUtil<T extends Retireable> {
    public static <T extends Retireable> List<T> getActiveObjects(List<T> objects) {
        List<T> l = new ArrayList<T>();
        for (Retireable object : objects) {
            if (!object.isRetired()) l.add((T)object);
        }
        return l;
    }

    public static List<Integer> subtract(List<Integer> a, List<Integer> b){
        Set<Integer> y = new HashSet<Integer>(b);
        Set<Integer> z = new HashSet<Integer>();
        for(Integer i : a){
            if(y.add(i)) z.add(i);
        }
        return new ArrayList(z);
    }
}