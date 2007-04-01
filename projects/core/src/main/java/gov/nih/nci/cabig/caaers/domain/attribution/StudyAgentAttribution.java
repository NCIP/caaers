package gov.nih.nci.cabig.caaers.domain.attribution;

import gov.nih.nci.cabig.caaers.domain.StudyAgent;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * @author Rhett Sutphin
 */
@Entity
@DiscriminatorValue("SA")
public class StudyAgentAttribution extends AdverseEventAttribution<StudyAgent> {
    @ManyToOne
    @JoinColumn(name = "cause_id")
    @Override
    public StudyAgent getCause() {
        return super.getCause();
    }
}
