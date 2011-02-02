package gov.nih.nci.cabig.caaers.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

 
/**
 * The Class LocalResearchStaff.
 */
@Entity
@DiscriminatorValue("LOCAL")
public class LocalResearchStaff extends ResearchStaff{
   
    /* (non-Javadoc)
     * @see gov.nih.nci.cabig.caaers.domain.Person#getFirstName()
     */
    @Override
    public String getFirstName() {
        return super.getFirstName();
    }
    
    /* (non-Javadoc)
     * @see gov.nih.nci.cabig.caaers.domain.Person#getMiddleName()
     */
    @Override
    public String getMiddleName() {
        return super.getMiddleName();
    }    
    
    /* (non-Javadoc)
     * @see gov.nih.nci.cabig.caaers.domain.Person#getLastName()
     */
    @Override
    public String getLastName() {
        return super.getLastName();
    }
    
    /* (non-Javadoc)
     * @see gov.nih.nci.cabig.caaers.domain.Person#getEmailAddress()
     */
    @Override
	public String getEmailAddress() {
		return super.getEmailAddress();
	}

	/* (non-Javadoc)
	 * @see gov.nih.nci.cabig.caaers.domain.Person#getFaxNumber()
	 */
	@Override
	public String getFaxNumber() {
		return super.getFaxNumber();
	}

	/* (non-Javadoc)
	 * @see gov.nih.nci.cabig.caaers.domain.Person#getPhoneNumber()
	 */
	@Override
	public String getPhoneNumber() {
		return super.getPhoneNumber();
	}

	/* (non-Javadoc)
	 * @see gov.nih.nci.cabig.caaers.domain.ResearchStaff#getNciIdentifier()
	 */
	@Override
	public String getNciIdentifier() {
		return super.getNciIdentifier();
	}
	
}
