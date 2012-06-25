package gov.nih.nci.cabig.caaers.domain.attribution;

import gov.nih.nci.cabig.caaers.domain.ConcomitantMedication;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

 
/**
 * The Class ConcomitantMedicationAttribution.
 *
 * @author Rhett Sutphin
 */
@Entity
@DiscriminatorValue("CM")
public class ConcomitantMedicationAttribution extends AdverseEventAttribution<ConcomitantMedication> {

    public ConcomitantMedicationAttribution(ConcomitantMedication cause) {
        super(cause);
    }

    public ConcomitantMedicationAttribution() {
    }

    /* (non-Javadoc)
    * @see gov.nih.nci.cabig.caaers.domain.attribution.AdverseEventAttribution#getCause()
    */
    @ManyToOne
    @JoinColumn(name = "cause_id")
    @Override
    public ConcomitantMedication getCause() {
        return super.getCause();
    }

    /* (non-Javadoc)
     * @see gov.nih.nci.cabig.caaers.domain.attribution.AdverseEventAttribution#copy()
     */
    @Override
    public ConcomitantMedicationAttribution copy() {
        return super.copy();
    }
}
