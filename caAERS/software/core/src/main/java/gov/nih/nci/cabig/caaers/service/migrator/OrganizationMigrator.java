package gov.nih.nci.cabig.caaers.service.migrator;

import gov.nih.nci.cabig.caaers.domain.Organization;
import gov.nih.nci.cabig.caaers.service.DomainObjectImportOutcome;

public class OrganizationMigrator implements Migrator<Organization>{

	public void migrate(Organization src, Organization dest,
			DomainObjectImportOutcome<Organization> outcome) {
		
		dest.setName(src.getName());
		dest.setDescriptionText(src.getDescriptionText());
		dest.setStatus(src.getStatus());
		dest.setMergedOrganization(src.getMergedOrganization());
		
		dest.setCity(src.getCity());
		dest.setCountry(src.getCountry());
		dest.setState(src.getState());
	}
}
