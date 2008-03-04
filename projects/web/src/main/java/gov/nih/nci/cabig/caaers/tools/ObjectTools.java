package gov.nih.nci.cabig.caaers.tools;

import gov.nih.nci.cabig.caaers.CaaersSystemException;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

/**
 * @author Rhett Sutphin
 */
public class ObjectTools {
    /**
     * Creates a copy of the given object with only the listed properties included.
     * 
     * @param src
     * @param initializer
     *                if supplied, the new instance will be passed to this object's
     *                {@link Initializer#initialize} method before the properties are copied. This
     *                provides an opportunity to initialize intermediate objects.
     * @param properties
     * @return a newly-created object of the same class as <code>src</code>
     */
    @SuppressWarnings("unchecked")
    public static <T> T reduce(T src, Initializer<T> initializer, String... properties) {
        T dst;
        try {
            // it doesn't seem like this cast should be necessary
            dst = (T) src.getClass().newInstance();
            if (initializer != null) initializer.initialize(dst);
        } catch (InstantiationException e) {
            throw new CaaersSystemException("Failed to instantiate " + src.getClass().getName(), e);
        } catch (IllegalAccessException e) {
            throw new CaaersSystemException("Failed to instantiate " + src.getClass().getName(), e);
        }

        BeanWrapper source = new BeanWrapperImpl(src);
        BeanWrapper destination = new BeanWrapperImpl(dst);
        for (String property : properties) {
            destination.setPropertyValue(property, source.getPropertyValue(property));
        }
        return dst;
    }

    public static <T> List<T> reduceAll(List<T> src, Initializer<T> initializer,
                    String... properties) {
        List<T> dst = new ArrayList<T>(src.size());
        for (T t : src) {
            dst.add(reduce(t, initializer, properties));
        }
        return dst;
    }

    /**
     * Creates a copy of the given object with only the listed properties included.
     * 
     * @param src
     * @param properties
     * @return a newly-created object of the same class as <code>src</code>
     */
    public static <T> T reduce(T src, String... properties) {
        return reduce(src, null, properties);
    }

    public static <T> List<T> reduceAll(List<T> src, String... properties) {
        return reduceAll(src, null, properties);
    }

    // TODO: the initializer callback is a bit of a hack. Come up with a different way to
    // initialize intermediate subobjects.
    public static interface Initializer<T> {
        void initialize(T instance);
    }

    // Static class
    private ObjectTools() {
    }
}
