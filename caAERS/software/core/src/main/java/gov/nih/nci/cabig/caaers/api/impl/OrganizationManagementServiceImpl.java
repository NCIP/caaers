package gov.nih.nci.cabig.caaers.api.impl;

import gov.nih.nci.cabig.caaers.api.OrganizationManagementService;
import gov.nih.nci.cabig.caaers.dao.OrganizationDao;
import gov.nih.nci.cabig.caaers.domain.EntityErrorMessage;
import gov.nih.nci.cabig.caaers.domain.LocalOrganization;
import gov.nih.nci.cabig.caaers.domain.Organization;
import gov.nih.nci.cabig.caaers.domain.repository.OrganizationRepository;
import gov.nih.nci.cabig.caaers.service.migrator.OrganizationMigrator;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.MessageSource;

public class OrganizationManagementServiceImpl implements OrganizationManagementService{
	
	private static Log logger = LogFactory.getLog(OrganizationManagementServiceImpl.class);
	private MessageSource messageSource;
    private OrganizationDao organizationDao;
    private OrganizationMigrator organizationMigrator;
    private OrganizationRepository  organizationRepository;

	public void setOrganizationRepository(
			OrganizationRepository organizationRepository) {
		this.organizationRepository = organizationRepository;
	}

	public void setOrganizationMigrator(OrganizationMigrator organizationMigrator) {
		this.organizationMigrator = organizationMigrator;
	}

	public void setMessageSource(MessageSource messageSource) {
		this.messageSource = messageSource;
	}

	public void setOrganizationDao(OrganizationDao organizationDao) {
		this.organizationDao = organizationDao;
	}

	public EntityErrorMessage createOrUpdateOrganization(Organization organization) {
		EntityErrorMessage errorMessage = new EntityErrorMessage();
		errorMessage.setBusinessId(organization.getNciInstituteCode());
		try {
			Organization dbOrganization = organizationDao.getByNCIcode(organization.getNciInstituteCode());
			// check if db organization with same NCI code exists
			if(dbOrganization !=null) {
				logger.info("found db Organization with NCI identifier: " + organization.getNciInstituteCode());
				// compare with db organization to see if any property changed
				if(organization.compareTo(dbOrganization) != 0){
					logger.info("updating db Organization with NCI identifier:" + organization.getNciInstituteCode() + " with remote Organization");
					organizationMigrator.migrate(organization, dbOrganization, null);
					organizationRepository.createOrUpdate(dbOrganization);
				} else {
					// db Organization doesn't exist. Create a new organization.
					logger.info("didn't find db Organization with NCI identifier:" + organization.getNciInstituteCode() + ". Creating new Organization");
					Organization newOrganization = new LocalOrganization();
					organizationMigrator.migrate(organization, newOrganization, null);
					organizationRepository.createOrUpdate(newOrganization);
				}
			} 
		} catch (Exception e) {
			errorMessage.addMessage(e.getMessage());
			logger.error(e.getMessage());
		}
		
		return errorMessage;
	}

	public List<EntityErrorMessage> createOrUpdateOrganizations(
			List<Organization> organizations) {
		List<EntityErrorMessage> errorMessages = new ArrayList<EntityErrorMessage>();
		for (Organization organization:organizations){
			errorMessages.add(createOrUpdateOrganization(organization));
		}
		return errorMessages;
	}
}
