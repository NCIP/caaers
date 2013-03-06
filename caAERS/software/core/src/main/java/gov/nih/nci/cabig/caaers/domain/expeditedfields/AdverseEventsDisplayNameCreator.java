/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.domain.expeditedfields;

 
/**
 * The Class AdverseEventsDisplayNameCreator.
 *
 * @author Rhett Sutphin
 */
public class AdverseEventsDisplayNameCreator extends DefaultListDisplayNameCreator {
    
    /**
     * Instantiates a new adverse events display name creator.
     */
    public AdverseEventsDisplayNameCreator() {
        super("Adverse event");
    }

    /* (non-Javadoc)
     * @see gov.nih.nci.cabig.caaers.domain.expeditedfields.DefaultListDisplayNameCreator#createIndexedName(int)
     */
    @Override
    public String createIndexedName(int i) {
        if (i == 0) return "Primary adverse event";
        else return super.createIndexedName(i);
    }
}
