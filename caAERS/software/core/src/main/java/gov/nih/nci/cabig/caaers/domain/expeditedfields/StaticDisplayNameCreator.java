/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.domain.expeditedfields;

 
/**
 * This implementation returns the given display name, unmodified, for every query.
 * 
 * @author Rhett Sutphin
 */
public class StaticDisplayNameCreator implements DisplayNameCreator {
    
    /** The display name. */
    private String displayName;

    /**
     * Instantiates a new static display name creator.
     *
     * @param displayName the display name
     */
    public StaticDisplayNameCreator(String displayName) {
        this.displayName = displayName;
    }

    /* (non-Javadoc)
     * @see gov.nih.nci.cabig.caaers.domain.expeditedfields.DisplayNameCreator#createIndexedName(int)
     */
    public String createIndexedName(int i) {
        return displayName;
    }

    /* (non-Javadoc)
     * @see gov.nih.nci.cabig.caaers.domain.expeditedfields.DisplayNameCreator#createGenericName()
     */
    public String createGenericName() {
        return displayName;
    }
}
