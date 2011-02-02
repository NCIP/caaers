package gov.nih.nci.cabig.caaers.domain;

import gov.nih.nci.cabig.caaers.resolver.OrganizationResolver;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import com.semanticbits.coppa.domain.annotations.RemoteEntity;
import com.semanticbits.coppa.domain.annotations.RemoteProperty;
import com.semanticbits.coppa.domain.annotations.RemoteUniqueId;

 
/**
 * The Class RemoteOrganization.
 */
@Entity
@RemoteEntity(entityResolver=OrganizationResolver.class)
@DiscriminatorValue("REMOTE")
public class RemoteOrganization extends Organization {
	
	
	/* (non-Javadoc)
	 * @see gov.nih.nci.cabig.caaers.domain.Organization#getExternalId()
	 */
	@RemoteUniqueId
    @RemoteProperty
    public String getExternalId() {
		return externalId;
	}
	
    /* (non-Javadoc)
     * @see gov.nih.nci.cabig.caaers.domain.Organization#getName()
     */
    @RemoteProperty
    public String getName() {
        return name;
    }
    
    /* (non-Javadoc)
     * @see gov.nih.nci.cabig.caaers.domain.Organization#getCity()
     */
    @RemoteProperty
    public String getCity() {
        return city;
    }
    
    /* (non-Javadoc)
     * @see gov.nih.nci.cabig.caaers.domain.Organization#getCountry()
     */
    @RemoteProperty
    public String getCountry() {
        return country;
    }
	
}