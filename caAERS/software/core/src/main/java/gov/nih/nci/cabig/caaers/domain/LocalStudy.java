package gov.nih.nci.cabig.caaers.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;

 
/**
 * The Class LocalStudy.
 */
@Entity
@DiscriminatorValue(value="LOCAL")
public class LocalStudy extends Study{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

    /* (non-Javadoc)
     * @see gov.nih.nci.cabig.caaers.domain.Study#getShortTitle()
     */
    public String getShortTitle() {
        return shortTitle;
    }
	
    /* (non-Javadoc)
     * @see gov.nih.nci.cabig.caaers.domain.Study#getLongTitle()
     */
    public String getLongTitle() {
        return longTitle;
    }
	
    /* (non-Javadoc)
     * @see gov.nih.nci.cabig.caaers.domain.Study#getPhaseCode()
     */
    public String getPhaseCode() {
        return phaseCode;
    }
	
    /* (non-Javadoc)
     * @see gov.nih.nci.cabig.caaers.domain.Study#getDescription()
     */
    public String getDescription() {
        return description;
    }

    /* (non-Javadoc)
     * @see gov.nih.nci.cabig.caaers.domain.Study#getStatus()
     */
    public String getStatus() {
        return status;
    }
	
	/* (non-Javadoc)
	 * @see gov.nih.nci.cabig.caaers.domain.Study#getExternalId()
	 */
	@Transient
    public String getExternalId() {
		return externalId;
	}
}
