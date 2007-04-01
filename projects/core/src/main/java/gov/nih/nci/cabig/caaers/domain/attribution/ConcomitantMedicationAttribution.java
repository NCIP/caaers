package gov.nih.nci.cabig.caaers.domain.attribution;

import gov.nih.nci.cabig.caaers.domain.ConcomitantMedication;

import javax.persistence.DiscriminatorValue;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Entity;

/**
 * @author Rhett Sutphin
 */
@Entity
@DiscriminatorValue("CM")
public class ConcomitantMedicationAttribution extends AdverseEventAttribution<ConcomitantMedication> {
    @ManyToOne
    @JoinColumn(name = "cause_id")
    @Override
    public ConcomitantMedication getCause() {
        return super.getCause();
    }
}
