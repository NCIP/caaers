package gov.nih.nci.cabig.caaers.domain.attribution;

import gov.nih.nci.cabig.caaers.domain.CourseAgent;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * @author Rhett Sutphin
 */
@Entity
@DiscriminatorValue("CA")
public class CourseAgentAttribution extends AdverseEventAttribution<CourseAgent> {
    @ManyToOne
    @JoinColumn(name = "cause_id")
    @Override
    public CourseAgent getCause() {
        return super.getCause();
    }

    @Override
    public CourseAgentAttribution copy() {
        return super.copy();
    }
}
