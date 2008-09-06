package gov.nih.nci.cabig.caaers.domain.attribution;

import gov.nih.nci.cabig.caaers.domain.OtherCause;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * @author Rhett Sutphin
 */
@Entity
@DiscriminatorValue("OC")
public class OtherCauseAttribution extends AdverseEventAttribution<OtherCause> {
    @ManyToOne
    @JoinColumn(name = "cause_id")
    @Override
    public OtherCause getCause() {
        return super.getCause();
    }


    @Override
    public OtherCauseAttribution copy() {
        return super.copy();
    }
}
