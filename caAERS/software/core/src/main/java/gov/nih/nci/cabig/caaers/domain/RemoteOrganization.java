package gov.nih.nci.cabig.caaers.domain;

import gov.nih.nci.cabig.caaers.resolver.OrganizationResolver;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import com.semanticbits.coppa.domain.annotations.RemoteEntity;
import com.semanticbits.coppa.domain.annotations.RemoteProperty;
import com.semanticbits.coppa.domain.annotations.RemoteUniqueId;

@Entity
@RemoteEntity(entityResolver=OrganizationResolver.class)
@DiscriminatorValue("REMOTE")
public class RemoteOrganization extends Organization {
	
	
	@RemoteUniqueId
    @RemoteProperty
    public String getExternalId() {
		return externalId;
	}
	
    @RemoteProperty
    public String getName() {
        return name;
    }
    
    @RemoteProperty
    public String getCity() {
        return city;
    }
    
    @RemoteProperty
    public String getCountry() {
        return country;
    }
	
}