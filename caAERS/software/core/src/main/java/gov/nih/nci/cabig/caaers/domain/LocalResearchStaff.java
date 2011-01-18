package gov.nih.nci.cabig.caaers.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("LOCAL")
public class LocalResearchStaff extends ResearchStaff{
   
    @Override
    public String getFirstName() {
        return super.getFirstName();
    }
    
    @Override
    public String getMiddleName() {
        return super.getMiddleName();
    }    
    
    @Override
    public String getLastName() {
        return super.getLastName();
    }
    
    @Override
	public String getEmailAddress() {
		return super.getEmailAddress();
	}

	@Override
	public String getFaxNumber() {
		return super.getFaxNumber();
	}

	@Override
	public String getPhoneNumber() {
		return super.getPhoneNumber();
	}

	@Override
	public String getNciIdentifier() {
		return super.getNciIdentifier();
	}
	
}
