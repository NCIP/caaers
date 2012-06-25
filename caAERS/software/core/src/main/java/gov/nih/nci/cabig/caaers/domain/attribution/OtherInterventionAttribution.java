package gov.nih.nci.cabig.caaers.domain.attribution;

import gov.nih.nci.cabig.caaers.domain.OtherAEIntervention;
import gov.nih.nci.cabig.caaers.domain.SurgeryIntervention;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * @author Ion C. Olaru
 */
@Entity
@DiscriminatorValue("OI")
public class OtherInterventionAttribution extends AdverseEventAttribution<OtherAEIntervention> {

    public OtherInterventionAttribution(OtherAEIntervention cause) {
        super(cause);
    }

    public OtherInterventionAttribution() {
    }

    /* (non-Javadoc)
    * @see gov.nih.nci.cabig.caaers.domain.attribution.AdverseEventAttribution#getCause()
    */
    @ManyToOne
    @JoinColumn(name = "cause_id")
    @Override
    public OtherAEIntervention getCause() {
        return super.getCause();
    }

    /* (non-Javadoc)
     * @see gov.nih.nci.cabig.caaers.domain.attribution.AdverseEventAttribution#copy()
     */
    @Override
    public OtherInterventionAttribution copy() {
        return super.copy();
    }
}
