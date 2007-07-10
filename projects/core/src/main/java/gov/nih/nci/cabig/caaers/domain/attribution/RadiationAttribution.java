package gov.nih.nci.cabig.caaers.domain.attribution;

import gov.nih.nci.cabig.caaers.domain.SurgeryIntervention;
import gov.nih.nci.cabig.caaers.domain.RadiationIntervention;

import javax.persistence.Entity;
import javax.persistence.DiscriminatorValue;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;

/**
 * @author Rhett Sutphin
 */
@Entity
@DiscriminatorValue("RI")
public class RadiationAttribution extends AdverseEventAttribution<RadiationIntervention> {
    @ManyToOne
    @JoinColumn(name = "cause_id")
    @Override
    public RadiationIntervention getCause() {
        return super.getCause();
    }
}
