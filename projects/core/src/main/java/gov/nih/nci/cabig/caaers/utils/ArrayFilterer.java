package gov.nih.nci.cabig.caaers.utils;


import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.acegisecurity.afterinvocation.BasicAclEntryAfterInvocationCollectionFilteringProvider;
import org.apache.commons.collections.iterators.ArrayIterator;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author Biju Joseph Date: Sep 20, 2007 Time: 10:55:46 AM
 * 
 */

public class ArrayFilterer implements Filterer {
    // ~ Static fields/initializers
    // =====================================================================================

    protected static final Log logger = LogFactory
                    .getLog(BasicAclEntryAfterInvocationCollectionFilteringProvider.class);

    // ~ Instance fields
    // ================================================================================================

    private List removeList;

    private Object[] list;

    // ~ Constructors
    // ===================================================================================================

    public ArrayFilterer(Object[] list) {
        this.list = list;

        // Collect the removed objects to a HashSet so that
        // it is fast to lookup them when a filtered array
        // is constructed.
        removeList = new ArrayList();
    }

    // ~ Methods
    // ========================================================================================================

    /**
     * 
     * @see org.acegisecurity.afterinvocation.Filterer#getFilteredObject()
     */
    public Object getFilteredObject() {
        // Recreate an array of same type and filter the removed objects.
        int originalSize = list.length;
        int sizeOfResultingList = originalSize - removeList.size();
        Object[] filtered = (Object[]) Array.newInstance(list.getClass().getComponentType(),
                        sizeOfResultingList);

        for (int i = 0, j = 0; i < list.length; i++) {
            Object object = list[i];

            if (!removeList.contains(object)) {
                filtered[j] = object;
                j++;
            }
        }

        if (logger.isDebugEnabled()) {
            logger.debug("Original array contained " + originalSize + " elements; now contains "
                            + sizeOfResultingList + " elements");
        }

        return filtered;
    }

    /**
     * 
     * @see org.acegisecurity.afterinvocation.Filterer#iterator()
     */
    public Iterator iterator() {
        return new ArrayIterator(list);
    }

    /**
     * 
     * @see org.acegisecurity.afterinvocation.Filterer#remove(java.lang.Object)
     */
    public void remove(Object object) {
        removeList.add(object);
    }
}
