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
