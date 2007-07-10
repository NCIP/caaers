package gov.nih.nci.cabig.caaers.domain.attribution;

import gov.nih.nci.cabig.caaers.domain.DiseaseHistory;
import gov.nih.nci.cabig.caaers.domain.SurgeryIntervention;

import javax.persistence.Entity;
import javax.persistence.DiscriminatorValue;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;

/**
 * @author Rhett Sutphin
 */
@Entity
@DiscriminatorValue("SI")
public class SurgeryAttribution extends AdverseEventAttribution<SurgeryIntervention> {
    @ManyToOne
    @JoinColumn(name = "cause_id")
    @Override
    public SurgeryIntervention getCause() {
        return super.getCause();
    }
}
