package gov.nih.nci.cabig.caaers.domain;

import gov.nih.nci.cabig.caaers.resolver.ResearchStaffResolver;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import com.semanticbits.coppa.domain.annotations.RemoteEntity;
import com.semanticbits.coppa.domain.annotations.RemoteProperty;
import com.semanticbits.coppa.domain.annotations.RemoteUniqueId;

@Entity
@RemoteEntity(entityResolver=ResearchStaffResolver.class)
@DiscriminatorValue("REMOTE")
public class RemoteResearchStaff extends ResearchStaff{
	
	@RemoteProperty
    public String getNciIdentifier() {
        return nciIdentifier;
    }
	
	@RemoteUniqueId
	@RemoteProperty
	public String getExternalId() {
		return externalId;
	}
	
	@RemoteProperty
	public String getFirstName() {
        return firstName;
    }
	
	@RemoteProperty
    public String getLastName() {
        return lastName;
    }
	
	@RemoteProperty
    public String getMiddleName() {
        return middleName;
    }
	
}
