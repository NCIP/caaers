package gov.nih.nci.cabig.caaers.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("LOCAL")
public class LocalResearchStaff extends ResearchStaff{
   
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
}
