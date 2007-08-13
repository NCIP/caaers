package gov.nih.nci.cabig.caaers.domain.expeditedfields;

/**
 * @author Rhett Sutphin
 * @see gov.nih.nci.cabig.caaers.domain.expeditedfields.DefaultListDisplayNameCreator
 * @see gov.nih.nci.cabig.caaers.domain.expeditedfields.StaticDisplayNameCreator
 */
public interface DisplayNameCreator {
    /**
     * @return The name for the item in general, not necessarily a particular instance.
     */
    String createGenericName();

    /**
     * @param i
     * @return the name for the <i>ith</i> instance of the item named by this creator
     */
    String createIndexedName(int i);
}
