package gov.nih.nci.cabig.caaers.service.migrator;

import gov.nih.nci.cabig.caaers.domain.Agent;
import gov.nih.nci.cabig.caaers.service.DomainObjectImportOutcome;

import java.util.Date;

public class AgentMigrator implements Migrator<Agent>{

	public void migrate(Agent src, Agent dest, DomainObjectImportOutcome<Agent> outcome) {
		dest.setName(src.getName());
		dest.setDescription(src.getDescription());
		dest.setRetiredIndicator(src.getRetiredIndicator());
		dest.setNscNumber(src.getNscNumber());
        dest.setLastSynchedDate(new Date());
	}
}
