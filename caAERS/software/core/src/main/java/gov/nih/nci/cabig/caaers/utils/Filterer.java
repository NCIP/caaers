package gov.nih.nci.cabig.caaers.utils;

import java.util.Iterator;

/**
 * @author Biju Joseph
 * 
 * Date: Sep 20, 2007 Time: 10:54:00 AM
 * 
 */
public interface Filterer {
    // ~ Methods
    // ========================================================================================================

    /**
     * Gets the filtered collection or array.
     * 
     * @return the filtered collection or array
     */
    public Object getFilteredObject();

    /**
     * Returns an iterator over the filtered collection or array.
     * 
     * @return an Iterator
     */
    public Iterator iterator();

    /**
     * Removes the the given object from the resulting list.
     * 
     * @param object
     *                the object to be removed
     */
    public void remove(Object object);
}