package gov.nih.nci.cabig.caaers.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;

/**
 * This class represents the StudyFundingSponsor domain object associated with the Adverse event
 * report.
 * 
 * @author Ram Chilukuri
 * 
 */

@Entity
@DiscriminatorValue(value = "SFS")
public class StudyFundingSponsor extends StudyOrganization {

    // TODO: save the primary flag to db... (we have only one study funding sponsor associated to
    // study [via UI]).

    boolean primary;

    @Transient
    public boolean isPrimary() {
        return true;
    }

    public void setPrimary(boolean primary) {
        this.primary = primary;
    }

    @Override
    @Transient
    public String getRoleName() {
        return "Sponsor";
    }
}
