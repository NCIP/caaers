package gov.nih.nci.cabig.caaers.domain.attribution;

import gov.nih.nci.cabig.caaers.domain.DiseaseHistory;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * @author Rhett Sutphin
 */
@Entity
@DiscriminatorValue("DH")
public class DiseaseAttribution extends AdverseEventAttribution<DiseaseHistory> {
    @ManyToOne
    @JoinColumn(name = "cause_id")
    @Override
    public DiseaseHistory getCause() {
        return super.getCause();
    }

    @Override
    public DiseaseAttribution copy() {
        return super.copy();
    }

}
