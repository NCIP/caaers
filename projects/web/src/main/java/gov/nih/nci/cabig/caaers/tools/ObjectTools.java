package gov.nih.nci.cabig.caaers.tools;

import gov.nih.nci.cabig.caaers.CaaersSystemException;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.util.List;
import java.util.ArrayList;

/**
 * @author Rhett Sutphin
 */
public class ObjectTools {

    /**
     * Creates a copy of the given object with only the listed properties included.
     * @param src
     * @param properties
     * @return a newly-created object of the same class as <code>src</code>
     */
    @SuppressWarnings("unchecked")
    public static <T> T reduce(T src, String... properties) {
        T dst;
        try {
            // it doesn't seem like this cast should be necessary
            dst = (T) src.getClass().newInstance();
        } catch (InstantiationException e) {
            throw new CaaersSystemException("Failed to instantiate " + src.getClass().getName(), e);
        } catch (IllegalAccessException e) {
            throw new CaaersSystemException("Failed to instantiate " + src.getClass().getName(), e);
        }

        BeanWrapper source = new BeanWrapperImpl(src);
        BeanWrapper destination = new BeanWrapperImpl(dst);
        for (String property : properties) {
            destination.setPropertyValue(
                property,
                source.getPropertyValue(property)
            );
        }
        return dst;
    }

    public static <T> List<T> reduceAll(List<T> src, String... properties) {
        List<T> dst = new ArrayList<T>(src.size());
        for (T t : src) {
            dst.add(reduce(t, properties));
        }
        return dst;
    }

    // Static class
    private ObjectTools() { }
}
