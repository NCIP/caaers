package gov.nih.nci.cabig.caaers.domain;

import gov.nih.nci.cabig.caaers.resolver.InvestigatorResolver;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import com.semanticbits.coppa.domain.annotations.RemoteEntity;

@Entity
@RemoteEntity(entityResolver=InvestigatorResolver.class)
@DiscriminatorValue("REMOTE")
public class RemoteInvestigator extends Investigator{
}
