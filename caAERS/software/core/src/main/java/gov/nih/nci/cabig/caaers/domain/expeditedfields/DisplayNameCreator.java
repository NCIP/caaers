package gov.nih.nci.cabig.caaers.domain.expeditedfields;

 
/**
 * The Interface DisplayNameCreator.
 *
 * @author Rhett Sutphin
 * @see gov.nih.nci.cabig.caaers.domain.expeditedfields.DefaultListDisplayNameCreator
 * @see gov.nih.nci.cabig.caaers.domain.expeditedfields.StaticDisplayNameCreator
 */
public interface DisplayNameCreator {
    
    /**
     * Creates the generic name.
     *
     * @return The name for the item in general, not necessarily a particular instance.
     */
    String createGenericName();

    /**
     * Creates the indexed name.
     *
     * @param i the i
     * @return the name for the <i>ith</i> instance of the item named by this creator
     */
    String createIndexedName(int i);
}
