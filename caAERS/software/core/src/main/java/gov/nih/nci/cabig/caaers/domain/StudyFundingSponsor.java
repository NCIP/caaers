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

    /** The primary. */
    boolean primary;

    /**
     * Checks if is primary.
     *
     * @return true, if is primary
     */
    @Transient
    public boolean isPrimary() {
        return true;
    }

    /**
     * Sets the primary.
     *
     * @param primary the new primary
     */
    public void setPrimary(boolean primary) {
        this.primary = primary;
    }

    /* (non-Javadoc)
     * @see gov.nih.nci.cabig.caaers.domain.StudyOrganization#getRoleName()
     */
    @Override
    @Transient
    public String getRoleName() {
        return "Sponsor";
    }
}
