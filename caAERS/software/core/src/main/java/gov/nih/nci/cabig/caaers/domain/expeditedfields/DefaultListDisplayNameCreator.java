/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.domain.expeditedfields;

 
/**
 * This implementation uses a base name like "Concomitant medication" and returns:
 * <ul>
 * <li><code>createIndexedName(2)</code>: "Concomitant medication 3"</li>
 * <li><code>createGenericName()</code>: "Concomitant medications"
 * </ul>.
 *
 * @author Rhett Sutphin
 */
public class DefaultListDisplayNameCreator implements DisplayNameCreator {
    
    /** The base name. */
    private String baseName;

    /**
     * Instantiates a new default list display name creator.
     *
     * @param baseName the base name
     */
    public DefaultListDisplayNameCreator(String baseName) {
        this.baseName = baseName;
    }

    /* (non-Javadoc)
     * @see gov.nih.nci.cabig.caaers.domain.expeditedfields.DisplayNameCreator#createIndexedName(int)
     */
    public String createIndexedName(int i) {
        return new StringBuilder(baseName).append(' ').append(i + 1).toString();
    }

    /* (non-Javadoc)
     * @see gov.nih.nci.cabig.caaers.domain.expeditedfields.DisplayNameCreator#createGenericName()
     */
    public String createGenericName() {
        return baseName + 's';
    }
}
