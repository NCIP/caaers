package gov.nih.nci.cabig.caaers.service.migrator;

import gov.nih.nci.cabig.caaers.domain.Organization;
import gov.nih.nci.cabig.caaers.service.DomainObjectImportOutcome;

import java.util.Date;

public class OrganizationMigrator implements Migrator<Organization>{

	public void migrate(Organization src, Organization dest, DomainObjectImportOutcome<Organization> outcome) {
		dest.setName(src.getName());
		dest.setDescriptionText(src.getDescriptionText());
		dest.setRetiredIndicator(src.getRetiredIndicator());
		dest.setNciInstituteCode(src.getNciInstituteCode());
		dest.setCity(src.getCity());
		dest.setCountry(src.getCountry());
		dest.setState(src.getState());
		dest.setType(src.getType());
        dest.setLastSynchedDate(new Date());
	}
}
