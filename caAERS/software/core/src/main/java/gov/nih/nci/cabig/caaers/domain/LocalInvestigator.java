/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

 
/**
 * The Class LocalInvestigator.
 */
@Entity
@DiscriminatorValue("LOCAL")
public class LocalInvestigator extends Investigator{
	
    /* (non-Javadoc)
     * @see gov.nih.nci.cabig.caaers.domain.Person#getFirstName()
     */
    @Override
    public String getFirstName() {
        return firstName;
    }
    
    /* (non-Javadoc)
     * @see gov.nih.nci.cabig.caaers.domain.Person#getMiddleName()
     */
    @Override
    public String getMiddleName() {
        return middleName;
    }    
    
    /* (non-Javadoc)
     * @see gov.nih.nci.cabig.caaers.domain.Person#getLastName()
     */
    @Override
    public String getLastName() {
        return lastName;
    }
    
    /* (non-Javadoc)
     * @see gov.nih.nci.cabig.caaers.domain.Person#getEmailAddress()
     */
    @Override
	public String getEmailAddress() {
		return emailAddress;
	}

	/* (non-Javadoc)
	 * @see gov.nih.nci.cabig.caaers.domain.Person#getFaxNumber()
	 */
	@Override
	public String getFaxNumber() {
		return faxNumber;
	}

	/* (non-Javadoc)
	 * @see gov.nih.nci.cabig.caaers.domain.Person#getPhoneNumber()
	 */
	@Override
	public String getPhoneNumber() {
		return phoneNumber;
	}

	/* (non-Javadoc)
	 * @see gov.nih.nci.cabig.caaers.domain.Investigator#getNciIdentifier()
	 */
	@Override
    public String getNciIdentifier() {
        return nciIdentifier;
    }
	
}
