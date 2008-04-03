package gov.nih.nci.cabig.caaers.service.migrator;

import org.springframework.beans.factory.annotation.Required;

import gov.nih.nci.cabig.caaers.dao.OrganizationDao;
import gov.nih.nci.cabig.caaers.domain.AbstractIdentifiableDomainObject;
import gov.nih.nci.cabig.caaers.domain.Identifier;
import gov.nih.nci.cabig.caaers.domain.Organization;
import gov.nih.nci.cabig.caaers.domain.OrganizationAssignedIdentifier;
import gov.nih.nci.cabig.caaers.service.DomainObjectImportOutcome;
import gov.nih.nci.cabig.caaers.service.DomainObjectImportOutcome.Severity;


class IdentifierMigrator implements Migrator<AbstractIdentifiableDomainObject>{
	private OrganizationDao organizationDao;
	
	/**
	 * Will migrate the identifiers from source to destination
	 */
	public void migrate(AbstractIdentifiableDomainObject src,  AbstractIdentifiableDomainObject dest,
			DomainObjectImportOutcome<AbstractIdentifiableDomainObject> outcome) {

        // Identifiers
        for (Identifier identifier : src.getIdentifiers()) {
            if (identifier instanceof OrganizationAssignedIdentifier) {
            	OrganizationAssignedIdentifier orgIdentifier = (OrganizationAssignedIdentifier) identifier;
            	Organization organization = organizationDao.getByName(orgIdentifier.getOrganization().getName());
            	outcome.ifNullObject(organization, Severity.ERROR,"The organization specified in identifier is invalid");
            	orgIdentifier.setOrganization(organization);
            }
            
            dest.getIdentifiers().add(identifier);
        }

        outcome.ifNullOrEmptyList(dest.getIdentifiers(), Severity.ERROR, "Identifiers are either Empty or Not Valid");
		
	}
	
	
	///BEAN PROPERTIES

    @Required
    public void setOrganizationDao(OrganizationDao organizationDao) {
        this.organizationDao = organizationDao;
    }
}