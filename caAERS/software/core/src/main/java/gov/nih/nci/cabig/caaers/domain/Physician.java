package gov.nih.nci.cabig.caaers.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

 
/**
 * The Class Physician.
 *
 * @author Rhett Sutphin
 */
@Entity
@DiscriminatorValue("P")
public class Physician extends ReportPerson {
    
    /* (non-Javadoc)
     * @see gov.nih.nci.cabig.caaers.domain.ReportPerson#copy()
     */
    @Override
    public Physician copy() {
        return (Physician) super.copy();    //To change body of overridden methods use File | Settings | File Templates.
    }
}
