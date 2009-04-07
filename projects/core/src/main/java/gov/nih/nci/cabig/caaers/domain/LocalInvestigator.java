package gov.nih.nci.cabig.caaers.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;

@Entity
@DiscriminatorValue("LOCAL")
public class LocalInvestigator extends Investigator{
	
	@Transient
	public String getExternalId() {
		return externalId;
	}
	
    @Override
    public String getFirstName() {
        return firstName;
    }
    
    @Override
    public String getLastName() {
        return lastName;
    }
    
    @Override
    public String getMiddleName() {
        return middleName;
    }

}
