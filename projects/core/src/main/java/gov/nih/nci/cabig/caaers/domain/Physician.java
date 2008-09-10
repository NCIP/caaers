package gov.nih.nci.cabig.caaers.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * @author Rhett Sutphin
 */
@Entity
@DiscriminatorValue("P")
public class Physician extends ReportPerson {
    @Override
    public Physician copy() {
        return (Physician) super.copy();    //To change body of overridden methods use File | Settings | File Templates.
    }
}
