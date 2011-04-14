package gov.nih.nci.cabig.caaers.domain.attribution;

import gov.nih.nci.cabig.caaers.domain.BiologicalIntervention;
import gov.nih.nci.cabig.caaers.domain.OtherAEIntervention;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * @author Ion C. Olaru
 */
@Entity
@DiscriminatorValue("BI")
public class BiologicalInterventionAttribution extends AdverseEventAttribution<BiologicalIntervention> {
    
    /* (non-Javadoc)
     * @see gov.nih.nci.cabig.caaers.domain.attribution.AdverseEventAttribution#getCause()
     */
    @ManyToOne
    @JoinColumn(name = "cause_id")
    @Override
    public BiologicalIntervention getCause() {
        return super.getCause();
    }

    /* (non-Javadoc)
     * @see gov.nih.nci.cabig.caaers.domain.attribution.AdverseEventAttribution#copy()
     */
    @Override
    public BiologicalInterventionAttribution copy() {
        return super.copy();
    }
}
