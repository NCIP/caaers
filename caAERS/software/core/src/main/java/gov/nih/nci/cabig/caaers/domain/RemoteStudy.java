package gov.nih.nci.cabig.caaers.domain;

import gov.nih.nci.cabig.caaers.resolver.RemoteStudyResolver;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import com.semanticbits.coppa.domain.annotations.RemoteEntity;
import com.semanticbits.coppa.domain.annotations.RemoteProperty;
import com.semanticbits.coppa.domain.annotations.RemoteUniqueId;

@Entity
@RemoteEntity(entityResolver=RemoteStudyResolver.class)
@DiscriminatorValue("REMOTE")
public class RemoteStudy extends Study{
	
	private static final long serialVersionUID = 1L;

	@RemoteUniqueId
    @RemoteProperty
    public String getExternalId() {
		return externalId;
	}
	
	@RemoteProperty
    public String getShortTitle() {
        return shortTitle;
    }
	
	@RemoteProperty
    public String getLongTitle() {
        return longTitle;
    }
	
	@RemoteProperty
    public String getPhaseCode() {
        return phaseCode;
    }
	
	@RemoteProperty
    public String getDescription() {
        return description;
    }

	@RemoteProperty
    public String getStatus() {
        return status;
    }

}
