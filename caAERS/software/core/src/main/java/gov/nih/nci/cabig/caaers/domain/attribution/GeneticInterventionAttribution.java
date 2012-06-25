package gov.nih.nci.cabig.caaers.domain.attribution;

import gov.nih.nci.cabig.caaers.domain.GeneticIntervention;
import gov.nih.nci.cabig.caaers.domain.OtherAEIntervention;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * @author Ion C. Olaru
 */
@Entity
@DiscriminatorValue("GI")
public class GeneticInterventionAttribution extends AdverseEventAttribution<GeneticIntervention> {

    public GeneticInterventionAttribution(GeneticIntervention cause) {
        super(cause);
    }

    public GeneticInterventionAttribution() {
    }

    /* (non-Javadoc)
    * @see gov.nih.nci.cabig.caaers.domain.attribution.AdverseEventAttribution#getCause()
    */
    @ManyToOne
    @JoinColumn(name = "cause_id")
    @Override
    public GeneticIntervention getCause() {
        return super.getCause();
    }

    /* (non-Javadoc)
     * @see gov.nih.nci.cabig.caaers.domain.attribution.AdverseEventAttribution#copy()
     */
    @Override
    public GeneticInterventionAttribution copy() {
        return super.copy();
    }
}
