/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.domain.expeditedfields;

 
/**
 * The Class LabsDisplayNameCreator.
 *
 * @author Rhett Sutphin
 */
public class LabsDisplayNameCreator implements DisplayNameCreator {
    
    /* (non-Javadoc)
     * @see gov.nih.nci.cabig.caaers.domain.expeditedfields.DisplayNameCreator#createGenericName()
     */
    public String createGenericName() {
        return "Labs";
    }

    /* (non-Javadoc)
     * @see gov.nih.nci.cabig.caaers.domain.expeditedfields.DisplayNameCreator#createIndexedName(int)
     */
    public String createIndexedName(int i) {
        return "Lab " + ((char) (i + 'A'));
    }
}
