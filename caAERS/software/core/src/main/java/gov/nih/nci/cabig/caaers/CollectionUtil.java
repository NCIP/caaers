package gov.nih.nci.cabig.caaers;

import gov.nih.nci.cabig.caaers.domain.Retireable;

import java.util.List;
import java.util.ArrayList;

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
}