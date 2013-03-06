/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("LOCAL")
public class LocalInvestigator extends Investigator{
	
    @Override
    public String getFirstName() {
        return firstName;
    }
    
    @Override
    public String getMiddleName() {
        return middleName;
    }    
    
    @Override
    public String getLastName() {
        return lastName;
    }
    
    @Override
	public String getEmailAddress() {
		return emailAddress;
	}

	@Override
	public String getFaxNumber() {
		return faxNumber;
	}

	@Override
	public String getPhoneNumber() {
		return phoneNumber;
	}

	@Override
    public String getNciIdentifier() {
        return nciIdentifier;
    }
	
}
