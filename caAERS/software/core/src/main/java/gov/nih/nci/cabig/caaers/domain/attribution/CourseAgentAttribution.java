package gov.nih.nci.cabig.caaers.domain.attribution;

import gov.nih.nci.cabig.caaers.domain.CourseAgent;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

 
/**
 * The Class CourseAgentAttribution.
 *
 * @author Rhett Sutphin
 */
@Entity
@DiscriminatorValue("CA")
public class CourseAgentAttribution extends AdverseEventAttribution<CourseAgent> {

    public CourseAgentAttribution(CourseAgent cause) {
        super(cause);
    }

    public CourseAgentAttribution() {
    }

    /* (non-Javadoc)
    * @see gov.nih.nci.cabig.caaers.domain.attribution.AdverseEventAttribution#getCause()
    */
    @ManyToOne
    @JoinColumn(name = "cause_id")
    @Override
    public CourseAgent getCause() {
        return super.getCause();
    }

    /* (non-Javadoc)
     * @see gov.nih.nci.cabig.caaers.domain.attribution.AdverseEventAttribution#copy()
     */
    @Override
    public CourseAgentAttribution copy() {
        return super.copy();
    }
}
